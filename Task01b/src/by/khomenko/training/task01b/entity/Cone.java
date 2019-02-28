package by.khomenko.training.task01b.entity;

import java.util.Objects;

/**
 * Entity for storing cone shape with parameters.
 */
public class Cone extends DefaultShape {

    private Vector baseCenter;
    private double height;
    private double radius;
    private Vector direction;
    private String name;

    public Cone(final Vector baseCenterVal, final double heightVal,
                final double radiusVal, final Vector directionVal,
                String nameVal) {
        this.baseCenter = baseCenterVal;
        this.height = heightVal;
        this.radius = radiusVal;
        this.direction = directionVal;
        if (name == null) {
            name = "";
        }
        this.name = nameVal;
    }

    /**
     * @return instance of Vector for cone's base center.
     */
    public Vector getBaseCenter() {
        return baseCenter;
    }

    /**
     * @param baseCenterVal instance of Vector for cone's base center.
     */
    public void setBaseCenter(final Vector baseCenterVal) {
        this.baseCenter = baseCenterVal;
        setChanged();
    }

    /**
     * @return double value of cone's height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param heightVal parameter of cone's height
     */
    public void setHeight(final double heightVal) {
        this.height = heightVal;
        setChanged();
    }

    /**
     * @return double value of cone's base radius.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radiusVal parameter of cone's radius.
     */
    public void setRadius(final double radiusVal) {
        this.radius = radiusVal;
        setChanged();
    }

    /**
     * @return instance of Vector for cone's direction
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * @param directionVal instance of Vector for cone's direction
     */
    public void setDirection(final Vector directionVal) {
        this.direction = directionVal;
        setChanged();
    }

    /**
     * @return String cone's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param nameVal cone's name.
     */
    public void setName(final String nameVal) {
        this.name = nameVal;
    }

    /**
     * @param o instance that we check equality to current instance.
     * @return true if passed parameter instance of Cone.
     */
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
                && getDirection().equals(cone.getDirection())
                && getName().equals(cone.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBaseCenter(), getHeight(), getRadius(),
                getDirection(), getName());
    }

    @Override
    public String toString() {
        return "Cone{"
                + "baseCenter=" + baseCenter
                + ", height=" + height
                + ", radius=" + radius
                + ", direction=" + direction
                + ", name='" + name + '\''
                + "} " + super.toString();
    }
}


