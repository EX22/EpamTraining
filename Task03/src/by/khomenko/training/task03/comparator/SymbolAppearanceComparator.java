package by.khomenko.training.task03.comparator;

import by.khomenko.training.task03.entity.TextComponent;


import java.util.Comparator;

/**
 * Compares two text's elements according to required symbol appears in each
 * text element.
 * According to this particular task's requirements compares two lexeme by
 * required symbol amount.
 */

public class SymbolAppearanceComparator implements Comparator<TextComponent> {

    /**
     * Symbol which we looking for in text elements.
     */
    private char toFind;

    /**
     * @param toFindVal symbol which we will looking for in text elements.
     */
    public SymbolAppearanceComparator(final char toFindVal) {
        this.toFind = toFindVal;
    }

    /**
     * @param o1 passed to method TextComponent's instance.
     * @param o2 passed to method TextComponent's instance.
     * @return a negative integer, zero, or a positive integer as the first
     * argument is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(final TextComponent o1, final TextComponent o2) {
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
