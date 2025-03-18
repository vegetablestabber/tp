package modmate;

import modmate.log.Log;
import modmate.mod.Mod;
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
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.Month;

/**
 * A utility class that provides a sample list of mods.
 * This class is used to generate a list of mods with details like mod code, name, description, and prerequisites.
 */
public class ModDataRetreiver {

    public static int getAdjustedYear() {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();

        return (today.getMonthValue() >= Month.AUGUST.getValue()) ? currentYear : currentYear - 1;
    }


    /**
     * Returns a list of sample mods.
     * <p>
     * Each mod is represented by a {@link Mod} object containing the following details:
     * <ul>
     *     <li>Mod code (e.g., "CS1010")</li>
     *     <li>Mod name (e.g., "Programming Methodology")</li>
     *     <li>Mod description (e.g., "Introduction to programming")</li>
     *     <li>Prerequisites (List of Mod objects)</li>
     * </ul>
     *
     * @return a list of {@link Mod} objects representing sample mods
     */
    public static List<Mod> getSampleMods() {
        // Faculties
        Faculty computingfaculty = new Faculty("School of Computing");
        Faculty businessfaculty = new Faculty("School of Business");

        // Semester availabilities
        List<SemesterAvailability> semesterAvailability1and2 = List.of(
                SemesterAvailability.SEMESTER_1,
                SemesterAvailability.SEMESTER_2);

        List<SemesterAvailability> semesterAvailability1 = List.of(
                SemesterAvailability.SEMESTER_1);

        List<SemesterAvailability> semesterAvailability2 = List.of(
                SemesterAvailability.SEMESTER_2);

        // Workloads
        WeeklyWorkload workload1 = new WeeklyWorkload(3, 6, 9, 12);
        WeeklyWorkload workload2 = new WeeklyWorkload(4, 8, 12, 16);
        WeeklyWorkload workload3 = new WeeklyWorkload(5, 10, 15, 20);

        // Mods
        // Programming Methodology I
        ModAttributes caProgMeth = new ModAttributes(
                computingfaculty,
                semesterAvailability1and2,
                4,
                true,
                null,
                workload1);

        Mod progMeth1 = new Mod(
                "Programming Methodology",
                "CS1010",
                "Introduction to programming",
                caProgMeth);

        // Programming Methodology II
        List<Mod> prqProgMeth1 = List.of(progMeth1);
        ModAttributes caProgMeth2 = new ModAttributes(
                computingfaculty,
                semesterAvailability2,
                4,
                true,
                prqProgMeth1,
                workload1);

        Mod progMeth2 = new Mod(
                "Programming Methodology II",
                "CS2030",
                "Advanced programming",
                caProgMeth2);

        // Software Engineering
        ModAttributes caSoftwareEngineering = new ModAttributes(
                computingfaculty,
                semesterAvailability1,
                4,
                true,
                List.of(),
                workload2);

        Mod softwareEng = new Mod(
                "Software Engineering",
                "CS2103T",
                "Introduction to software engineering",
                caSoftwareEngineering);

        // Computer Networks
        ModAttributes caComputerNetworks = new ModAttributes(
                computingfaculty,
                semesterAvailability1and2,
                4,
                true,
                List.of(),
                workload3);

        Mod computerNetworks = new Mod(
                "Computer Networks",
                "CS2105",
                "Introduction to computer networks",
                caComputerNetworks);

        // Operating Systems
        ModAttributes caOperatingSystems = new ModAttributes(
                computingfaculty,
                semesterAvailability1and2,
                4,
                false,
                List.of(),
                workload2);

        Mod operatingSystems = new Mod(
                "Operating Systems",
                "CS2106",
                "Introduction to operating systems",
                caOperatingSystems);

        // Principles of Accounting
        ModAttributes caPrinciplesOfAccounting = new ModAttributes(
                businessfaculty,
                semesterAvailability1,
                4,
                true,
                List.of(),
                workload2);

        Mod principlesOfAccounting = new Mod(
                "Principles of Accounting",
                "ACC1002",
                "Introduction to accounting",
                caPrinciplesOfAccounting);

        return List.of(
                progMeth1,
                progMeth2,
                softwareEng,
                computerNetworks,
                operatingSystems,
                principlesOfAccounting
        );
    }

    public static Mod getModFromAPIUsingCode(String modCode) {
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

                                jsonObject.getString("semesterData").contains("1") && jsonObject.getString("semesterData").contains("2") ?
                                        List.of(SemesterAvailability.SEMESTER_1, SemesterAvailability.SEMESTER_2) :
                                        jsonObject.getString("semesterData").contains("1") ?
                                                List.of(SemesterAvailability.SEMESTER_1) :
                                                List.of(SemesterAvailability.SEMESTER_2),
                                jsonObject.getInt("moduleCredit"),
                                jsonObject.getString("gradingBasisDescription").equals("Graded"),
                                null,
                                // TODO implement reading prerequisites
                                new WeeklyWorkload(
                                        jsonObject.getJSONArray("workload").getDouble(0),
                                        jsonObject.getJSONArray("workload").getDouble(1),
                                        jsonObject.getJSONArray("workload").getDouble(3),
                                        jsonObject.getJSONArray("workload").getDouble(4)

                                )
                        )
                );
                return mod;

            } else {
                Log.saveLog("\n[MODDATARETREIVER]   Request failed. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            Log.saveLog("\n[MODDATARETREIVER]   Error fetching module data: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }


    public static List<String> getAllModCodes(String startYear) {
        retreiveJsonFromAPIToFile(startYear);
        return loadAllModCodesFromFile(startYear);
    }

    public static List<String> getAllModCodes() {
        String startYear = String.valueOf(getAdjustedYear());
        retreiveJsonFromAPIToFile(startYear);
        return loadAllModCodesFromFile(startYear);
    }

    private static void retreiveJsonFromAPIToFile(String startYear) {
        try {
            String urlString = "https://api.nusmods.com/v2/" + startYear + "-" + (Integer.parseInt(startYear) + 1) + "/moduleList.json";
            URL url = new URL(urlString);


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();

                FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/modmate/modsjson/allmods" + startYear + "-" + (Integer.parseInt(startYear) + 1) + ".json");

                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                fileOutputStream.close();
                inputStream.close();

                Log.saveLog("\n[MODDATARETREIVER]   Data saved successfully to: src/main/java/modmate/modsjson/allmods" + startYear + "-" + (Integer.parseInt(startYear) + 1) + ".json");
            } else {
                Log.saveLog("\n[MODDATARETREIVER]   Request failed. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> loadAllModCodesFromFile(String startYear) {
        List<String> modCodes = new ArrayList<>();
        String filePath = "src/main/java/modmate/modsjson/allmods" + startYear + "-" + (Integer.parseInt(startYear) + 1) + ".json";

        try {
            // Read file content as a string
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse JSON
            JSONArray modulesArray = new JSONArray(content);

            // Extract mod codes
            for (int i = 0; i < modulesArray.length(); i++) {
                JSONObject module = modulesArray.getJSONObject(i);
                if (module.has("moduleCode")) {
                    modCodes.add(module.getString("moduleCode"));
                }
            }
        } catch (IOException e) {
            Log.saveLog("\n[MODDATARETREIVER]   Error reading file: " + filePath);
            e.printStackTrace();
        } catch (Exception e) {
            Log.saveLog("\n[MODDATARETREIVER]   Error parsing JSON from: "+ filePath);
            e.printStackTrace();
        }

        return modCodes;
    }
}
