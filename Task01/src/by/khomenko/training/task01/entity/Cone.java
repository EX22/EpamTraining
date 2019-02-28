package by.khomenko.training.task01.entity;

import java.util.Objects;

/**
 *
 * Entity for storing cone shape with parameters.
 */
public class Cone implements DefaultShape {

    private Vector baseCenter;
    private double height;
    private double radius;
    private Vector direction;

    public Cone(final Vector baseCenter, final double height,
                final double radius, final Vector direction) {
        this.baseCenter = baseCenter;
        this.height = height;
        this.radius = radius;
        this.direction = direction;
    }

    /**
     * @return instance of Vector for cone's base center.
     */
    public Vector getBaseCenter() {
        return baseCenter;
    }

    /**
     * @param baseCenter instance of Vector for cone's base center.
     */
    public void setBaseCenter(final Vector baseCenter) {
        this.baseCenter = baseCenter;
    }

    /**
     * @return double value of cone's height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height parameter of cone's height
     */
    public void setHeight(final double height) {
        this.height = height;
    }

    /**
     * @return double value of cone's base radius.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius parameter of cone's radius.
     */
    public void setRadius(final double radius) {
        this.radius = radius;
    }

    /**
     * @return instance of Vector for cone's direction
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * @param direction instance of Vector for cone's direction
     */
    public void setDirection(final Vector direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cone)) {
            return false;
        }
        Cone cone = (Cone) o;
        return Double.compare(cone.getHeight(), getHeight()) == 0
                && Double.compare(cone.getRadius(), getRadius()) == 0
                && getBaseCenter().equals(cone.getBaseCenter())
                && getDirection().equals(cone.getDirection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBaseCenter(), getHeight(), getRadius(),
                getDirection());
    }

    @Override
    public String toString() {
        return "Cone{"
                + "baseCenter=" + baseCenter
                + ", height=" + height
                + ", radius=" + radius
                + ", direction=" + direction
                + '}';
    }
}


