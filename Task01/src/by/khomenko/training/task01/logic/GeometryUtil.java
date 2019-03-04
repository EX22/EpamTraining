package by.khomenko.training.task01.logic;

import by.khomenko.training.task01.entity.Cone;
import by.khomenko.training.task01.entity.Plane;
import by.khomenko.training.task01.entity.Vector;

public class GeometryUtil {

    public static final double THRESHOLD = 1E-13;

    private GeometryUtil() {
    }

    public static double pointToPlaneDistance(Vector point, Plane plane) {
        Vector v = new Vector(point.getX() - plane.getPoint().getX(),
                point.getY() - plane.getPoint().getY(),
                point.getZ() - plane.getPoint().getZ());

        return Math.abs(v.getX() * plane.getNormal().getX()
                + v.getY() * plane.getNormal().getY()
                + v.getZ() * plane.getNormal().getZ());

    }

    //The method below checks if the cutting plane parallel the cone's base.
    public static boolean isParallel(Cone cone, Plane plane) {

        return (1.0 - Math.abs(cone.getDirection().getX() * plane.getNormal().getX()
                + cone.getDirection().getY() * plane.getNormal().getY()
                + cone.getDirection().getZ() * plane.getNormal().getZ())) < THRESHOLD;


    }

}
