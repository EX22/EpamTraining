package by.khomenko.training.task03.entity;

import java.util.List;
import java.util.StringJoiner;

public class Word extends TextComposite {

    public Word(final List<TextComponent> listVal) {
        super(listVal);
    }

    @Override
    public String getValue() {

        StringJoiner result = new StringJoiner("");
        for (TextComponent textComponent : list) {
            result.add(textComponent.getValue());
        }

        return result.toString();
    }

}
