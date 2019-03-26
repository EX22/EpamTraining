package by.khomenko.training.task03.comparator;

import by.khomenko.training.task03.entity.TextComponent;

import java.util.Comparator;

/**
 * Compares two text's elements according to their letters order in alphabet.
 */
public class AlphabeticOrderComparator implements Comparator<TextComponent> {

    /**
     * @param o1 passed to method TextComponent's instance.
     * @param o2 passed to method TextComponent's instance.
     * @return a negative integer, zero, or a positive integer as the first
     * argument is less than, equal to, or greater than the second.
     */
    @Override
    public int compare(final TextComponent o1, final TextComponent o2) {
        if (o1 != null && o2 != null) {

            return o1.getTextValue().compareTo(o2.getTextValue());

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
