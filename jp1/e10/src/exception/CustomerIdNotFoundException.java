package exception;

public class CustomerIdNotFoundException extends ValidationException {
    public CustomerIdNotFoundException() {
        super();
    }
    public CustomerIdNotFoundException(String message) {
        super(message);
    }
    public CustomerIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
