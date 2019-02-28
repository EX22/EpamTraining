package by.khomenko.training.task01b.repository;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.DefaultShape;
import by.khomenko.training.task01b.logic.GeometryUtil;
import by.khomenko.training.task01b.logic.ShapeSurfaceArea;
import by.khomenko.training.task01b.logic.ShapeVolume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements Repository pattern. Contains shape's instances collection,
 * with methods to manage it.
 * Contains various search methods as well, to search shape or shapes
 * according to different parameters.
 * We follow the agreement that this class is singleton.
 */

public class ShapeRepository {

    public static final ShapeRepository INSTANCE = new ShapeRepository();

    private Map<Integer, DefaultShape> shapeMap = new HashMap<>();

    private Integer idCounter = 0;

    private ShapeRegistry shapeRegistry = new ShapeRegistry();

    /**
     * Adds instance of shape to repository.
     *
     * @param shape instance of DefaultShape descendants.
     */
    public void addShape(final DefaultShape shape) {
        shape.setId(idCounter);
        shapeMap.put(idCounter, shape);
        idCounter++;
        shape.addObserver(shapeRegistry);
    }

    /**
     * Remove instance of shape from repository.
     *
     * @param shape instance of DefaultShape descendants.
     */
    public void removeShape(final DefaultShape shape) {
        shapeMap.remove(shape.getId());
        shape.deleteObservers();

    }

    /**
     * Update instance of shape in repository.
     *
     * @param shape instance of DefaultShape descendants.
     */
    public void updateShape(final DefaultShape shape) {
        DefaultShape tempShape = shapeMap.get(shape.getId());
        if (tempShape != null) {
            tempShape.deleteObservers();
            shapeMap.replace(shape.getId(), shape);
            shape.addObserver(shapeRegistry);
            shape.notifyObservers();
        }

    }

    /**
     * @param id of shape's instance that we want to find.
     * @return shape's instance with appropriate id.
     */
    public DefaultShape searchShapeByID(final Integer id) {

        return shapeMap.get(id);
    }

    /**
     * @param name of shape's instance that we want to find.
     * @return list of shape's instances with appropriate name.
     */
    public List<DefaultShape> searchShapeByName(final String name) {
        List<DefaultShape> resultShapeByNameList = new ArrayList<>();
        for (DefaultShape entryShape : shapeMap.values()) {
            Cone tempCone = (Cone) entryShape;
            if (tempCone.getName().equals(name)) {
                resultShapeByNameList.add(tempCone);
            }
        }
        return resultShapeByNameList;
    }

    /**
     * @return list of shape's instances located in octant.
     */
    public List<DefaultShape> searchShapeByCoordinateInOctant() {
        List<DefaultShape> resultShapeByCoordinateInOctantList
                = new ArrayList<>();
        for (DefaultShape entryShape : shapeMap.values()) {
            Cone tempCone = (Cone) entryShape;
            if (tempCone.getBaseCenter().getX() > 0
                    && tempCone.getBaseCenter().getY() > 0
                    && tempCone.getBaseCenter().getZ() > 0) {
                resultShapeByCoordinateInOctantList.add(tempCone);
            }
        }

        return resultShapeByCoordinateInOctantList;
    }

    /**
     * Search shape's instances that got surface area value in demanded range.
     *
     * @param start upper edge for shape's surface area value.
     * @param end   bottom edge for shape's surface area value.
     * @return list of shape's instances that surface area value
     * fits to demanded range.
     */
    public List<DefaultShape> searchShapeBySurfaceAreaInRange(double start,
                                                              double end) {
        List<DefaultShape> resultShapeBySurfaceAreaInRangeList
                = new ArrayList<>();
        ShapeSurfaceArea shapeSurfaceArea = new ShapeSurfaceArea();
        for (DefaultShape entryShape : shapeMap.values()) {
            Cone tempCone = (Cone) entryShape;
            if (start < shapeSurfaceArea.calcConeSurfaceArea(tempCone)
                    && shapeSurfaceArea.calcConeSurfaceArea(tempCone) < end) {
                resultShapeBySurfaceAreaInRangeList.add(tempCone);
            }
        }
        return resultShapeBySurfaceAreaInRangeList;
    }

    /**
     * Search shape's instances that got volume value in demanded range.
     *
     * @param start upper edge for shape's volume value.
     * @param end   bottom edge for shape's surface area value.
     * @return list of shape's instances that volume value fits
     * to demanded range.
     */
    public List<DefaultShape> searchShapeByVolumeInRange(double start,
                                                         double end) {
        List<DefaultShape> resultShapeByVolumeInRangeList = new ArrayList<>();
        ShapeVolume shapeVolume = new ShapeVolume();
        for (DefaultShape entryShape : shapeMap.values()) {
            Cone tempCone = (Cone) entryShape;
            if (start < shapeVolume.calcConeVolume(tempCone)
                    && shapeVolume.calcConeVolume(tempCone) < end) {
                resultShapeByVolumeInRangeList.add(tempCone);
            }
        }
        return resultShapeByVolumeInRangeList;
    }

    /**
     * Search shape's instances that located in demanded range from
     * coordinate's origin.
     *
     * @param start upper edge for distance from coordinate's origin
     *              to shape's base center.
     * @param end   bottom edge for distance from coordinate's origin
     *              to shape's base center.
     * @return list of shape's instances that's base centers located
     * in demanded range.
     */
    public List<DefaultShape> searchShapeByDistanceFromOriginInRange(double start,
                                                                     double end) {
        List<DefaultShape> resultShapeByDistanceFromOriginInRangeList
                = new ArrayList<>();
        for (Map.Entry<Integer, DefaultShape> entryShape : shapeMap.entrySet()) {
            Cone tempCone = (Cone) entryShape.getValue();
            if (start < GeometryUtil.vectorLength(tempCone.getBaseCenter())
                    && GeometryUtil.vectorLength(tempCone.getBaseCenter()) < end) {
                resultShapeByDistanceFromOriginInRangeList.add(tempCone);
            }
        }
        return resultShapeByDistanceFromOriginInRangeList;
    }

    /**
     * Sorts shape instances according to passed comparator.
     *
     * @param comparator comparator with parameters according to which shape's
     *                  instances should be sorted.
     * @return list of sorted shape's instances.
     */
    public List<DefaultShape> sortShape(Comparator<DefaultShape> comparator) {
        List<DefaultShape> list = new ArrayList<>(shapeMap.values());
        list.sort(comparator);
        return list;
    }


}
