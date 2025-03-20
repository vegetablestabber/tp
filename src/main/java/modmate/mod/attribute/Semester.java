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

    public static Semester fromInt(int value) throws IllegalArgumentException {
        return switch (value) {
            case 1 -> Semester.SEMESTER_1;
            case 2 -> Semester.SEMESTER_2;
            case 3 -> Semester.SPECIAL_TERM_1;
            case 4 -> Semester.SPECIAL_TERM_2;
            default -> throw new IllegalArgumentException(
                    "Unknown semester for value '" + value + "'");
        };
    }

}
