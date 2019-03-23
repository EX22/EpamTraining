package by.khomenko.training.task03.comparator;

import by.khomenko.training.task03.entity.TextComponent;

import java.util.Comparator;

public class WordComparator implements Comparator<TextComponent> {
    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        if (o1 != null && o2 != null){

            return Integer.compare(o1.getValue().length(), o2.getValue().length());
        }
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        } else {
            return 1;
        }
    }
}
