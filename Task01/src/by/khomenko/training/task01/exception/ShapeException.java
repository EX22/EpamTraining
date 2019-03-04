package by.khomenko.training.task01.exception;

/**
 * Exception thrown when some operation with Shape object has failed.
 */
public class ShapeException extends Exception {

    /**
     * Default constructor.
     */
    public ShapeException() {

    }

    /**
     * @param message passed to parent constructor.
     */
    public ShapeException(String message) {
        super(message);
    }

    /**
     * @param cause passed to parent constructor.
     */
    public ShapeException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message passed to parent constructor.
     * @param cause   passed to parent constructor.
     */
    public ShapeException(String message, Throwable cause) {
        super(message, cause);
    }
}
