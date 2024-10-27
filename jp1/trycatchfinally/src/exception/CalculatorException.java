package exception;

public class CalculatorException extends Exception {

    public CalculatorException () {}
    public CalculatorException (Throwable cause) {
        super(cause);
    }
    public CalculatorException (String message, Throwable cause) {
        super(message, cause);
    }
}
