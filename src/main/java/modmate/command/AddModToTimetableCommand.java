package modmate.command;

import java.util.Optional;
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

        Optional<Mod> modOpt = CommandUtil.modFromCodeOrName(modQuery);

        if (modOpt.isEmpty()) {
            System.out.println("Mod \"" + modQuery + "\" not found.");
            logUtil.warning("Mod '" + modQuery + "' not found.");
            return;
        }

        Mod mod = modOpt.get();
        currentUser.addModToTimetable(timetableName, mod);
        logUtil.info("Mod " + mod.getCode() + " added to timetable " + timetableName);
    }
}

