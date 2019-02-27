package by.khomenko.training.task01b.factory;

import by.khomenko.training.task01b.entity.DefaultShape;

import java.util.Map;


public abstract class ShapeCreator {

    abstract DefaultShape createShape(Map<String, Object> parameters);

}
