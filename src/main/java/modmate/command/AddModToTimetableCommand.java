package modmate.command;

import modmate.download.nusmods.NUSModsAPI;
import modmate.exception.ApiException;
import modmate.exception.CommandException;
import modmate.exception.UserException;
import modmate.mod.Mod;
import modmate.log.LogUtil;
import modmate.ui.Input;
import modmate.user.User;

public class AddModToTimetableCommand extends Command {

    public static final String CLI_REPRESENTATION = "addmod";

    private static final LogUtil logUtil = new LogUtil(AddModToTimetableCommand.class);

    public AddModToTimetableCommand(Input input) {
        super(input);
    }

    @Override
    public String getSyntax() {
        return CLI_REPRESENTATION + " <timetable name> <mod code or name>";
    }

    @Override
    public String getDescription() {
        return "Add a mod to your list.";
    }

    @Override
    public String getUsage() {
        return super.getUsage()
            + "  <timetable name>: The name of the timetable.\n"
            + "  <mod code or name>: The code or name of the mod to add.";
    }

    @Override
    public void execute(User currentUser) throws CommandException, UserException, ApiException {
        String[] args = input.getArgument().split(" ", 2);
        if (args.length < 2) {
            System.out.println("Usage: " + getSyntax());
            return;
        }

        String timetableName = args[0];
        String modQuery = args[1];

        if (!currentUser.hasTimetable(timetableName)) {
            System.out.println("Timetable \"" + timetableName + "\" not found.");
            logUtil.warning("Timetable '" + timetableName + "' not found.");
            return;
        }

        try {
            Mod mod = NUSModsAPI.modFromIdentifier(modQuery);
            currentUser.addModToTimetable(timetableName, mod);
            logUtil.info("Mod " + mod.getCode() + " added to timetable " + timetableName);
        } catch (ApiException e) {
            System.out.println("Mod \"" + modQuery + "\" not found.");
            logUtil.severe("Failed to fetch mod details: " + e.getMessage());
        }
    }
}

