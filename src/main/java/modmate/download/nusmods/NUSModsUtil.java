package modmate.download.nusmods;

import java.net.URI;
import java.net.URISyntaxException;

public class NUSModsUtil {

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

}
