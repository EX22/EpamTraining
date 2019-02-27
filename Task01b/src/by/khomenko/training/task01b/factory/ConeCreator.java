package by.khomenko.training.task01b.factory;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.DefaultShape;
import by.khomenko.training.task01b.entity.Vector;
import by.khomenko.training.task01b.validator.ShapeDataValidator;

import java.util.Map;

public class ConeCreator extends ShapeCreator {

    @Override
    public DefaultShape createShape(Map<String, Object> parameters) {

        ShapeDataValidator shapeDataValidator = new ShapeDataValidator();

        if (shapeDataValidator.validateCone(parameters)) {
            return new Cone(createVector((Map<String, Object>) parameters.get("baseCenter")),
                    (Double) parameters.get("height"),
                    (Double) parameters.get("radius"),
                    createVector((Map<String, Object>) parameters.get("direction")),
                    (String) parameters.get("name"));
        }

        return null;

    }

    private Vector createVector(Map<String, Object> vectorData) {

        return new Vector((Double) vectorData.get("x"), (Double) vectorData.get("y"), (Double) vectorData.get("z"));
    }
}
