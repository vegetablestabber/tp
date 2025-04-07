package modmate.command;

import modmate.exception.ApiException;
import modmate.exception.CommandException;
import modmate.exception.UserException;
import modmate.ui.Input;
import modmate.user.User;
import java.util.List;
import java.util.StringJoiner;

public abstract class Command {

    protected Input input;

    public Command(Input input) {
        this.input = input;
    }

    public abstract String getSyntax();

    public abstract String getDescription();

    private String getBasicUsage() {
        return "Description: " + getDescription()
            + "\nUsage: " + getSyntax();
    }

    public String getUsage() {
        return getBasicUsage();
    };

    protected String getUsage(List<Flag<?>> flags) {
        StringJoiner sj = new StringJoiner("\n  ", "  ", "");
        flags.forEach(flag ->
            sj.add(Flag.FLAG_PREFIX + flag.getName() + ": " + flag.getDescription()));

        return getBasicUsage() + "\nParameters:\n" + sj.toString();
    }

    public abstract void execute(User user)
        throws CommandException, UserException, ApiException;

}