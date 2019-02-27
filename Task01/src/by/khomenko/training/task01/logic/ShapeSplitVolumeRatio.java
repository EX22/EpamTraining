package by.khomenko.training.task01.logic;

import by.khomenko.training.task01.entity.*;
import by.khomenko.training.task01.exception.ShapeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Ratio of resulted shapes volume after initial shape splitted up by coordinate plane.
 */
public class ShapeSplitVolumeRatio {

    private static final Logger LOGGER = LogManager.getLogger(ShapeSplitVolumeRatio.class);

    public double calcConeSplitVolumeRation(Cone cone, Plane cuttingPlane) throws ShapeException {

        if (cone == null) {
            String message = "The Cone's object passed for cone split volume ration calculation is null.";
            LOGGER.warn(message);
            throw new ShapeException(message);
        }
        if (cuttingPlane == null) {
            String message = "The Plane's object passed for cone split volume ration calculation is null.";
            LOGGER.warn(message);
            throw new ShapeException(message);
        }
        if (Math.abs(cone.getHeight()) < GeometryUtil.THRESHOLD){
            String message = "The Cone's height is zero";
            LOGGER.warn(message);
            throw new ShapeException(message);
        }

        double shapeVolumeRatio = 0.0;

        // Cutting plane should be parallel to cone's base.
        if (GeometryUtil.isParallel(cone, cuttingPlane)) {

            double frustumHeight = GeometryUtil.pointToPlaneDistance(cone.getBaseCenter(), cuttingPlane);

            Vector apex = new Vector(cone.getBaseCenter().getX() + cone.getHeight() * cone.getDirection().getX(),
                    cone.getBaseCenter().getY() + cone.getHeight() * cone.getDirection().getY(),
                    cone.getBaseCenter().getZ() + cone.getHeight() * cone.getDirection().getZ());

            double upperConeHeight = GeometryUtil.pointToPlaneDistance(apex, cuttingPlane);

            if (Math.abs(cone.getHeight() - (frustumHeight + upperConeHeight)) < GeometryUtil.THRESHOLD) {

                double upBaseRadius = cone.getRadius() * upperConeHeight / cone.getHeight();

                ShapeVolume shapeVolume = new ShapeVolume();
                double frustumVolume = shapeVolume.calcFrustumVolume(cone.getRadius(), upBaseRadius, frustumHeight);

                if (frustumVolume > GeometryUtil.THRESHOLD){
                    shapeVolumeRatio = shapeVolume.calcConeVolume(upBaseRadius, upperConeHeight) / frustumVolume;
                }
            }

        }
        return shapeVolumeRatio;
    }


}
