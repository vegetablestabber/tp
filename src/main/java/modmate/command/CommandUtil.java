package modmate.command;

import java.util.Arrays;
import java.util.StringJoiner;

public class CommandUtil {
    public static String concatenate(String... tokens) {
        StringJoiner sj = new StringJoiner(" ");
        Arrays.stream(tokens).forEach(token -> sj.add(token));
        return sj.toString();
    }
}
