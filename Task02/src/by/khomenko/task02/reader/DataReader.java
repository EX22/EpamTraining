package by.khomenko.task02.reader;

import by.khomenko.task02.exception.DataReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reading data from file.
 */
public class DataReader {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER = LogManager.getLogger(DataReader.class);

    /**
     * @param path file path, locates a file in a file system.
     * @return list of strings read from file.
     * @throws DataReaderException if there is no such file in this path.
     */
    public List<String> readData(final Path path) throws DataReaderException {

        List<String> listOfLines;

        try (Stream<String> lineStream = Files.lines(path)) {

            listOfLines = lineStream.collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            String message = "The source for list is not found! ";
            LOGGER.error(message, e);
            throw new DataReaderException(message, e);
        } catch (IOException e) {
            String message = "During the source for list reading "
                    + "an exception occurred";
            LOGGER.error(message, e);
            throw new DataReaderException(message, e);
        }
        return listOfLines;
    }

}
