package by.khomenko.training.task03.entity;

import java.util.List;
import java.util.StringJoiner;

public class Text extends TextComposite {

    public Text(final List<TextComponent> list) {
        super(list);
    }

    //TODO The method below almost identical here, in Paragraph, Sentence,
    // Lexeme. Consider put it in TextComposite.

    @Override
    public String getValue() {

        StringJoiner result = new StringJoiner("");
        for (TextComponent textComponent : list) {
            result.add(textComponent.getValue());
        }

        return result.toString();
    }

}
