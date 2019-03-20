package by.khomenko.training.task03.exception;

/**
 * Exception thrown during the file reading.
 */
public class DataReaderException extends Exception {
    /**
     * Default constructor.
     */
    public DataReaderException() {

    }

    /**
     * @param message passed to parent constructor.
     */
    public DataReaderException(final String message) {
        super(message);
    }

    /**
     * @param cause passed to parent constructor.
     */
    public DataReaderException(final Throwable cause) {
        super(cause);
    }

    /**
     * @param message passed to parent constructor.
     * @param cause   passed to parent constructor.
     */
    public DataReaderException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
