package exception;

public class ProductIdNotFoundException extends ValidationException {
    public ProductIdNotFoundException() {
        super();
    }
    public ProductIdNotFoundException(String message) {
        super(message);
    }
    public ProductIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
