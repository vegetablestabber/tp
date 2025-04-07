package modmate.exception;

import modmate.command.Command;

public class CommandException extends IllegalArgumentException {

    public CommandException(Command command, String message) {
        super("Command error: " + message +
            "\nUsage: " + command.getSyntax());
    }

}
