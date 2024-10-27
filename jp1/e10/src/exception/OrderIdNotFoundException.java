package exception;

public class OrderIdNotFoundException extends ValidationException {
    public OrderIdNotFoundException() {
        super();
    }
    public OrderIdNotFoundException(String message) {
        super(message);
    }
    public OrderIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
