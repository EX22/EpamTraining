package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Text;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class TextParser extends CommonParser {

    @Override
    public List<TextComponent> parseIt(String line) {
        if (nextCommonParser instanceof ParagraphParser) {
            List<TextComponent> textComponentList = nextCommonParser.parseIt(line);
            Text text = new Text(textComponentList);
            List<TextComponent> textList = new ArrayList<>();
            textList.add(text);
            return textList;
        }

        return new ArrayList<>();
    }
}
