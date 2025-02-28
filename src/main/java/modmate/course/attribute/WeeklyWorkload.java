package modmate.course.attribute;

public class WeeklyWorkload {
    private final double lectureHours;
    private final double tutorialHours;
    private final double projectHours;
    private final double preparationHours;

    public WeeklyWorkload(double lectureHours, double tutorialHours, double projectHours, double preparationHours) {
        this.lectureHours = lectureHours;
        this.tutorialHours = tutorialHours;
        this.projectHours = projectHours;
        this.preparationHours = preparationHours;
    }

    public double getTotalWorkload() {
        return this.lectureHours + this.tutorialHours + this.projectHours + this.preparationHours;
    }
}
