package modmate.command;

import modmate.CommandCenter;
import modmate.log.LogUtil;
import modmate.user.User;

public class RemoveBookmarkCommand implements Command {

    public static final String CLI_REPRESENTATION = "removebookmark";

    private static final LogUtil logUtil = new LogUtil(RemoveBookmarkCommand.class);

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 2) {
            System.out.println("Usage: removebookmark <mod code or name>");
            return;
        }

        String inputCodeOrName = CommandCenter.stringFromBetweenPartsXY(args, 1);

        assert inputCodeOrName != null
                && !inputCodeOrName.trim().isEmpty() : "Mod code or name cannot be null or empty";
        logUtil.info("Removing bookmark for mod.");

        CommandCenter.modFromCodeOrName(inputCodeOrName).ifPresentOrElse(mod -> {
            if (!currentUser.hasBookmark(mod)) {
                logUtil.warning("Mod '" + mod.getCode() + "' not in bookmarks.");
                System.out.println("Mod " + mod.getCode() + " isn't in your bookmarks.");
                return;
            }
            currentUser.removeBookmark(mod);
            logUtil.info("Removed mod '" + mod.getCode() + "' from bookmarks.");
            System.out.println("Removed mod " + mod.getCode() + " from bookmarks.");
        }, () -> {
            logUtil.info("Course to remove bookmark not found.");
            System.out.println("Course with code '" + inputCodeOrName.toUpperCase() + "' not found.");
        });
    }
}
