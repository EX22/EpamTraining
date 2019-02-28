package by.khomenko.training.task01.logic;

import by.khomenko.training.task01.entity.Cone;

public class ShapeSurfaceArea {

    public double calcConeSurfaceArea(Cone cone) {

        double radius = cone.getRadius();
        double height = cone.getHeight();

        return ((Math.PI * radius * Math.hypot(radius, height))
                + (Math.PI * Math.pow(radius, 2)));
    }
}
