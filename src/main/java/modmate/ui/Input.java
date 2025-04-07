package modmate.ui;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class Input {

    private final String command;
    private final String argument;
    private final Map<String, String> flags;

    public Input(String command, String argument, Map<String, String> flags) {
        this.command = command;
        this.argument = argument;
        this.flags = flags;
    }


    public Input(String command, String argument) {
        this(command, argument, Collections.emptyMap());
    }

    public String getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }

    public Optional<String> getFlag(String flagName) {
        return Optional.ofNullable(flags.get(flagName))
            .filter(str -> !str.isEmpty())
            .map(str -> str.trim());
    }

}