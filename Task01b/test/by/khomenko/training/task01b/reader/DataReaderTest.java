package by.khomenko.training.task01b.reader;

import by.khomenko.training.task01b.exception.DataReaderException;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.testng.Assert.*;

public class DataReaderTest {

    //Relative path should be different if test runs standalone.
    //In this case the path should be "..\\out\\production\\Task01b\\data\\DataReaderTestFile.txt";

    private static final String FILE_PATH = ".\\out\\production\\Task01b\\data\\DataReaderTestFile.txt";
    private DataReader dataReader = new DataReader();

    @Test
    public void testReadData() throws DataReaderException {
        Path path = Paths.get(FILE_PATH);
        List<String> readLines = dataReader.readData(path);
        assertFalse(readLines.isEmpty());
    }
}