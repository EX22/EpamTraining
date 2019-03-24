package by.khomenko.training.task03.entity;

import java.util.List;

public class Sentence extends TextComposite {

    public Sentence(final List<TextComponent> list) {
        super(list, " ", "", "");
    }

}
