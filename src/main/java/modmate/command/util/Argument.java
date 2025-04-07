package modmate.command.util;

import java.util.Optional;

public class Argument<T> {

    protected final String name;
    private Optional<T> value;
    private final String description;
    private final boolean isRequired;

    public Argument(String name, Optional<T> value, String description, boolean isRequired) {
        this.name = name;
        this.value = value;
        this.description = (isRequired ? "" : "Optional. ") + description;
        this.isRequired = isRequired;
    }

    public Argument(String name, T value, String description, boolean isRequired) {
        this(name, Optional.ofNullable(value), description, isRequired);
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getName() {
        return "<" + name + ">";
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
        return (isRequired ? "Optional. " : "") + description;
    }

}
