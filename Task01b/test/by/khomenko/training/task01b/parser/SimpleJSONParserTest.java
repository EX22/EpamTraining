package by.khomenko.training.task01b.parser;

import by.khomenko.training.task01b.exception.LineSyntaxException;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.*;

/**
 * Tests SimpleJSONParser method.
 */
public class SimpleJSONParserTest {

    /**
     * SimpleJSONParser's parseLine method test.
     *
     * @throws LineSyntaxException if start and end symbols are not those that
     * passed in parameters.
     */
    @Test
    public void testParseLine() throws LineSyntaxException {
        SimpleJSONParser parser = new SimpleJSONParser();
        String line = "{ \"point\" : { \"x\" : 1.0, \"y\" : 2.0, \"z\" : 3.0} ," +
                " \"height\" : 9.0 , \"radius\" : 3.0 , \"plane\" :  \"x\" }";
        Map<String, Object> parsedLine = parser.parseLine(line);
        assertEquals(parsedLine.get("radius"), 3.0);

    }
}