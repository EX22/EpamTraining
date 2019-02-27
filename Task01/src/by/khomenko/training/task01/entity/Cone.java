package by.khomenko.training.task01.entity;

import java.util.Objects;

public class Cone implements DefaultShape {

    private Vector baseCenter;
    private double height;
    private double radius;
    private Vector direction;

    public Cone(Vector baseCenter, double height, double radius, Vector direction) {
        this.baseCenter = baseCenter;
        this.height = height;
        this.radius = radius;
        this.direction = direction;
    }

    public Vector getBaseCenter() {
        return baseCenter;
    }

    public void setBaseCenter(Vector baseCenter) {
        this.baseCenter = baseCenter;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cone)) return false;
        Cone cone = (Cone) o;
        return Double.compare(cone.getHeight(), getHeight()) == 0 &&
                Double.compare(cone.getRadius(), getRadius()) == 0 &&
                getBaseCenter().equals(cone.getBaseCenter()) &&
                getDirection().equals(cone.getDirection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBaseCenter(), getHeight(), getRadius(), getDirection());
    }

    @Override
    public String toString() {
        return "Cone{" +
                "baseCenter=" + baseCenter +
                ", height=" + height +
                ", radius=" + radius +
                ", direction=" + direction +
                '}';
    }
}


