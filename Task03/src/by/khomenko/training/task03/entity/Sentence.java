package by.khomenko.training.task03.entity;

import java.util.List;

/**
 * Represents a sentence's instance.
 */
public class Sentence extends TextComposite {

    /**
     * @param list text's components list to instantiate a class instance.
     */
    public Sentence(final List<TextComponent> list) {
        super(list, " ", "", "");
    }

}
