package by.khomenko.training.task01b.factory;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.DefaultShape;
import by.khomenko.training.task01b.entity.Vector;
import by.khomenko.training.task01b.validator.ShapeDataValidator;

import java.util.Map;

/**
 * Cone's creation, implements one of Factory Method's pattern options.
 */
public class ConeCreator extends ShapeCreator {

    /**
     * @param parameters contains valid parameters for shape creation.
     * @return instance of shape.
     */
    @Override
    public DefaultShape createShape(final Map<String, Object> parameters) {

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

    /**
     * @param vectorData contains valid data for vector creation.
     * @return instance of Vector.
     */
    private Vector createVector(final Map<String, Object> vectorData) {

        return new Vector((Double) vectorData.get("x"),
                (Double) vectorData.get("y"), (Double) vectorData.get("z"));
    }
}
