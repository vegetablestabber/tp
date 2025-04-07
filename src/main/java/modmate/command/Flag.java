package modmate.command;

import java.util.Optional;

public class Flag<T> {

    private final String name;
    private Optional<T> value;
    private final String description;
    private final boolean isRequired;
    private final String expectedValue;

    public static final String FLAG_PREFIX = "--";

    public Flag(String name, Optional<T> value, String description, boolean isRequired, String expectedValue) {
        this.name = name;
        this.value = value;
        this.description = (isRequired ? "" : "Optional. ") + description;
        this.isRequired = isRequired;
        this.expectedValue = expectedValue;
    }

    public Flag(String name, T value, String description, boolean isRequired, String expectedValue) {
        this(name, Optional.ofNullable(value), description, isRequired, expectedValue);
    }

    public String getName() {
        return name;
    }

    public Optional<T> getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = Optional.ofNullable(value);
    }

    public void setValue(Optional<T> value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

}
