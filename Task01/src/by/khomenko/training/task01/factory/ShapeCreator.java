package by.khomenko.training.task01.factory;

import by.khomenko.training.task01.entity.DefaultShape;

import java.util.Map;


public abstract class ShapeCreator {

    abstract DefaultShape createShape(Map<String, Object> parameters);

}
