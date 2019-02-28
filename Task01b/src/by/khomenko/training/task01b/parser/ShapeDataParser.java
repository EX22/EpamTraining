package by.khomenko.training.task01b.parser;

import by.khomenko.training.task01b.exception.LineSyntaxException;
import by.khomenko.training.task01b.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Parsing data in appropriate format.
 */
public class ShapeDataParser {

    private static final Logger LOGGER
            = LogManager.getLogger(ShapeDataParser.class);

    /**
     * @param readList list of strings.
     * @return list of maps each of those suppose to contain parameters
     * for cone creation.
     * @throws ValidationException if passed list is null or empty.
     */
    public List<Map<String, Object>> parseData(final List<String> readList)
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
