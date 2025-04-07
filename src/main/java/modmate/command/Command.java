package modmate.command;

import java.util.List;
import java.util.StringJoiner;

import modmate.command.util.Argument;
import modmate.exception.ApiException;
import modmate.exception.CommandException;
import modmate.exception.UserException;
import modmate.ui.Input;
import modmate.user.User;

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

    protected String getUsage(List<Argument<?>> arguments) {
        StringJoiner sj = new StringJoiner("\n  ", "  ", "");
        arguments.forEach(arg ->
            sj.add(arg.getName() + ": " + arg.getDescription()));

        return getBasicUsage() + "\nArguments:\n" + sj.toString();
    }

    public abstract void execute(User user)
        throws CommandException, UserException, ApiException;

}