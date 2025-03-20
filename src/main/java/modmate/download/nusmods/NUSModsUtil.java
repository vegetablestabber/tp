package modmate.download.nusmods;

import java.net.MalformedURLException;
import java.net.URL;

public class NUSModsUtil {

    private static final String MODULE_LIST_URL = "https://api.nusmods.com/v2/%d-%d/moduleList.json";
    private static final String MODULE_URL = "https://api.nusmods.com/v2/%d-%d/modules/%s.json";

    public static URL getUrlForModuleList(int startYear) throws MalformedURLException {
        int endYear = startYear + 1;
        String urlString = String.format(MODULE_LIST_URL, startYear, endYear);

        return new URL(urlString);
    }

    public static URL getUrlForModule(String moduleCode, int startYear) throws MalformedURLException {
        int endYear = startYear + 1;
        String urlString = String.format(MODULE_URL, startYear, endYear, moduleCode.toString());

        return new URL(urlString);
    }

}
