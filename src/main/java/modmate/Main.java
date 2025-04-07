package modmate;

import modmate.download.nusmods.NUSModsAPI;
import modmate.log.LogUtil;
import modmate.ui.UI;
import modmate.user.User;

/**
 * Main entry point for the ModMate application. It handles user input
 * and executes commands such as viewing mods, adding/removing mods
 * to/from timetables, and managing bookmarks.
 */
public class Main {

    private static final LogUtil logUtil = new LogUtil(Main.class);

    /**
     * The main command loop of the application that processes user input
     * and executes corresponding actions based on the command received.
     *
     * @param args Command-line arguments (for logging configuration).
     */
    public static void main(String[] args) {

        // for (int i = 0; i < args.length; i++) {
        //     if (args[i].equals("--startYear") && i + 1 < args.length) {
        //         int startYear = Integer.parseInt(args[i + 1]);
        //         CommandCenter.allModCodesAndNames = NUSModsAPI.fetchAllModCodes(startYear);
        //         log.info("Start year set to: " + startYear);
        //     }
        // }

        // NUSModsAPI.loadAllCondensedMods();

        User currentUser = new User();
        UI.run(currentUser);
    }
}
