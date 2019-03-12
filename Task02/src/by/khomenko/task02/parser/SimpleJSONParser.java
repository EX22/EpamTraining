package by.khomenko.task02.parser;

import by.khomenko.task02.exception.LineSyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains JSON parsing methods.
 */
public class SimpleJSONParser {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SimpleJSONParser.class);

    /**
     * Parse JSON.
     *
     * @param line - JSON string.
     * @return map with parameters (could be nested maps).
     * @throws LineSyntaxException if the closing quote is not found.
     */
    public Map<String, Object> parseLine(String line)
            throws LineSyntaxException {

        Map<String, Object> result = new HashMap<>();
        List<Object> paramValues = null;
        boolean isInArray = false;

        line = line.trim();

        if (line.charAt(0) == '[') {
            isInArray = true;
            line = unwrap(line, '[', ']');
            paramValues = new ArrayList<>();
            result.put("values", paramValues);
        } else {
            line = unwrap(line, '{', '}');
        }

        while (line.length() > 0) {

            String paramName = "";
            Object paramValue;
            line = line.trim();

            if (!isInArray) {
                int closingQuoteIndex = findClosingQuote(line);
                int colonIndex = line.indexOf(':', closingQuoteIndex);
                if (colonIndex <= 0) {
                    String message = "Colon not found at: ";
                    LOGGER.warn(message);
                    throw new LineSyntaxException(message + line);
                }
                paramName = unwrap(line.substring(0, colonIndex),
                        '"', '"');
                line = line.substring(colonIndex + 1).trim();
            }

            int valueEndIndex;

            if (line.charAt(0) == '{') {
                int closingBraceIndex = findParamClosingBrace(line,
                        '{', '}');
                paramValue
                        = parseLine(line.substring(0, closingBraceIndex + 1));
                valueEndIndex = line.indexOf(',', closingBraceIndex);

                if (valueEndIndex < 0) {
                    valueEndIndex = closingBraceIndex;
                }

            } else if (line.charAt(0) == '[') {
                int closingBraceIndex = findParamClosingBrace(line,
                        '[', ']');
                paramValue = parseLine(line.substring(0, closingBraceIndex
                        + 1)).get("values");
                valueEndIndex = line.indexOf(',', closingBraceIndex);

                if (valueEndIndex < 0) {
                    valueEndIndex = closingBraceIndex;
                }

            } else if (line.charAt(0) == '"') {
                int closingQuoteIndex = findClosingQuote(line);
                paramValue = unwrap(line.substring(0, closingQuoteIndex + 1),
                        '"', '"');
                valueEndIndex = line.indexOf(',', closingQuoteIndex);

                if (valueEndIndex < 0) {
                    valueEndIndex = closingQuoteIndex;
                }

            } else {
                valueEndIndex = line.indexOf(',');
                if (valueEndIndex < 0) {
                    valueEndIndex = line.length();
                }
                paramValue
                        = parseValue(line.substring(0, valueEndIndex).trim());
            }

            if (!isInArray) {
                result.put(paramName, paramValue);
            } else {
                paramValues.add(paramValue);
            }

            if (valueEndIndex >= line.length()) {
                break;
            }

            line = line.substring(valueEndIndex + 1);
        }
        return result;
    }

    /**
     * Trim whitespace and remove start and end symbols of the string,
     * throw exception if they are not equal to
     * start and end parameters.
     *
     * @param s     - string.
     * @param start - start character.
     * @param end   - end character.
     * @return string without start and end symbols.
     * @throws LineSyntaxException if start and end symbols are not those that
     *                             passed in parameters.
     */
    private String unwrap(String s, char start, char end)
            throws LineSyntaxException {

        s = s.trim();

        if (s.charAt(0) != start || s.charAt(s.length() - 1) != end) {
            String message = "Unwrapping exception at: ";
            LOGGER.warn(message);
            throw new LineSyntaxException(message + s);
        }

        return s.substring(1, s.length() - 1).trim();
    }

    /**
     * Find closing quote. Skip escaped quotes.
     *
     * @param s string
     * @return index of the closing quote.
     * @throws LineSyntaxException if the closing quote is not found.
     */
    private int findClosingQuote(String s) throws LineSyntaxException {

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '"' && s.charAt(i) != '\\') {
                return i;
            }
        }

        String message = "Closing quote not found at: ";
        LOGGER.warn(message);
        throw new LineSyntaxException(message + s);
    }

    /**
     * Find closing brace that corresponds the opening.
     *
     * @param s            string.
     * @param openBrace    open brace symbol.
     * @param closingBrace corresponding closing brace symbol.
     * @return index of the closing brace.
     * @throws LineSyntaxException in case if corresponding closing brace is
     *                             not found.
     */
    private int findParamClosingBrace(String s,
                                      char openBrace, char closingBrace)
            throws LineSyntaxException {

        int openBraceCounter = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == openBrace) {
                openBraceCounter++;
            } else if (s.charAt(i) == closingBrace) {
                if (openBraceCounter == 0) {
                    return i;
                } else {
                    openBraceCounter--;
                }
                if (openBraceCounter < 0) {
                    String message
                            = "More closing braces than open braces at: ";
                    LOGGER.warn(message);
                    throw new LineSyntaxException(message + s);
                }

            }
        }

        String message = "Closing brace not found at: ";
        LOGGER.warn(message);
        throw new LineSyntaxException(message + s);
    }

    /**
     * Parse numeric value.
     *
     * @param paramValue string.
     * @return Integer if there is no decimal point or Double.
     * @throws LineSyntaxException if NumberFormatException occurred during
     *                             the parsing.
     */
    private Object parseValue(String paramValue) throws LineSyntaxException {

        try {
            if ("null".equals(paramValue)) {
                return null;
            } else if ("true".equals(paramValue)
                    || "false".equals(paramValue)) {
                return Boolean.valueOf(paramValue);
            } else if (paramValue.indexOf('.') < 0
                    && paramValue.indexOf('e') < 0
                    && paramValue.indexOf('E') < 0) {
                return Integer.valueOf(paramValue);
            } else {
                return Double.valueOf(paramValue);
            }
        } catch (NumberFormatException e) {
            String message = "Number format exception parsing: ";
            LOGGER.warn(message);
            throw new LineSyntaxException(message + paramValue, e);
        }
    }

}
