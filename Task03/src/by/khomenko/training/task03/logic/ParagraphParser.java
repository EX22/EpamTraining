package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Paragraph;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Parsing text into paragraphs.
 */
public class ParagraphParser extends CommonParser {

    /**
     * Regular expression for parsing text into paragraphs.
     */
    private static final String REG_EXP_PARAGRAPH = "\\n(\\t|[ ]{4})";

    /**
     * Instantiate the next parser's instance.
     */
    public ParagraphParser() {
        setNextCommonParser(new SentenceParser());
    }

    /**
     * @param line String value of line that should be parsed.
     * @return list of text components, paragraph's instances.
     */
    @Override
    public List<TextComponent> parseIt(final String line) {

        String[] paragraphStringsArray = splitter(line);
        List<TextComponent> paragraphList = new ArrayList<>();

        for (String paragraphString : paragraphStringsArray) {
            List<TextComponent> sentencesList
                    = getNextCommonParser().parseIt(paragraphString);
            Paragraph paragraph = new Paragraph(sentencesList);
            paragraphList.add(paragraph);
        }
        return paragraphList;

    }

    /**
     * @param string line to split.
     * @return line splitted into Strings array.
     */
    private String[] splitter(final String string) {
        return string.split(REG_EXP_PARAGRAPH);
    }
}
