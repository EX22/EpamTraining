package by.khomenko.training.task03.entity;

import java.util.List;

/**
 * Represents a text's instance.
 */
public class Text extends TextComposite {

    /**
     * @param list text's components list to instantiate a class instance.
     */
    public Text(final List<TextComponent> list) {
        super(list);
    }

}
