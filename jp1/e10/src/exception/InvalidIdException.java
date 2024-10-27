package exception;

public class InvalidIdException extends ValidationException {
    public InvalidIdException() {
        super();
    }
    public InvalidIdException(String message) {
        super(message);
    }
    public InvalidIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
