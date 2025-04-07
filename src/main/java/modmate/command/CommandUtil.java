package modmate.command;

import modmate.download.nusmods.NUSModsAPI;
import modmate.mod.CondensedMod;
import modmate.mod.Mod;
import java.util.Arrays;
import java.util.Optional;
import java.util.StringJoiner;

public class CommandUtil {
    public static String concatenate(String... tokens) {
        StringJoiner sj = new StringJoiner(" ");
        Arrays.stream(tokens).forEach(token -> sj.add(token));
        return sj.toString();
    }
    public static Optional<Mod> modFromCodeOrName(String query) {
        if (query == null || query.isBlank()) {
            return Optional.empty();
        }

        // Mod codes are stored uppercase, so normalize user input
        String upper = query.toUpperCase();

        // Try to get a condensed mod
        CondensedMod condensed = NUSModsAPI.condensedMods.get(upper);
        if (condensed == null) {
            return Optional.empty(); // Mod code does not exist
        }

        // Fetch full module info
        return NUSModsAPI.fetchModuleByCode(condensed.getCode());
    }
}
