package modmate.exception;

public class ApiException extends Exception {
    public ApiException(String message) {
        super("NUSMods API error: " + message);
    }
}
