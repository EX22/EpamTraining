package by.khomenko.training.task01b.comparator;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.DefaultShape;

import java.util.Comparator;

/**
 * Compares two shape's instances by first Y coordinate of base center.
 */
public class ShapeByFirstYCoordinate implements Comparator<DefaultShape> {
    @Override
    public int compare(final DefaultShape o1, final DefaultShape o2) {
        if (o1 != null && o2 != null) {
            return Double.compare(((Cone) o1).getBaseCenter().getY(),
                    ((Cone) o2).getBaseCenter().getY());
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
