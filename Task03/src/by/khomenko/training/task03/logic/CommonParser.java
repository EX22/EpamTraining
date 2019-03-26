package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.TextComponent;

import java.util.List;

/**
 * Defines behavior for all its descendants in Chain of Responsibility.
 */
public abstract class CommonParser {

    /**
     * Defines which particular parser should be following.
     */
    private CommonParser nextCommonParser;

    /**
     * @param nextCommonParserVal set next parser.
     */
    protected void setNextCommonParser(final CommonParser nextCommonParserVal) {
        this.nextCommonParser = nextCommonParserVal;
    }

    /**
     * @return next parser.
     */
    protected CommonParser getNextCommonParser() {
        return nextCommonParser;
    }

    /**
     * @param line String value of line that should be parsed.
     * @return list of text's components.
     */
    public abstract List<TextComponent> parseIt(String line);
}
