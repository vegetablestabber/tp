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
    public WeeklyWorkload getWorkload() {
        return workload;
    }

    public String getAvailabilityToString() {
        assert (semesterAvailability != null && !semesterAvailability.isEmpty());
        StringBuilder availability = new StringBuilder();
        for (SemesterAvailability semester : semesterAvailability) {
            switch (semester) {
            case SEMESTER_1:
                availability.append("Semester 1, ");
                break;
            case SEMESTER_2:
                availability.append("Semester 2, ");
                break;
            case SPECIAL_TERM_1:
                availability.append("Special Term 1, ");
                break;
            case SPECIAL_TERM_2:
                availability.append("Special Term 2, ");
                break;
            default:
                break;
            }
        }
        return availability.substring(0, availability.length() - 2);
    }
}
