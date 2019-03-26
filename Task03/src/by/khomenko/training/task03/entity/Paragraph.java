package by.khomenko.training.task03.entity;

import java.util.List;

/**
 * Represents a paragraph's instance.
 */
public class Paragraph extends TextComposite {

    /**
     * @param list text's components list to instantiate a class instance.
     */
    public Paragraph(final List<TextComponent> list) {
        super(list, " ", "    ", "\n");
    }

}
