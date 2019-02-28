package by.khomenko.training.task01b.logic;

import by.khomenko.training.task01b.entity.Cone;

/**
 * Shape's surface area calculation.
 */
public class ShapeSurfaceArea {

    /**
     * Calculates cone's surface area.
     *
     * @param cone instance of shape.
     * @return double value passed cone's surface area.
     */
    public double calcConeSurfaceArea(final Cone cone) {

        double radius = cone.getRadius();
        double height = cone.getHeight();

        return ((Math.PI * radius * Math.hypot(radius, height))
                + (Math.PI * Math.pow(radius, 2)));
    }
}
