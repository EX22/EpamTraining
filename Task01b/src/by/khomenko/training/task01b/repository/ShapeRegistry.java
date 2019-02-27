package by.khomenko.training.task01b.repository;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.logic.ShapeSurfaceArea;
import by.khomenko.training.task01b.logic.ShapeVolume;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class ShapeRegistry implements Observer {

    private Map<Integer, Double> shapeSurfaceAreaInfoStorage = new HashMap<>();
    private Map<Integer, Double> shapeVolumeInfoStorage = new HashMap<>();

    private ShapeSurfaceArea shapeSurfaceArea = new ShapeSurfaceArea();
    private ShapeVolume shapeVolume = new ShapeVolume();

    @Override
    public void update(Observable o, Object arg) {

        Cone tempCone = (Cone) o;

        shapeSurfaceAreaInfoStorage.put(tempCone.getId(), shapeSurfaceArea.calcConeSurfaceArea(tempCone));
        shapeVolumeInfoStorage.put(tempCone.getId(), shapeVolume.calcConeVolume(tempCone));

    }

    public Double getShapeSurfaceArea(Integer key) {
        return shapeSurfaceAreaInfoStorage.get(key);
    }

    public Double getShapeVolume(Integer key) {
        return shapeVolumeInfoStorage.get(key);
    }

}
