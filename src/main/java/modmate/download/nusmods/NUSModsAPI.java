package modmate.download.nusmods;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;

import modmate.download.HttpUtil;
import modmate.download.json.ModJSONParser;
import modmate.log.Log;
import modmate.mod.Mod;

/**
 * A utility class that provides functionality for retrieving and managing
 * module data from the NUSMods API.
 * This class can fetch sample module data, retrieve module data by code,
 * and fetch module codes for a given academic year.
 */
public class NUSModsAPI {

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
            ModJSONParser jsonReader = new ModJSONParser(jsonObject);

            return Optional.of(jsonReader.getModule());
        } catch (URISyntaxException e) {
            // Log the stack trace to the log file for better tracking
            Log.saveLog("\n[MODDATARETREIVER]   Error fetching module data: " + e.getMessage());
            Log.saveLog("\n[MODDATARETREIVER]   Stack Trace: ");

            for (StackTraceElement element : e.getStackTrace()) {
                Log.saveLog("\t" + element.toString());
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
    public static Map<String, String> fetchAllModCodes(int startYear) {
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
    public static Map<String, String> fetchAllModCodes() {
        return fetchAllModCodes(NUSModsUtil.getAdjustedYear());
    }

    /**
     * Fetches the module list JSON data from the NUSMods API and saves it to a
     * local file.
     *
     * @param startYear the start year of the academic year
     */
    private static void downloadModListJSON(int startYear) {
        System.out.println("Downloading has started.");

        try {
            URI uri = NUSModsUtil.getUriForModuleList(startYear);
            String jsonResponse = HttpUtil.retrieveDataFromURL(uri).join();

            // Write the content of the API response to a file
            String filePath = NUSModsUtil.createModListFilePath(startYear);

            Path dataDirectory = Paths.get(filePath);
            Files.createDirectories(dataDirectory.getParent());

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(jsonResponse.getBytes());
            fileOutputStream.close();

            System.out.println("Data has been downloaded");
            Log.saveLog("\n[MODDATARETREIVER]   Data saved successfully to: " + filePath);
        } catch (IOException | URISyntaxException e) {
            // Log the error message and stack trace for better tracking
            Log.saveLog("\n[MODDATARETREIVER]   Error retrieving data from API: " + e.getMessage());
            Log.saveLog("\n[MODDATARETREIVER]   Stack Trace: ");

            for (StackTraceElement element : e.getStackTrace()) {
                Log.saveLog("\t" + element.toString());
            }
        }
    }

    /**
     * Loads the module codes from the previously saved file.
     *
     * @param startYear the start year of the academic year
     * @return a map of module codes and titles extracted from the file
     */
    private static Map<String, String> loadCondensedModData(int startYear) {
        String filePath = NUSModsUtil.createModListFilePath(startYear);
        Map<String, String> modulesMap = new HashMap<>();

        try {
            // Read file content as a string
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse JSON
            JSONArray modulesArray = new JSONArray(content);

            // Extract mod codes and titles
            IntStream.range(0, modulesArray.length()).forEach(i -> {
                JSONObject moduleJSON = modulesArray.getJSONObject(i);
                ModJSONParser parser = new ModJSONParser(moduleJSON);

                modulesMap.put(parser.getCode(), parser.getName());
            });
        } catch (IOException e) {
            // Log error details for file reading issue
            Log.saveLog("\n[MODDATARETREIVER]   Error reading file: " + filePath);
            Log.saveLog("\n[MODDATARETREIVER]   Error Message: " + e.getMessage());
            Log.saveLog("\n[MODDATARETREIVER]   Stack Trace: ");

            for (StackTraceElement element : e.getStackTrace()) {
                Log.saveLog("\t" + element.toString());
            }
        } catch (Exception e) {
            // Log error details for JSON parsing issue
            Log.saveLog("\n[MODDATARETREIVER]   Error parsing JSON from: " + filePath);
            Log.saveLog("\n[MODDATARETREIVER]   Error Message: " + e.getMessage());
            Log.saveLog("\n[MODDATARETREIVER]   Stack Trace: ");

            for (StackTraceElement element : e.getStackTrace()) {
                Log.saveLog("\t" + element.toString());
            }
        }

        return modulesMap;
    }

}
