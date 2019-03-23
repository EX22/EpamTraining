package by.khomenko.training.task03.comparator;

import by.khomenko.training.task03.entity.TextComponent;


import java.util.Comparator;

public class SymbolAppearanceComparator implements Comparator<TextComponent> {

    char toFind;

    public SymbolAppearanceComparator(final char toFindVal) {
        this.toFind = toFindVal;
    }

    @Override
    public int compare(TextComponent o1, TextComponent o2) {
        if (o1 != null && o2 != null) {

            return Integer.compare(o1.getSymbolAppearanceCount(toFind),
                    o2.getSymbolAppearanceCount(toFind));

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
