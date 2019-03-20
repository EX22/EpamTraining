package by.khomenko.training.task03.entity;

import java.util.List;
import java.util.StringJoiner;

public class Text extends TextComposite {

    public Text(List<TextComponent> list) {
        super(list);
    }

    @Override
    public String getValue() {
        StringJoiner result = new StringJoiner(" ");
        for (TextComponent textComponent : list) {
            result.add(textComponent.getValue());
        }
        return toString();
    }

}
