package by.khomenko.training.task03.reader;

import by.khomenko.training.task03.exception.DataReaderException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.testng.Assert.*;

public class DataReaderTest {

    private DataReader dataReader = new DataReader();
    /**
     * @param filePath file path, locates a file in a file system.
     * @throws DataReaderException if there is no such file in this path.
     */
    @Parameters("readerTestFilePath")
    @Test
    public void testReadData(String filePath) throws DataReaderException {
        Path path = Paths.get(filePath);
        String readLines = dataReader.readData(path);
        assertFalse(readLines.isEmpty());
    }
    @Test(expectedExceptions = DataReaderException.class)
    public void testReadDataException() throws DataReaderException {
        Path path = Paths.get("");
        dataReader.readData(path);

    }
}