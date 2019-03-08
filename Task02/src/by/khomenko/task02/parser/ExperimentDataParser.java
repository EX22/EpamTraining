package by.khomenko.task02.parser;

import by.khomenko.task02.exception.LineSyntaxException;
import by.khomenko.task02.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Parsing data in appropriate format.
 */
public class ExperimentDataParser {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ExperimentDataParser.class);

    /**
     * @param readList list of strings.
     * @return list of maps each of those suppose to contain parameters
     * for ports creation.
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

