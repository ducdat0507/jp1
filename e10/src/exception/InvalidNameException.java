package exception;

public class InvalidNameException extends ValidationException {
    public InvalidNameException() {
        super();
    }
    public InvalidNameException(String message) {
        super(message);
    }
    public InvalidNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
