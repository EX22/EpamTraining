package by.khomenko.training.task01.parser;

import by.khomenko.training.task01.exception.LineSyntaxException;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.*;

public class SimpleJSONParserTest {

    @Test
    public void testParseLine() throws LineSyntaxException {
        SimpleJSONParser parser = new SimpleJSONParser();
        String line = "{ \"point\" : { \"x\" : 1.0, \"y\" : 2.0, \"z\" : 3.0} ," +
                " \"height\" : 9.0 , \"radius\" : 3.0 , \"plane\" :  \"x\" }";
        Map<String, Object> parsedLine = parser.parseLine(line);

        //TODO To write proper assertEquals method here.
    }
}