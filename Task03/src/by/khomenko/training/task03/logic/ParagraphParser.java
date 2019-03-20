package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Paragraph;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class ParagraphParser extends CommonParser {

    private static final String REG_EXP_PARAGRAPH = "\\n\\t";

    @Override
    public List<TextComponent> parseIt(String line) {
        if (nextCommonParser instanceof SentenceParser) {
            String[] paragraphStrings = splitter(line);
            List<TextComponent> paragraphList = new ArrayList<>();
            for (String paragraphString : paragraphStrings) {
                List<TextComponent> sentencesList = nextCommonParser.parseIt(paragraphString);
                Paragraph paragraph = new Paragraph(sentencesList);
                paragraphList.add(paragraph);
            }
            return paragraphList;
        }
        return new ArrayList<>();
    }

    private String[] splitter(String string){
        return string.split(REG_EXP_PARAGRAPH);
    }
}
