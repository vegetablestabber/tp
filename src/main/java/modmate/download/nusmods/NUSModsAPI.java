package modmate.download.nusmods;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
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
import modmate.log.Log;
import modmate.mod.CondensedMod;
import modmate.mod.Mod;

/**
 * A utility class that provides functionality for retrieving and managing
 * module data from the NUSMods API.
 * This class can fetch sample module data, retrieve module data by code,
 * and fetch module codes for a given academic year.
 */
public class NUSModsAPI {

    /**
     * Returns the adjusted year based on the current month.
     * If the current month is before August, it returns the previous year,
     * otherwise, it returns the current year.
     *
     * @return the adjusted year
     */
    private static int getAdjustedYear() {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        return (today.getMonthValue() >= Month.AUGUST.getValue()) ? currentYear : currentYear - 1;
    }

    /**
     * Creates the file path for storing the module list JSON data.
     *
     * @param startYear the start year of the academic year
     * @return the file path as a string
     */
    private static String createModListFilePath(int startYear) {
        int endYear = startYear + 1;
        return String.format("src/main/java/modmate/mod/modsjson/allmods%d-%d.json", startYear, endYear);
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
        return fetchModuleByCode(moduleCode, getAdjustedYear());
    }

    /**
     * Retrieves module information from the NUSMods API using a module code.
     * This fetches data for the module, such as the title, description, faculty,
     * semester availability, and workload information.
     *
     * @param moduleCode the module code (e.g., "CS1010")
     * @param startYear the start year of the academic year (e.g., 2024)
     * @return an {@link Optional} containing the {@link Mod} object if the data is
     *         successfully fetched, or empty if not
     */
    public static Optional<Mod> fetchModuleByCode(String moduleCode, int startYear) {
        try {
            URL url = NUSModsUtil.getUrlForModule(moduleCode, startYear);
            String jsonResponse = HttpUtil.retrieveDataFromURL(url);

            JSONObject jsonObject = new JSONObject(jsonResponse);
            ModJSONParser jsonParser = new ModJSONParser(jsonObject);

            return Optional.of(jsonParser.getModule());
        } catch (IOException e) {
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
     * Retrieves a list of all module codes for a given academic year from the NUSMods API.
     * The data is saved to a local file, and then all the module codes are extracted from that file.
     *
     * @param startYear the start year of the academic year (e.g., 2024)
     * @return a map of module codes and titles
     */
    public static Map<String, CondensedMod> fetchAllModCodes(int startYear) {
        downloadModListJSON(startYear);
        return loadCondensedModData(startYear);
    }

    /**
     * Retrieves a list of all module codes for the current academic year from the NUSMods API.
     * The current year is determined by the adjusted year logic.
     * The data is saved to a local file, and then all the module codes are extracted from that file.
     *
     * @return a map of module codes and titles
     */
    public static Map<String, CondensedMod> fetchAllModCodes() {
        return fetchAllModCodes(getAdjustedYear());
    }

    /**
     * Fetches the module list JSON data from the NUSMods API and saves it to a local file.
     *
     * @param startYear the start year of the academic year
     */
    private static void downloadModListJSON(int startYear) {
        try {
            URL url = NUSModsUtil.getUrlForModuleList(startYear);
            String jsonResponse = HttpUtil.retrieveDataFromURL(url);

            // Write the content of the API response to a file
            String filePath = createModListFilePath(startYear);
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            fileOutputStream.write(jsonResponse.getBytes());
            fileOutputStream.close();

            Log.saveLog("\n[MODDATARETREIVER]   Data saved successfully to: " + filePath);
        } catch (IOException e) {
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
    private static Map<String, CondensedMod> loadCondensedModData(int startYear) {
        String filePath = createModListFilePath(startYear);

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

        return Collections.emptyMap();
    }

}
