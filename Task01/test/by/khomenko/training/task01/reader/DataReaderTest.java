package by.khomenko.training.task01.reader;

import by.khomenko.training.task01.exception.DataReaderException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Tests reading data from file.
 */
public class DataReaderTest {

    private DataReader dataReader = new DataReader();

    /**
     * @param filePath
     * @throws DataReaderException
     */
    @Parameters("readerTestFilePath")
    @Test
    public void testReadData(String filePath) throws DataReaderException {
        Path path = Paths.get(filePath);
        List<String> readLines = dataReader.readData(path);
        assertFalse(readLines.isEmpty());
    }
}