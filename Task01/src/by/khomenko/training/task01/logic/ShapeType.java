package by.khomenko.training.task01.logic;

import by.khomenko.training.task01.entity.Cone;
import by.khomenko.training.task01.entity.DefaultShape;
import by.khomenko.training.task01.exception.ShapeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShapeType {

    private static final Logger LOGGER = LogManager.getLogger(ShapeType.class);

    public boolean shapeTypeDetector(DefaultShape shape) throws ShapeException {

        if (shape == null) {
            String message = "The object passed for type detection is null.";
            LOGGER.warn(message);
            throw new ShapeException(message);
        }

        return shape instanceof Cone;
    }
}

