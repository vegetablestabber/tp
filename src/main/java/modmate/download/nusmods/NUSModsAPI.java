package modmate.download.nusmods;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;

import modmate.download.HttpUtil;
import modmate.download.json.mod.CondensedModJSONParser;
import modmate.download.json.mod.ModJSONParser;
import modmate.exception.ApiException;
import modmate.log.LogUtil;
import modmate.mod.CondensedMod;
import modmate.mod.Mod;

/**
 * A utility class that provides functionality for retrieving and managing
 * module data from the NUSMods API.
 * This class can fetch sample module data, retrieve module data by code,
 * and fetch module codes for a given academic year.
 */
public class NUSModsAPI {
    public static final Map<String, CondensedMod> CONDENSED_MODS = NUSModsAPI.fetchAllModCodes();
    private static final LogUtil logUtil = new LogUtil(NUSModsAPI.class);

    /**
     * Helper method that searches for an exact matching mod by its code or name.
     *
     * @param identifier The code or name of the mod to search for.
     * @return The mod that matches the given code or name, or null if no match is
     *         found.
     */
    public static Mod modFromIdentifier(String identifier) throws ApiException {
        // First, check for a match with the module code (key)
        String key = identifier.toUpperCase();
        Optional<CondensedMod> condensedModOpt = Optional.ofNullable(CONDENSED_MODS.get(key))
            .or(() -> CONDENSED_MODS.values()
                .stream()
                .filter(condensedMod -> condensedMod.getName().equalsIgnoreCase(identifier))
                .findFirst());

        // If a match is found, retrieve mod details using the module code
        return condensedModOpt.flatMap(module -> NUSModsAPI.fetchModuleByCode(module.getCode()))
            .orElseThrow(() -> {
                String message = "Mod '" + identifier + "' not found";
                logUtil.severe(message);
                return new ApiException(message);
            });
    }

    /**
     * Retrieves module information from the NUSMods API using a module code.
     * This fetches data for the module, such as the title, description, faculty,
     * semester availability, and workload information.
     *
     * @param moduleCode the module code (e.g., "CS1010")
     * @return an {@link Optional} containing the {@link Mod} object if the data is
     *         successfully fetched, or empty if not
     */
    public static Optional<Mod> fetchModuleByCode(String moduleCode) {
        return fetchModuleByCode(moduleCode, NUSModsUtil.getAdjustedYear());
    }

    /**
     * Retrieves module information from the NUSMods API using a module code.
     * This fetches data for the module, such as the title, description, faculty,
     * semester availability, and workload information.
     *
     * @param moduleCode the module code (e.g., "CS1010")
     * @param startYear  the start year of the academic year (e.g., 2024)
     * @return an {@link Optional} containing the {@link Mod} object if the data is
     *         successfully fetched, or empty if not
     */
    public static Optional<Mod> fetchModuleByCode(String moduleCode, int startYear) {
        try {
            URI uri = NUSModsUtil.getUriForModule(moduleCode, startYear);
            String jsonResponse = HttpUtil.retrieveDataFromURL(uri).join();

            JSONObject jsonObject = new JSONObject(jsonResponse);
            ModJSONParser jsonParser = new ModJSONParser(jsonObject);

            return Optional.of(jsonParser.getModule());
        } catch (URISyntaxException e) {
            logUtil.severe("Error fetching module data: " + e.getMessage());
            logUtil.severe("Stack Trace:");
            for (StackTraceElement element : e.getStackTrace()) {
                logUtil.severe("\t" + element.toString());
            }
        }

        return Optional.empty();
    }

    /**
     * Retrieves a list of all module codes for a given academic year from the
     * NUSMods API.
     * The data is saved to a local file, and then all the module codes are
     * extracted from that file.
     *
     * @param startYear the start year of the academic year (e.g., 2024)
     * @return a map of module codes and titles
     */
    public static Map<String, CondensedMod> fetchAllModCodes(int startYear) {
        downloadModListJSON(startYear);
        return loadCondensedModData(startYear);
    }

    /**
     * Retrieves a list of all module codes for the current academic year from the
     * NUSMods API.
     * The current year is determined by the adjusted year logic.
     * The data is saved to a local file, and then all the module codes are
     * extracted from that file.
     *
     * @return a map of module codes and titles
     */
    public static Map<String, CondensedMod> fetchAllModCodes() {
        return fetchAllModCodes(NUSModsUtil.getAdjustedYear());
    }

    /**
     * Fetches the module list JSON data from the NUSMods API and saves it to a
     * local file.
     *
     * @param startYear the start year of the academic year
     */
    private static void downloadModListJSON(int startYear) {
        try {
            URI uri = NUSModsUtil.getUriForModuleList(startYear);
            String jsonResponse = HttpUtil.retrieveDataFromURL(uri).join();

            // Write the content of the API response to a file
            String filePath = NUSModsUtil.buildModListFilePath(startYear);

            Path dataDirectory = Paths.get(filePath);
            Files.createDirectories(dataDirectory.getParent());

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(jsonResponse.getBytes());
            fileOutputStream.close();

            logUtil.info("Data saved successfully to: " + filePath);
        } catch (IOException | URISyntaxException e) {
            logUtil.severe("Error retrieving data from API: " + e.getMessage());
            logUtil.severe("Stack Trace:");
            for (StackTraceElement element : e.getStackTrace()) {
                logUtil.severe("\t" + element.toString());
            }
        }
    }

    /**
     * Loads the module codes from the previously saved file.
     *
     * @param startYear the start year of the academic year
     * @return a map of module codes and titles extracted from the file
     */
    private static Map<String, CondensedMod> loadCondensedModData(int startYear) {
        String filePath = NUSModsUtil.buildModListFilePath(startYear);

        try {
            // Read file content as a string
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse JSON
            JSONArray moduleJSONArray = new JSONArray(content);

            // Extract mod codes and titles
            return IntStream.range(0, moduleJSONArray.length())
                .mapToObj(i -> moduleJSONArray.getJSONObject(i))
                .map(jsonObject -> {
                    CondensedModJSONParser jsonParser = new CondensedModJSONParser(jsonObject);
                    return jsonParser.getModule();
                })
                .collect(Collectors.toMap(mod -> mod.getCode(), mod -> mod));
        } catch (IOException e) {
            logUtil.warning("Error reading file: " + filePath);
            logUtil.warning("Error Message: " + e.getMessage());
            logUtil.warning("Stack Trace:");
            for (StackTraceElement element : e.getStackTrace()) {
                logUtil.warning("\t" + element.toString());
            }
        } catch (Exception e) {
            logUtil.warning("Error parsing JSON from: " + filePath);
            logUtil.warning("Error Message: " + e.getMessage());
            logUtil.warning("Stack Trace:");
            for (StackTraceElement element : e.getStackTrace()) {
                logUtil.warning("\t" + element.toString());
            }
        }

        return Collections.emptyMap();
    }

}
