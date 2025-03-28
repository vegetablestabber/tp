package modmate.mod.attribute;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

import modmate.mod.Mod;
import modmate.timetable.Semester;

/**
 * Represents various attributes of a mod, including faculty, semester
 * availability, unit count, grading status, prerequisites, and workload.
 */
public class ModAttributes {

    private final Faculty faculty;
    private final List<Semester> availableSemesters;
    private final double units;
    private final boolean isGraded;
    private final List<Mod> prerequisites;
    private final Optional<WeeklyWorkload> workload;

    /**
     * Constructs a ModAttributes object with the given attributes.
     *
     * @param faculty       The faculty offering the mod.
     * @param semesters     A list indicating the semesters in which the mod is
     *                      available.
     * @param units         The number of units the mod is worth.
     * @param isGraded      Whether the mod is graded or pass/fail.
     * @param prerequisites A list of prerequisite mods required for enrollment.
     * @param workload      The expected weekly workload for the mod.
     */
    public ModAttributes(Faculty faculty, List<Semester> semesters, double units,
            boolean isGraded, List<Mod> prerequisites, Optional<WeeklyWorkload> workload) {
        this.faculty = faculty;
        this.availableSemesters = semesters;
        this.units = units;
        this.isGraded = isGraded;
        this.prerequisites = prerequisites;
        this.workload = workload;
    }

    public ModAttributes(Faculty faculty, List<Semester> semesters, double units,
        boolean isGraded, List<Mod> prerequisites, WeeklyWorkload workload) {
            this(faculty, semesters, units, isGraded, prerequisites, Optional.of(workload));
        }

    /**
     * Gets the faculty offering the mod.
     *
     * @return The faculty of the mod.
     */
    public Faculty getFaculty() {
        return faculty;
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
    public double getUnits() {
        return units;
    }

    /**
     * Checks if the mod is graded.
     *
     * @return true if the mod is graded, false otherwise.
     */
    public boolean isGraded() {
        return isGraded;
    }

    /**
     * Gets the list of prerequisite mods required for enrollment.
     *
     * @return A list of prerequisite mods.
     */
    public List<Mod> getPrerequisites() {
        return prerequisites;
    }

    /**
     * Gets the expected weekly workload for the mod.
     *
     * @return The WeeklyWorkload object representing the workload.
     */
    public Optional<WeeklyWorkload> getWorkload() {
        return workload;
    }

}
