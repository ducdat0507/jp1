package exception;

public class NotEnoughInventoryException extends Exception {
    public NotEnoughInventoryException() {
        super();
    }
    public NotEnoughInventoryException(String message) {
        super(message);
    }
    public NotEnoughInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
