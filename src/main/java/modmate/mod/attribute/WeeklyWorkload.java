package modmate.mod.attribute;

/**
 * Represents the weekly workload of a student for various activities, including lectures, tutorials,
 * projects, and preparation.
 */
public class WeeklyWorkload {

    /** The number of hours spent on lectures per week. */
    private final double lectureHours;

    /** The number of hours spent on tutorials per week. */
    private final double tutorialHours;

    /** The number of hours spent on projects per week. */
    private final double projectHours;

    /** The number of hours spent on preparation per week. */
    private final double preparationHours;

    /**
     * Constructs a WeeklyWorkload object with the specified hours for each activity.
     *
     * @param lectureHours The number of hours spent on lectures per week.
     * @param tutorialHours The number of hours spent on tutorials per week.
     * @param projectHours The number of hours spent on projects per week.
     * @param preparationHours The number of hours spent on preparation per week.
     */
    public WeeklyWorkload(double lectureHours, double tutorialHours, double projectHours, double preparationHours) {
        this.lectureHours = lectureHours;
        this.tutorialHours = tutorialHours;
        this.projectHours = projectHours;
        this.preparationHours = preparationHours;
    }

    @Override
    public String toString() {
        return "Lectures: " + this.lectureHours + " hours, Tutorials: " + this.tutorialHours + " hours, "
                + "Projects: " + this.projectHours + " hours, Preparation: " + this.preparationHours + " hours";
    }
}
