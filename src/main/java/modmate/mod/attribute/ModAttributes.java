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
     * @param facultyStrOpt  The faculty offering the mod.
     * @param semesters   A list indicating the semesters in which the mod is
     *                    available.
     * @param unitsStrOpt    The number of units the mod is worth.
     * @param isGradedStrOpt Whether the mod is graded or pass/fail.
     * @param workloadOpt The expected weekly workload for the mod.
     */
    public ModAttributes(Optional<String> facultyStrOpt, List<Semester> semesters,
            Optional<String> unitsStrOpt, Optional<String> isGradedStrOpt, Optional<String> workloadOpt) {
        this.facultyOpt = facultyStrOpt.map(str -> new Faculty(str));

        this.availableSemesters = semesters;

        this.unitsOpt = unitsStrOpt.flatMap(str -> {
            try {
                return Optional.of(Double.valueOf(str));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        });

        this.isGradedOpt = isGradedStrOpt.flatMap(str -> {
            if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false")) {
                return Optional.of(Boolean.valueOf(str));
            }

            return Optional.empty();
        });

        this.workloadOpt = workloadOpt;
    }

    public ModAttributes(String facultyStr, List<Semester> semesters,
            String unitsStr, String isGradedStr, Optional<String> workload) {
        this(Optional.of(facultyStr), semesters,
            Optional.of(unitsStr), Optional.of(isGradedStr), workload);
    }

    @Override
    public String toString() {
        return "Faculty: " + getUserFriendlyInfo(getFacultyOpt())
                + "\nAvailability: " + listAvailableSemesters()
                + "\nUnits: " + getUserFriendlyInfo(getUnitsOpt())
                + "\nGraded: " + getUserFriendlyInfo(isGraded())
                + "\nWorkload: " + getUserFriendlyInfo(getWorkloadOpt());
    }

    private String getUserFriendlyInfo(Optional<?> opt) {
        return opt.map(value -> value.toString())
                .orElse("No information provided");
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

            this.doesFacultyMatch(other.facultyOpt)
                .ifPresent(result -> conditions.add(result));

            conditions.add(this.doSemestersMatch(other.availableSemesters));

            this.doUnitsMatch(other.unitsOpt)
                .ifPresent(result -> conditions.add(result));

            this.doesGradabilityMatch(other.isGradedOpt)
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

    public List<Semester> getSemesters() {
        return availableSemesters;
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
     * Gets the number of units the mod is worth.
     *
     * @return The number of units.
     */
    public Optional<Double> getUnitsOpt() {
        return unitsOpt;
    }

    /**
     * Checks if the mod is graded.
     *
     * @return true if the mod is graded, false otherwise.
     */
    public Optional<Boolean> isGraded() {
        return isGradedOpt;
    }

    /**
     * Gets the expected weekly workload for the mod.
     *
     * @return The WeeklyWorkload object representing the workload.
     */
    public Optional<String> getWorkloadOpt() {
        return workloadOpt;
    }

    private Optional<Boolean> doesFacultyMatch(Faculty otherFaculty) {
        return facultyOpt.map(faculty -> faculty.equals(otherFaculty));
    }

    private Optional<Boolean> doesFacultyMatch(Optional<Faculty> otherFacultyOpt) {
        return otherFacultyOpt.flatMap(otherFaculty -> doesFacultyMatch(otherFaculty));
    }

    private boolean doSemestersMatch(List<Semester> otherSemesters) {
        return availableSemesters.containsAll(otherSemesters)
                || otherSemesters.containsAll(availableSemesters);
    }

    private Optional<Boolean> doUnitsMatch(double otherUnits) {
        return unitsOpt.map(units -> units.equals(otherUnits));
    }

    private Optional<Boolean> doUnitsMatch(Optional<Double> otherUnitsOpt) {
        return otherUnitsOpt.flatMap(otherUnits -> doUnitsMatch(otherUnits));
    }

    private Optional<Boolean> doesGradabilityMatch(boolean otherIsGraded) {
        return isGradedOpt.map(isGraded -> isGraded == otherIsGraded);
    }

    private Optional<Boolean> doesGradabilityMatch(Optional<Boolean> otherIsGradedOpt) {
        return otherIsGradedOpt.flatMap(otherIsGraded -> doesGradabilityMatch(otherIsGraded));
    }

}
