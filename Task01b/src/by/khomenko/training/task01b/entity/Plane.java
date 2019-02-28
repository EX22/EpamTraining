package by.khomenko.training.task01b.entity;

import java.util.Objects;

/**
 * Entity for storing coordinate planes with parameters.
 */
public class Plane {

    public static final Plane XZ_PLANE = new Plane(new Vector(0.0, 0.0, 0.0),
            new Vector(0.0, 1.0, 0.0));
    public static final Plane ZY_PLANE = new Plane(new Vector(0.0, 0.0, 0.0),
            new Vector(1.0, 0.0, 0.0));
    public static final Plane XY_PLANE = new Plane(new Vector(0.0, 0.0, 0.0),
            new Vector(0.0, 0.0, 1.0));

    private Vector point;
    private Vector normal;

    public Plane(final Vector pointVal, final Vector normalVal) {
        this.point = pointVal;
        this.normal = normalVal;
    }

    /**
     * @return instance of Vector for coordinate plane.
     */
    public Vector getPoint() {
        return point;
    }

    /**
     * @param pointVal instance of Vector for coordinate plane.
     */
    public void setPoint(final Vector pointVal) {
        this.point = pointVal;
    }

    /**
     * @return instance of Vector for coordinate plane's normal.
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * @param normalVal instance of Vector for coordinate plane's normal.
     */
    public void setNormal(final Vector normalVal) {
        this.normal = normalVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plane)) {
            return false;
        }
        Plane plane = (Plane) o;
        return getPoint().equals(plane.getPoint())
                && getNormal().equals(plane.getNormal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPoint(), getNormal());
    }

    @Override
    public String toString() {
        return "Plane{"
                + "point=" + point
                + ", normal=" + normal
                + '}';
    }
}
