package modmate.command;

import modmate.command.util.Flag;
import modmate.download.nusmods.NUSModsAPI;
import modmate.exception.ApiException;
import modmate.exception.CommandException;
import modmate.log.LogUtil;
import modmate.mod.Mod;
import modmate.ui.Input;
import modmate.user.User;

import java.util.List;

public class BookmarkCommand extends Command {

    public static final String CLI_REPRESENTATION = "bookmark";

    private static final LogUtil logUtil = new LogUtil(BookmarkCommand.class);

    private final Flag<String> modCodeOrNameFlag;

    public BookmarkCommand(Input input) {
        super(input);
        this.modCodeOrNameFlag = new Flag<>(
            "mod code or name",
            input.getArgument(),
            "The code or name of the mod to bookmark.",
            true,
            "value"
        );

        if (input.getArgument().isEmpty()) {
            throw new CommandException(this, "Mod code or name cannot be empty");
        }
    }

    @Override
    public String getSyntax() {
        return CommandUtil.buildSyntax(CLI_REPRESENTATION, "", List.of(modCodeOrNameFlag));
    }

    @Override
    public String getDescription() {
        return "Bookmark a mod for later reference.";
    }

    @Override
    public String getUsage() {
        return super.getUsage(List.of(modCodeOrNameFlag));
    }

    @Override
    public void execute(User currentUser) throws ApiException {
        String identifier = input.getArgument();
        logUtil.info("Bookmarking mod.");

        // Bookmark a mod for later reference
        Mod mod = NUSModsAPI.modFromIdentifier(identifier);
        currentUser.addBookmark(mod);

        logUtil.info("Mod '" + identifier + "' bookmarked.");
        System.out.println("Bookmark " + identifier + " successfully added to your list.");
    }

}
