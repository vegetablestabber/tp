package modmate.mod.attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import modmate.timetable.Semester;

/**
 * Represents various attributes of a mod, including faculty, semester
 * availability, unit count, grading status, prerequisites, and workload.
 */
public class ModAttributes {

    private final Optional<Faculty> facultyOpt;
    private final List<Semester> availableSemesters;
    private final Optional<Double> unitsOpt;
    private final Optional<Boolean> isGradedOpt;
    private final Optional<String> workloadOpt;

    /**
     * Constructs a ModAttributes object with the given attributes.
     *
     * @param facultyOpt  The faculty offering the mod.
     * @param semesters   A list indicating the semesters in which the mod is
     *                    available.
     * @param unitsOpt    The number of units the mod is worth.
     * @param isGradedOpt Whether the mod is graded or pass/fail.
     * @param workloadOpt The expected weekly workload for the mod.
     */
    public ModAttributes(Optional<Faculty> facultyOpt, List<Semester> semesters,
            Optional<Double> unitsOpt, Optional<Boolean> isGradedOpt, Optional<String> workloadOpt) {
        this.facultyOpt = facultyOpt;
        this.availableSemesters = semesters;
        this.workloadOpt = workloadOpt;
        this.unitsOpt = unitsOpt;
        this.isGradedOpt = isGradedOpt;
    }

    public ModAttributes(Faculty faculty, List<Semester> semesters,
            double units, boolean isGraded, Optional<String> workload) {
        this(Optional.of(faculty), semesters, Optional.of(units), Optional.of(isGraded), workload);
    }

    @Override
    public String toString() {
        return "Faculty: " + getUserFriendlyInfo(facultyOpt)
                + "\nAvailability: " + listAvailableSemesters()
                + "\nUnits: " + getUserFriendlyInfo(unitsOpt)
                + "\nGraded: " + getUserFriendlyInfo(isGradedOpt)
                + "\nWorkload: " + getUserFriendlyInfo(workloadOpt);
    }

    private String getUserFriendlyInfo(Optional<?> opt) {
        return opt.map(value -> value.toString())
                .orElse("No information provided");
    }

    /**
     * Gets a comma-separated string of the semesters in which the mod is available.
     *
     * @return A string listing the available semesters.
     */
    public String listAvailableSemesters() {
        assert (availableSemesters != null && !availableSemesters.isEmpty());

        StringJoiner sj = new StringJoiner(", ");
        availableSemesters.forEach(semester -> sj.add(semester.toString()));

        return sj.toString();
    }

    /**
     * Checks if at least one attribute is present.
     *
     * @return true if at least one attribute is present, false otherwise.
     */
    public boolean hasAtLeastOneAttribute() {
        return facultyOpt.map(f -> true).orElse(false)
                || !availableSemesters.isEmpty()
                || unitsOpt.map(u -> true).orElse(false)
                || isGradedOpt.map(g -> true).orElse(false)
                || workloadOpt.map(w -> true).orElse(false);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof ModAttributes other) {
            List<Boolean> conditions = new ArrayList<>();

            doOptionalsMatch(this.facultyOpt, other.facultyOpt)
                    .ifPresent(result -> conditions.add(result));

            conditions.add(this.doSemestersMatch(other.availableSemesters));

            doOptionalsMatch(this.unitsOpt, other.unitsOpt)
                    .ifPresent(result -> conditions.add(result));

            doOptionalsMatch(this.isGradedOpt, other.isGradedOpt)
                    .ifPresent(result -> conditions.add(result));

            boolean result = conditions.stream()
                    .allMatch(condition -> condition);

            return result;
        }

        return false;
    }

    /**
     * Gets the faculty offering the mod.
     *
     * @return The faculty of the mod.
     */
    public Optional<Faculty> getFacultyOpt() {
        return facultyOpt;
    }

    /**
     * Gets the number of units the mod is worth.
     *
     * @return The number of units.
     */
    public Optional<Double> getUnitsOpt() {
        return unitsOpt;
    }

    private static <T> Optional<Boolean> doOptionalsMatch(Optional<T> firstOpt, Optional<T> secondOpt) {
        return firstOpt.flatMap(firstValue -> secondOpt.map(secondValue -> firstValue.equals(secondValue)));
    }

    private boolean doSemestersMatch(List<Semester> otherSemesters) {
        return availableSemesters.containsAll(otherSemesters)
                || otherSemesters.containsAll(availableSemesters);
    }

}
