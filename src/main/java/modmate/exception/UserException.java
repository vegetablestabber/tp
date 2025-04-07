package modmate.exception;

public class UserException extends Exception {
    public UserException(String message) {
        super("User error: " + message);
    }
}
