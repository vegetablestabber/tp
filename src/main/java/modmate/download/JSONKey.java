package modmate.download;

public enum JSONKey {

    NAME("name"),
    CODE("moduleCode"),
    DESCRIPTION("description"),

    FACULTY("faculty"),
    AVAILABLE_SEMESTERS("semesterData"),
    UNITS("moduleCredit"),
    // TODO: Need one for prerequisites
    IS_GRADED("gradingBasisDescription"),
    WORKLOAD("workload");

    private final String text;

    private JSONKey(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

}
