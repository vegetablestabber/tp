package modmate.mod.attribute;

import java.util.List;

import modmate.mod.Mod;

/**
 * Represents various attributes of a mod, including faculty, semester availability,
 * unit count, grading status, prerequisites, and workload.
 */
public class ModAttributes {
    private Faculty faculty;
    private List<SemesterAvailability> semesterAvailability;
    private int units;
    private boolean isGraded;
    private List<Mod> prerequisites;
    private WeeklyWorkload workload;

    /**
     * Constructs a ModAttributes object with the given attributes.
     *
     * @param faculty               The faculty offering the mod.
     * @param semesterAvailability  A list indicating the semesters in which the mod is available.
     * @param units                 The number of units the mod is worth.
     * @param isGraded              Whether the mod is graded or pass/fail.
     * @param prerequisites         A list of prerequisite mods required for enrollment.
     * @param workload              The expected weekly workload for the mod.
     */
    public ModAttributes(Faculty faculty, List<SemesterAvailability> semesterAvailability,
                         int units, boolean isGraded, List<Mod> prerequisites, WeeklyWorkload workload) {
        this.faculty = faculty;
        this.semesterAvailability = semesterAvailability;
        this.units = units;
        this.isGraded = isGraded;
        this.prerequisites = prerequisites;
        this.workload = workload;
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
     * Sets the faculty offering the mod.
     *
     * @param faculty The faculty to set.
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    /**
     * Gets the list of semesters in which the mod is available.
     *
     * @return A list of SemesterAvailability objects.
     */
    public List<SemesterAvailability> getSemesterAvailability() {
        return semesterAvailability;
    }

    /**
     * Sets the list of semesters in which the mod is available.
     *
     * @param semesterAvailability A list of SemesterAvailability objects.
     */
    public void setSemesterAvailability(List<SemesterAvailability> semesterAvailability) {
        this.semesterAvailability = semesterAvailability;
    }

    /**
     * Gets the number of units the mod is worth.
     *
     * @return The number of units.
     */
    public int getUnits() {
        return units;
    }

    /**
     * Sets the number of units the mod is worth.
     *
     * @param units The number of units to set.
     */
    public void setUnits(int units) {
        this.units = units;
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
     * Sets whether the mod is graded.
     *
     * @param isGraded true if the mod should be graded, false otherwise.
     */
    public void setGraded(boolean isGraded) {
        this.isGraded = isGraded;
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
     * Sets the list of prerequisite mods required for enrollment.
     *
     * @param prerequisites A list of prerequisite mods.
     */
    public void setPrerequisites(List<Mod> prerequisites) {
        this.prerequisites = prerequisites;
    }

    /**
     * Gets the expected weekly workload for the mod.
     *
     * @return The WeeklyWorkload object representing the workload.
     */
    public WeeklyWorkload getWorkload() {
        return workload;
    }

    /**
     * Sets the expected weekly workload for the mod.
     *
     * @param workload The WeeklyWorkload object to set.
     */
    public void setWorkload(WeeklyWorkload workload) {
        this.workload = workload;
    }
}
