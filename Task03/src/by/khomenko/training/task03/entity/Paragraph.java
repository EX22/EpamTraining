package by.khomenko.training.task03.entity;

import java.util.List;

public class Paragraph extends TextComposite {

    public Paragraph(final List<TextComponent> list) {
        super(list, " ", "    ", "\n");
    }

}
