package by.khomenko.training.task01.logic;

import by.khomenko.training.task01.entity.Cone;

public class ShapeVolume {

    public double calcConeVolume(Cone cone) {

        return calcConeVolume(cone.getRadius(), cone.getHeight());
    }

    public double calcConeVolume(double radius, double height) {

        return ((1.0 / 3.0) * Math.PI * Math.pow(radius, 2) * height);
    }

    public double calcFrustumVolume(double downBaseRadius, double upBaseRadius,
                                    double frustumHeight) {

        return ((1.0 / 3.0) * Math.PI * frustumHeight * (Math.pow(downBaseRadius, 2)
                + downBaseRadius * upBaseRadius + Math.pow(upBaseRadius, 2)));
    }

}
