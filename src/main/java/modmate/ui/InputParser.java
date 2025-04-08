package modmate.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modmate.command.util.Flag;

public class InputParser {

    private static final String REGEX = "^(?<command>\\S+)(?:\\s+(?<argument>(?:(?!"
        + Flag.FLAG_PREFIX + ")\\S+(?:\\s+(?!" + Flag.FLAG_PREFIX
        + ")\\S+)*)))?(?<flags>(?:\\s+" + Flag.FLAG_PREFIX + "\\S+(?:\\s+\\S+)*)*)$";

    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public static Input parse(String inputString) throws IllegalArgumentException {
        if (inputString.isEmpty()) {
            throw new IllegalArgumentException("Input error: Input cannot be empty");
        }

        Matcher matcher = PATTERN.matcher(inputString);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Input error: Input does not match the required format");
        }

        // Extract command and argument
        String command = matcher.group("command");
        String argument = Optional.ofNullable(matcher.group("argument"))
            .map(str -> str.trim())
            .orElse("");

        // Extract flags and parameters
        String flagsSubstring = Optional.ofNullable(matcher.group("flags"))
            .map(str -> str.trim())
            .orElse("");

        if (flagsSubstring.isEmpty()) {
            return new Input(command, argument);
        }

        Map<String, String> flagsMap = parseFlags(flagsSubstring);
        return new Input(command, argument, flagsMap);
    }

    private static Map<String, String> parseFlags(String flagsSubstring) {
        Map<String, String> flagsMap = new HashMap<>();
        int endIndex = 0;

        while (endIndex < flagsSubstring.length()) {
            int flagStart = flagsSubstring.indexOf(Flag.FLAG_PREFIX, endIndex);
            if (flagStart == -1) {
                break;
            }

            flagStart += Flag.FLAG_PREFIX.length();
            int nextFlagStart = flagsSubstring.indexOf(Flag.FLAG_PREFIX, flagStart);

            String flagPair = (nextFlagStart == -1
                ? flagsSubstring.substring(flagStart)
                : flagsSubstring.substring(flagStart, nextFlagStart)).trim();

            int spaceIndex = flagPair.indexOf(' ');

            String flagName = (spaceIndex == -1)
                ? flagPair
                : flagPair.substring(0, spaceIndex);


            if (flagName.isEmpty()) {
                throw new IllegalArgumentException("Flag name is empty");
            }

            String flagValue = (spaceIndex == -1)
                ? ""
                : flagPair.substring(spaceIndex + 1).trim();

            flagsMap.put(flagName, flagValue);

            endIndex = nextFlagStart == -1 ? flagsSubstring.length() : nextFlagStart;
        }

        return flagsMap;
    }
}
