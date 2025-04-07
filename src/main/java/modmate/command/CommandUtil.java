package modmate.command;

import modmate.download.nusmods.NUSModsAPI;
import modmate.mod.CondensedMod;
import modmate.mod.Mod;

import java.util.Arrays;
import java.util.Optional;
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

    public static String buildSyntax(String commandName) {
        return buildSyntax(commandName, List.of());
    }
}
