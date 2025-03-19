package modmate.mod.attribute;

/**
 * Represents the availability of a mod in different semesters or special terms.
 */
public enum Semester {
    SEMESTER_1("Semester 1"),
    SEMESTER_2("Semester 2"),
    SPECIAL_TERM_1("Special Term 1"),
    SPECIAL_TERM_2("Special Term 2");

    private final String label;

    private Semester(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
