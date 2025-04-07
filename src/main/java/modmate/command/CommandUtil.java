package modmate.command;

import java.util.List;
import java.util.StringJoiner;

public class CommandUtil {

    public static String buildSyntax(String commandName, String argument, List<Flag<?>> flags) {
        StringJoiner sj = new StringJoiner(" ");
        sj.add(commandName);

        if (!argument.isEmpty()) {
            sj.add("<" + argument + ">");
        }

        for (Flag<?> flag : flags) {
            String flagSyntax = Flag.FLAG_PREFIX + flag.getName() + (flag.isRequired()
                ? " <" + flag.getExpectedValue() + ">"
                : " [<" + flag.getExpectedValue() + ">]");
            sj.add(flagSyntax);
        }

        return sj.toString();
    }

    public static String buildSyntax(String commandName, String argument) {
        return buildSyntax(commandName, argument, List.of());
    }

    public static String buildSyntax(String commandName) {
        return buildSyntax(commandName, "");
    }
}
