package modmate.mod;

import modmate.log.Log;
import modmate.mod.attribute.ModAttributes;
import modmate.mod.attribute.Faculty;
import modmate.mod.attribute.SemesterAvailability;
import modmate.mod.attribute.WeeklyWorkload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A utility class that provides functionality for retrieving and managing module data from an external API.
 * This class can fetch sample module data,
 * retrieve module data by code, and fetch module codes for a given academic year.
 */
public class ModDataRetreiver {

    /**
     * Returns the adjusted year based on the current month.
     * If the current month is before August, it returns the previous year,
     * otherwise, it returns the current year.
     *
     * @return the adjusted year
     */
    public static int getAdjustedYear() {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        return (today.getMonthValue() >= Month.AUGUST.getValue()) ? currentYear : currentYear - 1;
    }

    /**
     * Retrieves module information from the NUS Mod API using a module code.
     * This fetches data for the module, such as the title, description, faculty, semester availability,
     * and workload information.
     *
     * @param modCode the module code (e.g., "CS1010")
     * @return an {@link Optional} containing the {@link Mod} object if the data is successfully fetched,
     *     or empty if not
     */
    public static Optional<Mod> getModFromAPIUsingCode(String modCode) {
        modCode = modCode.toUpperCase();
        String startYear = String.valueOf(getAdjustedYear());
        String urlString = "https://api.nusmods.com/v2/" + startYear + "-" + (Integer.parseInt(startYear) + 1)
                + "/modules/" + modCode + ".json";

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                String jsonResponse = new String(inputStream.readAllBytes());
                inputStream.close();

                if (jsonResponse.isEmpty()) {
                    throw new IllegalArgumentException("JSON response is empty or null");
                }
                JSONObject jsonObject = new JSONObject(jsonResponse);

                Mod mod = new Mod(
                        jsonObject.getString("title"),
                        jsonObject.getString("moduleCode"),
                        jsonObject.getString("description"),
                        new ModAttributes(
                                new Faculty(jsonObject.getString("faculty")),
                                jsonObject.getJSONArray("semesterData").toList().stream()
                                        .filter(obj -> obj instanceof Map) // Ensure obj is a Map before casting
                                        .map(obj -> {
                                            Map<?, ?> map = (Map<?, ?>) obj; // Safe cast
                                            Object semesterObj = map.get("semester");

                                            // Map the semester number to the corresponding enum
                                            if (semesterObj instanceof Integer semester) {
                                                return switch (semester) {
                                                case 1 -> SemesterAvailability.SEMESTER_1;
                                                case 2 -> SemesterAvailability.SEMESTER_2;
                                                case 3 -> SemesterAvailability.SPECIAL_TERM_1;
                                                case 4 -> SemesterAvailability.SPECIAL_TERM_2;
                                                default -> throw new IllegalArgumentException(
                                                        "Unknown semester: " + semester
                                                );
                                                };
                                            } else {
                                                throw new IllegalArgumentException(
                                                        "Invalid semester format: " + semesterObj
                                                );
                                            }
                                        })
                                        .collect(Collectors.toList()),
                                jsonObject.getInt("moduleCredit"),
                                jsonObject.getString("gradingBasisDescription").equals("Graded"),
                                null,
                                // I know having null here isn't ideal, but it is an unfinished feature,
                                // so it can serve as a reminder to implement it
                                new WeeklyWorkload(
                                        jsonObject.getJSONArray("workload").getDouble(0),
                                        jsonObject.getJSONArray("workload").getDouble(1),
                                        jsonObject.getJSONArray("workload").getDouble(3),
                                        jsonObject.getJSONArray("workload").getDouble(4)
                                )
                        )
                );
                return Optional.of(mod);

            } else {
                Log.saveLog("\n[MODDATARETREIVER]   Request failed. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            Log.saveLog("\n[MODDATARETREIVER]   Error fetching module data: " + e.getMessage());
            // Log the stack trace to the log file for better tracking
            Log.saveLog("\n[MODDATARETREIVER]   Stack Trace: ");
            for (StackTraceElement element : e.getStackTrace()) {
                Log.saveLog("\t" + element.toString());
            }
        }


        return Optional.empty();
    }

    /**
     * Retrieves a list of all module codes for a given academic year from the NUS Mod API.
     * The data is saved to a local file, and then all the module codes are extracted from that file.
     *
     * @param startYear the start year of the academic year (e.g., "2024")
     * @return a map of module codes and titles
     */
    public static Map<String, String> getAllModCodes(String startYear) {
        retreiveJsonFromAPIToFile(startYear);
        return loadAllModCodesAndNamesFromFile(startYear);
    }

    /**
     * Retrieves a list of all module codes for the current academic year from the NUS Mod API.
     * The current year is determined by the adjusted year logic.
     * The data is saved to a local file, and then all the module codes are extracted from that file.
     *
     * @return a map of module codes and titles
     */
    public static Map<String, String> getAllModCodes() {
        String startYear = String.valueOf(getAdjustedYear());
        retreiveJsonFromAPIToFile(startYear);
        return loadAllModCodesAndNamesFromFile(startYear);
    }

    /**
     * Fetches the module list JSON data from the NUS Mod API and saves it to a local file.
     *
     * @param startYear the start year of the academic year
     */
    private static void retreiveJsonFromAPIToFile(String startYear) {
        try {
            String urlString = "https://api.nusmods.com/v2/" +
                    startYear +
                    "-" +
                    (Integer.parseInt(startYear) + 1) +
                    "/moduleList.json";
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();

                // Write the content of the API response to a file
                FileOutputStream fileOutputStream = new FileOutputStream(
                        "src/main/java/modmate/modsjson/allmods" +
                                startYear +
                                "-" +
                                (Integer.parseInt(startYear) + 1) +
                                ".json"
                );

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                fileOutputStream.close();
                inputStream.close();

                Log.saveLog("\n[MODDATARETREIVER]   Data saved successfully to: " +
                        "src/main/java/modmate/modsjson/allmods" +
                        startYear +
                        "-" +
                        (Integer.parseInt(startYear) + 1) +
                        ".json"
                );
            } else {
                Log.saveLog("\n[MODDATARETREIVER]   Request failed. Response Code: " + responseCode);
            }
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
    private static Map<String, String> loadAllModCodesAndNamesFromFile(String startYear) {
        Map<String, String> modulesMap = new HashMap<>();
        String filePath = "src/main/java/modmate/modsjson/allmods" +
                startYear +
                "-" +
                (Integer.parseInt(startYear) + 1) +
                ".json";

        try {
            // Read file content as a string
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse JSON
            JSONArray modulesArray = new JSONArray(content);

            // Extract mod codes and titles
            for (int i = 0; i < modulesArray.length(); i++) {
                JSONObject module = modulesArray.getJSONObject(i);
                if (module.has("moduleCode") && module.has("title")) {
                    String moduleCode = module.getString("moduleCode");
                    String title = module.getString("title");
                    modulesMap.put(moduleCode, title);
                }
            }
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
