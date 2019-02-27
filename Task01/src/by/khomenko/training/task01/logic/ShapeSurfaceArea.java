package by.khomenko.training.task01.logic;

import by.khomenko.training.task01.entity.Cone;

public class ShapeSurfaceArea {
    /*
    Площадь полной поверхности конуса находится по формуле:
    Sкон = Pi*R*l + Pi*(R)sqr,
    где R – радиус основания, l – длина образующей.
     */

    public double calcConeSurfaceArea(Cone cone) {

        double radius = cone.getRadius();
        double height = cone.getHeight();

        return ((Math.PI * radius * Math.hypot(radius, height)) + (Math.PI * Math.pow(radius, 2)));
    }
}
