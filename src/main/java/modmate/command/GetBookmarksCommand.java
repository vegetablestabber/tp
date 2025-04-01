package modmate.command;

import modmate.log.LogUtil;
import modmate.mod.Mod;
import modmate.user.User;

import java.util.List;

public class GetBookmarksCommand implements Command {

    public static final String CLI_REPRESENTATION = "bookmarks";

    private static final LogUtil logUtil = new LogUtil(GetBookmarksCommand.class);

    @Override
    public void execute(String[] args, User currentUser) {
        logUtil.info("Viewing bookmarks.");

        // View all bookmarked courses
        List<Mod> bookmarks = currentUser.getBookmarks();

        System.out.println("You have " + bookmarks.size() + " bookmark(s).");

        for (Mod mod : bookmarks) {
            System.out.println(mod);
        }
    }
}
