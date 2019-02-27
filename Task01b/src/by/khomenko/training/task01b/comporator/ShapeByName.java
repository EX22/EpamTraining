package by.khomenko.training.task01b.comporator;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.DefaultShape;

import java.util.Comparator;

public class ShapeByName implements Comparator<DefaultShape> {
    @Override
    public int compare(DefaultShape o1, DefaultShape o2) {
        if (o1 != null && o2 != null) {
            return ((Cone)o1).getName().compareTo(((Cone)o2).getName());
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
