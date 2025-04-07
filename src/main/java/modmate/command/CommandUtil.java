package modmate.command;

import java.util.List;
import java.util.StringJoiner;

import modmate.command.util.Argument;

public class CommandUtil {

    public static String buildSyntax(String commandName, List<Argument<?>> args) {
        StringJoiner sj = new StringJoiner(" ");
        sj.add(commandName);
        args.forEach(arg -> sj.add(arg.toString()));

        return sj.toString();
    }

    public static String buildSyntax(String commandName) {
        return buildSyntax(commandName, List.of());
    }
}
