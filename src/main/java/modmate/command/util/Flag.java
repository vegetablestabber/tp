package modmate.command.util;

import java.util.Optional;

public class Flag<T> extends Argument<T> {

    private final String expectedValue;

    public static final String FLAG_PREFIX = "--";

    public Flag(String name, Optional<T> value, String description, boolean isRequired, String expectedValue) {
        super(name, value, description, isRequired);
        this.expectedValue = expectedValue;
    }

    public Flag(String name, T value, String description, boolean isRequired, String expectedValue) {
        this(name, Optional.ofNullable(value), description, isRequired, expectedValue);
    }

    @Override
    public String toString() {
        return getName() + " <" + expectedValue + ">";
    }

    @Override
    public String getName() {
        return FLAG_PREFIX + name;
    }

}
