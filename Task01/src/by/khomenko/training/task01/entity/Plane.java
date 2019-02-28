package by.khomenko.training.task01.entity;

import java.util.Objects;

public class Plane {

    public static final Plane XZ_PLANE = new Plane(new Vector(0.0, 0.0, 0.0),
            new Vector(0.0, 1.0, 0.0));
    public static final Plane ZY_PLANE = new Plane(new Vector(0.0, 0.0, 0.0),
            new Vector(1.0, 0.0, 0.0));
    public static final Plane XY_PLANE = new Plane(new Vector(0.0, 0.0, 0.0),
            new Vector(0.0, 0.0, 1.0));

    private Vector point;
    private Vector normal;

    public Plane(Vector point, Vector normal) {
        this.point = point;
        this.normal = normal;
    }

    public Vector getPoint() {
        return point;
    }

    public void setPoint(Vector point) {
        this.point = point;
    }

    public Vector getNormal() {
        return normal;
    }

    public void setNormal(Vector normal) {
        this.normal = normal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plane)) {
            return false;
        }
        Plane that = (Plane) o;
        return getPoint().equals(that.getPoint())
                && getNormal().equals(that.getNormal());
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
