package modmate.command;

import modmate.exception.UserException;
import modmate.log.LogUtil;
import modmate.ui.Input;
import modmate.user.User;

public class CreateTimetableCommand extends Command {

    public static final String CLI_REPRESENTATION = "createtimetable";

    private static final LogUtil logUtil = new LogUtil(CreateTimetableCommand.class);

    public CreateTimetableCommand(Input input) {
        super(input);
    }

    @Override
    public String getSyntax() {
        return CommandUtil.concatenate(
            CLI_REPRESENTATION,
            "<timetable>"
        );
    }

    @Override
    public String getDescription() {
        return "Create a new timetable.";
    }

    @Override
    public String getUsage() {
        return super.getUsage() + "  <timetable>: The name of the timetable to create.";
    }

    @Override
    public void execute(User user) throws UserException {
        String inputTimetableName = input.getArgument();

        if (user.hasTimetable(inputTimetableName)) {
            logUtil.warning("Timetable " + inputTimetableName + " already exists.");
            throw new UserException("Timetable with this name already exists.");
        }

        logUtil.info("Creating timetable: " + inputTimetableName);
        user.addTimetable(inputTimetableName.replaceAll("\\s+", ""));
        logUtil.info("Timetable " + inputTimetableName + " created successfully.");
    }

}
