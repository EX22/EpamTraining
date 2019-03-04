package by.khomenko.training.task01b.repository;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.logic.ShapeSurfaceArea;
import by.khomenko.training.task01b.logic.ShapeVolume;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Stores and manages shape's data, such as surface area and volume
 */
public class ShapeRegistry implements Observer {

    private Map<Integer, Double> shapeSurfaceAreaInfoStorage = new HashMap<>();
    private Map<Integer, Double> shapeVolumeInfoStorage = new HashMap<>();

    private ShapeSurfaceArea shapeSurfaceArea = new ShapeSurfaceArea();
    private ShapeVolume shapeVolume = new ShapeVolume();

    /**
     * This method is called whenever the observed shape's object is changed.
     *
     * @param o   the observable shape's instance information of that needed
     *            to be updated.
     * @param arg an argument passed to the notifyObservers method.
     */
    @Override
    public void update(final Observable o, final Object arg) {

        Cone tempCone = (Cone) o;

        shapeSurfaceAreaInfoStorage.put(tempCone.getId(),
                shapeSurfaceArea.calcConeSurfaceArea(tempCone));
        shapeVolumeInfoStorage.put(tempCone.getId(),
                shapeVolume.calcConeVolume(tempCone));

    }

    public Double getShapeSurfaceArea(final Integer key) {
        return shapeSurfaceAreaInfoStorage.get(key);
    }

    public Double getShapeVolume(final Integer key) {
        return shapeVolumeInfoStorage.get(key);
    }

}
