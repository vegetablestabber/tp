package modmate.course.attribute;

import java.util.List;

import modmate.course.Course;

public class CourseAttributes {
    private Faculty faculty;
    private List<SemesterAvailability> semesterAvailability;
    private int units;

    private boolean isGraded;
    private List<Course> prerequisites;
    private WeeklyWorkload workload;

    public CourseAttributes(Faculty faculty, List<SemesterAvailability> semesterAvailability,
            int units, boolean isGraded, List<Course> prerequisites, WeeklyWorkload workload) {
        this.faculty = faculty;
        this.semesterAvailability = semesterAvailability;
        this.units = units;

        this.isGraded = isGraded;
        this.prerequisites = prerequisites;
        this.workload = workload;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<SemesterAvailability> getSemesterAvailability() {
        return semesterAvailability;
    }

    public void setSemesterAvailability(List<SemesterAvailability> semesterAvailability) {
        this.semesterAvailability = semesterAvailability;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public boolean isGraded() {
        return isGraded;
    }

    public void setGraded(boolean isGraded) {
        this.isGraded = isGraded;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public WeeklyWorkload getWorkload() {
        return workload;
    }

    public void setWorkload(WeeklyWorkload workload) {
        this.workload = workload;
    }
}
