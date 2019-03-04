package by.khomenko.training.task01.logic;

import by.khomenko.training.task01.entity.Cone;
import by.khomenko.training.task01.entity.Plane;
import by.khomenko.training.task01.exception.ShapeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Shape's base position in coordinate plane.
 */
public class ShapeBasePosition {

    private static final Logger LOGGER
            = LogManager.getLogger(ShapeBasePosition.class);

    public boolean isConeBaseOnPosition(Cone cone) throws ShapeException {

        if (cone == null) {
            String message = "The Cone's object passed for base position"
                    + " detection is null.";
            LOGGER.warn(message);
            throw new ShapeException(message);
        }

        return ((GeometryUtil.isParallel(cone, Plane.XZ_PLANE)
                && GeometryUtil.pointToPlaneDistance(cone.getBaseCenter(),
                Plane.XZ_PLANE) < GeometryUtil.THRESHOLD)
                || (GeometryUtil.isParallel(cone, Plane.ZY_PLANE)
                && GeometryUtil.pointToPlaneDistance(cone.getBaseCenter(),
                Plane.ZY_PLANE) < GeometryUtil.THRESHOLD)
                || (GeometryUtil.isParallel(cone, Plane.XY_PLANE)
                && GeometryUtil.pointToPlaneDistance(cone.getBaseCenter(),
                Plane.XY_PLANE) < GeometryUtil.THRESHOLD));
    }

}
