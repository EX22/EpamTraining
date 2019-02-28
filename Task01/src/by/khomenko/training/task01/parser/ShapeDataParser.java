package by.khomenko.training.task01.parser;

import by.khomenko.training.task01.exception.LineSyntaxException;
import by.khomenko.training.task01.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShapeDataParser {

    private static final Logger LOGGER
            = LogManager.getLogger(ShapeDataParser.class);

    public List<Map<String, Object>> parseData(List<String> readList)
            throws ValidationException {

        if (readList == null) {
            String message = "List is null";
            LOGGER.warn(message);
            throw new ValidationException(message);

        } else if (readList.isEmpty()) {
            String message = "List is empty";
            LOGGER.warn(message);
            throw new ValidationException(message);
        }

        List<Map<String, Object>> parsedList = new ArrayList<>();
        SimpleJSONParser simpleJSONParser = new SimpleJSONParser();

        for (String string : readList) {
            try {
                parsedList.add(simpleJSONParser.parseLine(string));
            } catch (LineSyntaxException e) {
                String message = "Incorrect data in line : " + string;
                LOGGER.warn(message, e);
            }

        }

        return parsedList;
    }

}
