package by.khomenko.training.task01b.logic;

import by.khomenko.training.task01b.entity.Cone;

/**
 * Contains methods to calculate shapes volume.
 */
public class ShapeVolume {

    /**
     * Having received the Cone object calculates it's volume.
     *
     * @param cone shape's object instance of Cone.
     * @return double, cone's volume.
     */
    public double calcConeVolume(final Cone cone) {

        return calcConeVolume(cone.getRadius(), cone.getHeight());
    }

    /**
     * Having received Cone's object parameters calculates it's volume.
     *
     * @param radius double, cone's radius.
     * @param height double, cone's height.
     * @return double, cone's volume.
     */
    public double calcConeVolume(final double radius, final double height) {

        return ((1.0 / 3.0) * Math.PI * Math.pow(radius, 2) * height);
    }

    /**
     * Calculates frustum's, which resulted initial cone split up
     * by cutting plane, volume.
     *
     * @param downBaseRadius double, initial cone's base radius.
     * @param upBaseRadius   double, frustum's up base radius.
     * @param frustumHeight  double, frustum's height.
     * @return double, frustum's volume.
     */
    public double calcFrustumVolume(final double downBaseRadius,
                                    final double upBaseRadius,
                                    final double frustumHeight) {

        return ((1.0 / 3.0) * Math.PI * frustumHeight * (Math.pow(downBaseRadius, 2)
                + downBaseRadius * upBaseRadius + Math.pow(upBaseRadius, 2)));
    }

}
