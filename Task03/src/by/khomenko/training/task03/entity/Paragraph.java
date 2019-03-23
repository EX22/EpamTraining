package by.khomenko.training.task03.entity;

import java.util.List;
import java.util.StringJoiner;

public class Paragraph extends TextComposite {

    public Paragraph(final List<TextComponent> list) {
        super(list);
    }

    @Override
    public String getValue() {

        StringJoiner result = new StringJoiner(" ", "    ", "\n");
        for (TextComponent textComponent : list) {
            result.add(textComponent.getValue());
        }

        return result.toString();
    }

}
