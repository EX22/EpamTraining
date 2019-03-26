package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Text;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Parsing text in appropriate format.
 */
public class TextParser extends CommonParser {

    /**
     * Instantiate the next parser's instance.
     */
    public TextParser() {
        setNextCommonParser(new ParagraphParser());
    }

    /**
     * @param line String value of line that should be parsed.
     * @return list of text components, text's instances.
     */
    @Override
    public List<TextComponent> parseIt(final String line) {

        List<TextComponent> paragraphsList
                = getNextCommonParser().parseIt(line);
        Text text = new Text(paragraphsList);
        List<TextComponent> textList = new ArrayList<>();
        textList.add(text);

        return textList;

    }
}
