package by.khomenko.training.task01b.repository;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.DefaultShape;
import by.khomenko.training.task01b.logic.GeometryUtil;
import by.khomenko.training.task01b.logic.ShapeSurfaceArea;
import by.khomenko.training.task01b.logic.ShapeVolume;

import java.util.*;

/**
 * Implements Repository pattern. Contains shape's instances collection, with methods to manage it.
 * Contains various search methods as well, to search shape or shapes according to different parameters.
 * We follow the agreement that this class is singleton.
 */

public class ShapeRepository {

    public static final ShapeRepository INSTANCE = new ShapeRepository();

    private Map<Integer, DefaultShape> shapeMap = new HashMap<>();

    private Integer idCounter = 0;

    private ShapeRegistry shapeRegistry = new ShapeRegistry();

    /**
     * Adds instance of shape to repository.
     * @param shape instance of DefaultShape descendants.
     */
    public void addShape(DefaultShape shape) {
        shape.setId(idCounter);
        shapeMap.put(idCounter, shape);
        idCounter++;
        shape.addObserver(shapeRegistry);
    }

    /**
     * Remove instance of shape from repository.
     * @param shape instance of DefaultShape descendants.
     */
    public void removeShape(DefaultShape shape) {
        shapeMap.remove(shape.getId());
        shape.deleteObservers();

    }

    /**
     * Update instance of shape in repository.
     * @param shape instance of DefaultShape descendants.
     */
    public void updateShape(DefaultShape shape) {
        DefaultShape tempShape = shapeMap.get(shape.getId());
        if (tempShape != null) {
            tempShape.deleteObservers();
            shapeMap.replace(shape.getId(), shape);
            shape.addObserver(shapeRegistry);
            shape.notifyObservers();
        }

    }

    public DefaultShape searchShapeByID(Integer id) {

        return shapeMap.get(id);
    }

    public List<DefaultShape> searchShapeByName(String name) {
        List<DefaultShape> resultShapeByNameList = new ArrayList<>();
        for (DefaultShape entryShape : shapeMap.values()) {
            Cone tempCone = (Cone) entryShape;
            if (tempCone.getName().equals(name)) {
                resultShapeByNameList.add(tempCone);
            }
        }
        return resultShapeByNameList;
    }

    public List<DefaultShape> searchShapeByCoordinateInOctant() {
        List<DefaultShape> resultShapeByCoordinateInOctantList = new ArrayList<>();
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

    public List<DefaultShape> searchShapeBySurfaceAreaInRange(double start, double end) {
        List<DefaultShape> resultShapeBySurfaceAreaInRangeList = new ArrayList<>();
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

    public List<DefaultShape> searchShapeByVolumeInRange(double start, double end) {
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

    public List<DefaultShape> searchShapeByDistanceFromOriginInRange(double start, double end) {
        List<DefaultShape> resultShapeByDistanceFromOriginInRangeList = new ArrayList<>();
        for (Map.Entry<Integer, DefaultShape> entryShape : shapeMap.entrySet()) {
            Cone tempCone = (Cone) entryShape.getValue();
            if (start < GeometryUtil.vectorLength(tempCone.getBaseCenter())
                    && GeometryUtil.vectorLength(tempCone.getBaseCenter()) < end) {
                resultShapeByDistanceFromOriginInRangeList.add(tempCone);
            }
        }
        return resultShapeByDistanceFromOriginInRangeList;
    }

    public List<DefaultShape> sortShape(Comparator<DefaultShape> comparator){
        List<DefaultShape> list = new ArrayList<>(shapeMap.values());
        list.sort(comparator);
        return list;
    }


}
