package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Text;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class TextParser extends CommonParser {

    public TextParser() {
        nextCommonParser = new ParagraphParser();
    }

    @Override
    public List<TextComponent> parseIt(String line) {



            List<TextComponent> paragraphsList = nextCommonParser.parseIt(line);
            Text text = new Text(paragraphsList);
            List<TextComponent> textList = new ArrayList<>();
            textList.add(text);

            return textList;

    }
}
