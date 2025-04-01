package modmate.command;

import modmate.log.LogUtil;
import modmate.CommandCenter;
import modmate.user.User;

public class BookmarkCommand implements Command {

    public static final String CLI_REPRESENTATION = "bookmark";

    private static final LogUtil logUtil = new LogUtil(BookmarkCommand.class);

    @Override
    public void execute(String[] args, User currentUser) {
        if (args.length < 2) {
            System.out.println("Usage: bookmark <mod code or name>");
            return;
        }

        String inputCodeOrName = CommandCenter.stringFromBetweenPartsXY(args, 1);

        assert inputCodeOrName != null
                && !inputCodeOrName.trim().isEmpty() : "Mod code or name cannot be null or empty";
        logUtil.info("Bookmarking mod.");
        // Bookmark a mod for later reference

        CommandCenter.modFromCodeOrName(inputCodeOrName).ifPresentOrElse(mod -> {
            currentUser.addBookmark(mod);
            logUtil.info("Mod '" + inputCodeOrName + "' bookmarked.");
            System.out.println("Bookmark " + inputCodeOrName + " successfully added to your list.");
        }, () -> {
            logUtil.info("Course to bookmark not found.");
            System.out.println("Course with code '" + inputCodeOrName + "' not found.");
        });
    }
}
