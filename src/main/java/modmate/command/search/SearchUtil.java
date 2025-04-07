package modmate.command.search;

import modmate.command.util.Argument;
import modmate.command.util.Flag;
import modmate.download.nusmods.NUSModsAPI;
import modmate.mod.CondensedMod;
import modmate.mod.attribute.Faculty;
import modmate.mod.attribute.ModAttributes;
import modmate.timetable.Semester;
import modmate.ui.ProgressBar;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchUtil {

    public static ModAttributes createAttributes(
        Flag<Faculty> facultyFlag,
        Flag<List<Semester>> semestersFlag,
        Flag<Double> unitsFlag,
        Flag<Boolean> gradedFlag
    ) {
        return new ModAttributes(
            facultyFlag.getValue(),
            semestersFlag.getValue()
                .orElse(Collections.emptyList()),
            unitsFlag.getValue(),
            gradedFlag.getValue(),
            Optional.empty()
        );
    }

    public static Stream<CondensedMod> filterByAttributes(
        Stream<CondensedMod> modStream,
        ModAttributes searchAttrs
    ) {
        return modStream.filter(mod -> {
            Optional<ModAttributes> modAttrs =
                NUSModsAPI.fetchModuleByCode(mod.getCode())
                    .map(fetchedMod -> fetchedMod.getAttributes());

            return modAttrs.map(attrs -> attrs.equals(searchAttrs))
                .orElse(false);
        });
    }

    public static Stream<CondensedMod> filterByIdentifier(
        Stream<CondensedMod> modStream,
        Argument<String> identifierArg
    ) {
        return identifierArg.getValue().map(id -> {
            String lowerCaseId = id.toLowerCase();
            return modStream.filter(mod -> {
                String lowerCaseModCode = mod.getCode().toLowerCase();
                String lowerCaseModName = mod.getName().toLowerCase();
                return lowerCaseModCode.contains(lowerCaseId)
                    || lowerCaseModName.contains(lowerCaseId);
            });
        }).orElseGet(() -> modStream);
    }

    public static List<CondensedMod> collectWithProgress(
        Stream<? extends CondensedMod> modStream,
        int totalModules
    ) {
        final int[] processedCount = { 0 };
        return modStream.peek(mod -> {
            synchronized (processedCount) {
                processedCount[0]++;
                ProgressBar.print(processedCount[0], totalModules);
            }
        }).collect(Collectors.toList());
    }
}
