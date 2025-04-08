package modmate.command;

import java.util.Optional;
import modmate.download.nusmods.NUSModsAPI;
import modmate.exception.ApiException;
import modmate.exception.CommandException;
import modmate.exception.UserException;
import modmate.log.LogUtil;
import modmate.mod.Mod;
import modmate.ui.Input;
import modmate.user.User;

public class RemoveModFromTimetableCommand extends Command {

    public static final String CLI_REPRESENTATION = "removemod";

    private static final LogUtil logUtil = new LogUtil(RemoveModFromTimetableCommand.class);

    public RemoveModFromTimetableCommand(Input input) {
        super(input);
    }

    @Override
    public String getDescription() {
        return "Remove a mod from your list.";
    }

    @Override
    public String getUsage() {
        return super.getUsage()
            + "  <timetable name>: The name of the timetable.\n"
            + "  <mod code or name>: The code or name of the mod to remove.";
    }

    @Override
    public String getSyntax() {
        return CLI_REPRESENTATION + " <timetable name> <mod code or name>";
    }

    @Override
    public void execute(User currentUser) throws CommandException, UserException, ApiException {
        String[] args = input.getArgument().split(" ", 2);
        if (args.length < 2) {
            System.out.println("Usage: " + getSyntax());
            return;
        }

        String timetable = args[0];
        String modQuery = args[1];

        if (!currentUser.hasTimetable(timetable)) {
            System.out.println("Timetable \"" + timetable + "\" not found.");
            logUtil.severe("Timetable '" + timetable + "' not found while attempting to remove mod.");
            return;
        }

        logUtil.info("Removing mod from timetable: " + timetable);

        try {
            Mod mod = NUSModsAPI.modFromIdentifier(modQuery);
            boolean removed = currentUser.removeModFromTimetable(timetable, mod);
            if (removed) {
                logUtil.info("Mod " + mod.getCode() + " removed from timetable " + timetable);
            }
        } catch (ApiException e) {
            System.out.println("Mod \"" + modQuery + "\" not found.");
            logUtil.severe("Failed to fetch mod details: " + e.getMessage());
        }
    }
}
