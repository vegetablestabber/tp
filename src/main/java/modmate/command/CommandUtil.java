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

        String upper = query.toUpperCase();

        CondensedMod condensed = NUSModsAPI.condensedMods.get(upper);
        if (condensed == null) {
            return Optional.empty();
        }
        
        return NUSModsAPI.fetchModuleByCode(condensed.getCode());
    }
}
