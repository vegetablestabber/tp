package modmate.mod.attribute;


/**
 * Enum representing the different semesters in an academic year.
 * Each semester has a corresponding label.
 *
 * <p>Semesters include:</p>
 * <ul>
 *   <li>SEMESTER_1 - "Semester 1"</li>
 *   <li>SEMESTER_2 - "Semester 2"</li>
 *   <li>SPECIAL_TERM_1 - "Special Term 1"</li>
 *   <li>SPECIAL_TERM_2 - "Special Term 2"</li>
 * </ul>
 */
public enum Semester {

    SEMESTER_1("Semester 1"),
    SEMESTER_2("Semester 2"),
    SPECIAL_TERM_1("Special Term 1"),
    SPECIAL_TERM_2("Special Term 2");

    /**
     * The label of the semester.
     */
    private final String label;

    private Semester(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    /**
     * Converts an integer value to its corresponding Semester enum.
     *
     * @param value the integer value representing the semester
     * @return the Semester enum corresponding to the given integer value
     * @throws IllegalArgumentException if the integer value does not correspond to any known semester
     */
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
