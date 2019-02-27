package by.khomenko.training.task01b.logic;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.Plane;
import by.khomenko.training.task01b.entity.Vector;

/**
 * Utility class, contains methods perform some geometrical calculations.
 */
public class GeometryUtil {

    public static final double THRESHOLD = 1E-13;

    private GeometryUtil() {
    }

    /**
     * @param point instance of Vector, contains X, Y, Z coordinates.
     * @param plane instance of Plane.
     * @return double value, distance from passed point to passed plane.
     */
    public static double pointToPlaneDistance(Vector point, Plane plane) {
        Vector v = new Vector(point.getX() - plane.getPoint().getX(),
                point.getY() - plane.getPoint().getY(),
                point.getZ() - plane.getPoint().getZ());

        return Math.abs(v.getX() * plane.getNormal().getX() +
                v.getY() * plane.getNormal().getY() +
                v.getZ() * plane.getNormal().getZ());

    }

    /**
     * Checks if the cutting plane parallel the cone's base.
     * @param cone instance of Cone shape
     * @param plane instance of Plane that should be a cone's cutting plane.
     * @return boolean value, true if cutting plane is parallel cone's base, otherwise false.
     */
    public static boolean isParallel(Cone cone, Plane plane) {

        return (1.0 - Math.abs(cone.getDirection().getX() * plane.getNormal().getX() +
                cone.getDirection().getY() * plane.getNormal().getY() +
                cone.getDirection().getZ() * plane.getNormal().getZ())) < THRESHOLD;
    }

    /**
     * @param vector instance of Vector
     * @return double vector's length.
     */
    public static double vectorLength(Vector vector){
        return Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2) + Math.pow(vector.getZ(), 2));
    }

}
