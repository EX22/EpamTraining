package by.khomenko.training.task01b.exception;

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
    public ShapeException(final String message) {
        super(message);
    }

    /**
     * @param cause passed to parent constructor.
     */
    public ShapeException(final Throwable cause) {
        super(cause);
    }

    /**
     * @param message passed to parent constructor.
     * @param cause   passed to parent constructor.
     */
    public ShapeException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
