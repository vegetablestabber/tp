package modmate.download.nusmods;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.Month;

public class NUSModsUtil {

    /**
     * The file path template for the condensed NUSMods JSON files.
     * The placeholders %d-%d in the file path will be replaced with the academic year range.
     * For example, if the academic year is 2023-2024, the file path will be:
     * "src/main/java/modmate/download/nusmods/nusmods_condensed_2023-2024.json".
     */
    private static final String CONDENSED_MODS_FILE_PATH = "src/main/java/modmate/download/nusmods/nusmods_condensed_%d-%d.json";

    /**
     * The base URI for retrieving the list of modules from NUSMods API.
     * The URI is formatted with two integer values representing the academic year.
     * For example, to get the module list for the academic year 2021/2022, the URI would be:
     * https://api.nusmods.com/v2/2021-2022/moduleList.json
     */
    private static final String MODULE_LIST_URI = "https://api.nusmods.com/v2/%d-%d/moduleList.json";

    /**
     * The base URI for accessing module information from the NUSMods API.
     * The URI is formatted with the academic year and module code.
     * Example usage: String.format(MODULE_URI, 2021, 2022, "CS2113");
     */
    private static final String MODULE_URI = "https://api.nusmods.com/v2/%d-%d/modules/%s.json";

    /**
     * Generates a URI for the module list for a given academic year.
     *
     * @param startYear The starting year of the academic year (must be greater than 2020).
     * @return A URI pointing to the module list for the specified academic year.
     * @throws URISyntaxException If the generated URI string is not a valid URI.
     */
    public static URI getUriForModuleList(int startYear) throws URISyntaxException {
        assert startYear > 2020;

        int endYear = startYear + 1;
        String uriString = String.format(MODULE_LIST_URI, startYear, endYear);

        return new URI(uriString);
    }

    /**
     * Generates a URI for the specified module code and academic year.
     *
     * @param moduleCode The code of the module (e.g., "CS2113").
     * @param startYear The starting year of the academic year (e.g., 2023 for the academic year 2023/2024).
     * @return A URI object representing the module's URL.
     * @throws URISyntaxException If the generated URI string is not a valid URI.
     */
    public static URI getUriForModule(String moduleCode, int startYear) throws URISyntaxException {
        assert startYear > 2020;

        int endYear = startYear + 1;
        String uriString = String.format(MODULE_URI, startYear, endYear, moduleCode.toString());

        return new URI(uriString);
    }

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
     * Creates the file path for storing the module list JSON data.
     *
     * @param startYear the start year of the academic year
     * @return the file path as a string
     */
    public static String createModListFilePath(int startYear) {
        int endYear = startYear + 1;
        return String.format(CONDENSED_MODS_FILE_PATH,
                startYear, endYear);
    }

}
