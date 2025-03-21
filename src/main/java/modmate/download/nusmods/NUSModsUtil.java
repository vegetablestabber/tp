package modmate.download.nusmods;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.Month;

public class NUSModsUtil {

    private static final String CONDENSED_MODS_FILE_PATH = "src/main/java/modmate/download/nusmods/nusmods_condensed_%d-%d.json";
    private static final String MODULE_LIST_URI = "https://api.nusmods.com/v2/%d-%d/moduleList.json";
    private static final String MODULE_URI = "https://api.nusmods.com/v2/%d-%d/modules/%s.json";

    public static URI getUriForModuleList(int startYear) throws URISyntaxException {
        assert startYear > 2020;

        int endYear = startYear + 1;
        String uriString = String.format(MODULE_LIST_URI, startYear, endYear);

        return new URI(uriString);
    }

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
