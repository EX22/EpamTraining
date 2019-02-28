package by.khomenko.training.task01b.logic;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.DefaultShape;
import by.khomenko.training.task01b.exception.ShapeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Shape's type detection.
 */
public class ShapeType {

    private static final Logger LOGGER = LogManager.getLogger(ShapeType.class);

    /**
     * @param shape instance of shape/
     * @return boolean true if passed object instance of Cone,
     * otherwise return false.
     * @throws ShapeException if passed parameter is null.
     */
    public boolean shapeTypeDetector(final DefaultShape shape)
            throws ShapeException {

        if (shape == null) {
            String message = "The object passed for type detection is null.";
            LOGGER.warn(message);
            throw new ShapeException(message);
        }

        return shape instanceof Cone;
    }
}

