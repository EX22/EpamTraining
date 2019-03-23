package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Paragraph;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class ParagraphParser extends CommonParser {

    private static final String REG_EXP_PARAGRAPH = "\\n\\t";

    public ParagraphParser() {
        nextCommonParser = new SentenceParser();
    }

    @Override
    public List<TextComponent> parseIt(String line) {



            String[] paragraphStringsArray = splitter(line);
            List<TextComponent> paragraphList = new ArrayList<>();

            for (String paragraphString : paragraphStringsArray) {
                List<TextComponent> sentencesList = nextCommonParser.parseIt(paragraphString);
                Paragraph paragraph = new Paragraph(sentencesList);
                paragraphList.add(paragraph);
            }
            return paragraphList;

    }

    private String[] splitter(String string){
        return string.split(REG_EXP_PARAGRAPH);
    }
}
