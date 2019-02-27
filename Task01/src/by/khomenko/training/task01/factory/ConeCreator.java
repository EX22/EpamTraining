package by.khomenko.training.task01.factory;

import by.khomenko.training.task01.entity.Cone;
import by.khomenko.training.task01.entity.DefaultShape;
import by.khomenko.training.task01.entity.Vector;
import by.khomenko.training.task01.validator.ShapeDataValidator;

import java.util.Map;

public class ConeCreator extends ShapeCreator {
    @Override
    public DefaultShape createShape(Map<String, Object> parameters) {
        //TODO Find out what to do with unchecked cast SonarLint's hint.
        ShapeDataValidator shapeDataValidator = new ShapeDataValidator();
        if (shapeDataValidator.validateCone(parameters)) {
            return new Cone(createVector((Map<String, Object>) parameters.get("baseCenter")),
                    (Double) parameters.get("height"),
                    (Double) parameters.get("radius"),
                    createVector((Map<String, Object>) parameters.get("direction")));
        }

        return null;
    }

    private Vector createVector(Map<String, Object> vectorData) {
        return new Vector((Double) vectorData.get("x"), (Double) vectorData.get("y"), (Double) vectorData.get("z"));
    }
}
