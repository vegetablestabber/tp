package modmate.command;

import java.util.Optional;
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
        String inputCodeOrName = args[1];

        assert timetable != null
                && !timetable.trim().isEmpty() : "Timetable name cannot be null or empty";
        assert inputCodeOrName != null
                && !inputCodeOrName.trim().isEmpty() : "Mod code or name cannot be null or empty";

        if (!currentUser.hasTimetable(timetable)) {
            System.out.println("Timetable \"" + timetable + "\" not found.");
            logUtil.severe("Timetable '" + timetable + "' not found while attempting to remove mod.");
            return;
        }

        logUtil.info("Removing mod from timetable: " + timetable);

        Optional<Mod> modOpt = CommandUtil.modFromCodeOrName(inputCodeOrName);

        if (modOpt.isEmpty()) {
            System.out.println("Mod '" + inputCodeOrName + "' not found.");
            logUtil.severe("Mod '" + inputCodeOrName + "' not found.");
            return;
        }

        Mod mod = modOpt.get();
        boolean removed = currentUser.removeModFromTimetable(timetable, mod);
        if (removed) {
            logUtil.info("Mod " + mod.getCode() + " removed from timetable " + timetable);
        }
    }
}
