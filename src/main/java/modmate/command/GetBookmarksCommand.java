package modmate.command;

import modmate.log.Log;
import modmate.mod.Mod;
import modmate.user.User;

import java.util.List;

public class GetBookmarksCommand implements Command {

    @Override
    public void execute(String[] args, User currentUser) {
        Log.saveLog("[MAIN]   Viewing bookmarks.");

        // View all bookmarked courses
        List<Mod> bookmarks = currentUser.getBookmarks();

        System.out.println("You have " + bookmarks.size() + " bookmark(s).");

        for (Mod mod : bookmarks) {
            System.out.println(mod);
        }
    }
}
