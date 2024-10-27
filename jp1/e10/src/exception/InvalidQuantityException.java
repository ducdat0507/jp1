package exception;

public class InvalidQuantityException extends ValidationException {
    public InvalidQuantityException() {
        super();
    }
    public InvalidQuantityException(String message) {
        super(message);
    }
    public InvalidQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}
