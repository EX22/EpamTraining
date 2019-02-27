package by.khomenko.training.task01b.entity;

import java.util.Objects;

public class Cone extends DefaultShape {

    private Vector baseCenter;
    private double height;
    private double radius;
    private Vector direction;
    private String name;

    public Cone(Vector baseCenter, double height, double radius, Vector direction, String name) {
        this.baseCenter = baseCenter;
        this.height = height;
        this.radius = radius;
        this.direction = direction;
        if (name == null){
            name = "";
        }
        this.name = name;
    }

    public Vector getBaseCenter() {
        return baseCenter;
    }

    public void setBaseCenter(Vector baseCenter) {
        this.baseCenter = baseCenter;
        setChanged();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        setChanged();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
        setChanged();
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
        setChanged();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cone)) return false;
        Cone cone = (Cone) o;
        return Double.compare(cone.getHeight(), getHeight()) == 0 &&
                Double.compare(cone.getRadius(), getRadius()) == 0 &&
                getBaseCenter().equals(cone.getBaseCenter()) &&
                getDirection().equals(cone.getDirection()) &&
                getName().equals(cone.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBaseCenter(), getHeight(), getRadius(), getDirection(), getName());
    }

    @Override
    public String toString() {
        return "Cone{" +
                "baseCenter=" + baseCenter +
                ", height=" + height +
                ", radius=" + radius +
                ", direction=" + direction +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }
}


