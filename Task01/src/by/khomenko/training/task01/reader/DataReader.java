package by.khomenko.training.task01.reader;

import by.khomenko.training.task01.exception.DataReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DataReader {

    private static final Logger LOGGER = LogManager.getLogger(DataReader.class);

    public List<String> readData(Path path) throws DataReaderException {

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

