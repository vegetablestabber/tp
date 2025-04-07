package modmate.command;

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

    public String getUsage() {
        return "Usage: " + this.getSyntax() + "\n\n"
                + this.getDescription() + "\nParameters:\n";
    }

    public abstract void execute(User user)
            throws CommandException, UserException, ApiException;

}
