package exception;

public class NotEnoughInventoryException extends Exception {
    public NotEnoughInventoryException(String message) {
        super(message);
    }
}
