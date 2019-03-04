package by.khomenko.training.task01.exception;

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
    public DataReaderException(String message) {
        super(message);
    }

    /**
     * @param cause passed to parent constructor.
     */
    public DataReaderException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message passed to parent constructor.
     * @param cause   passed to parent constructor.
     */
    public DataReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
