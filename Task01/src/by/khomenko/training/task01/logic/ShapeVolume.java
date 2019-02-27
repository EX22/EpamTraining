package by.khomenko.training.task01.logic;

import by.khomenko.training.task01.entity.Cone;

public class ShapeVolume {
    /*
    The Cone's volume equals to
    V(c) = 1/3*PI*(R)sqr*H,
    where
    R – base' radius, Н – cone' height
     */
    public double calcConeVolume(Cone cone) {

        return calcConeVolume(cone.getRadius(), cone.getHeight());
    }

    public double calcConeVolume(double radius, double height){

        return ((1.0 / 3.0) * Math.PI * Math.pow(radius, 2) * height);
    }

    /*
    The Frustum's volume equals to
    V(f) = 1/3*PI*h*((r1)sqr + r1*r2 + (r2)sqr)
    where
    r1 - frustum' down base radius
    r2 - frustum' up base radius
    h - frustum' height
     */
    //TODO Consider remove this method.
    public double calcFrustumVolume(double downBaseRadius, double upBaseRadius, double frustumHeight) {

        return ((1.0 / 3.0) * Math.PI * frustumHeight * (Math.pow(downBaseRadius, 2)
                + downBaseRadius * upBaseRadius + Math.pow(upBaseRadius, 2)));
    }

    //TODO The method below is needed to be deleted
    public int sum(int a, int b) {
        return (a + b);
    }

}
