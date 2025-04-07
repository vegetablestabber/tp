package modmate.command.search;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import modmate.command.util.Argument;
import modmate.command.util.Flag;
import modmate.mod.attribute.Faculty;
import modmate.timetable.Semester;

public class SearchFlagBuilder {

    public static Argument<String> createIdentifierArg(String idInput) {
        return new Argument<>(
            "search query",
            idInput,
            "Mod code or name.",
            true
        );
    }

    public static Flag<Faculty> createFacultyFlag(Optional<String> facultyInput) {
        return new Flag<>(
            "faculty",
            facultyInput.map(Faculty::new),
            "Filter by faculty.",
            false,
            "value"
        );
    }

    public static Flag<List<Semester>> createSemestersFlag(Optional<String> semestersInput) {
        return new Flag<>(
            "semesters",
            semestersInput.map(str -> Arrays.stream(str.split("\\s+"))
                .map(Integer::valueOf)
                .map(Semester::fromInt)
                .toList()),
            "Filter by semesters (space-separated).",
            false,
            "value"
        );
    }

    public static Flag<Double> createUnitsFlag(Optional<String> unitsInput) {
        return new Flag<>(
            "units",
            unitsInput.flatMap(str -> {
                try {
                    return Optional.of(Double.valueOf(str));
                } catch (NumberFormatException e) {
                    return Optional.empty();
                }
            }),
            "Filter by modular credits.",
            false,
            "value"
        );
    }

    public static Flag<Boolean> createGradedFlag(Optional<String> gradedInput) {
        return new Flag<>(
            "graded",
            gradedInput.filter(str -> str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false"))
                .map(Boolean::valueOf),
            "Filter by grading type.",
            false,
            "value"
        );
    }

}
