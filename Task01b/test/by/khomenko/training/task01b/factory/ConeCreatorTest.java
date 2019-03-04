package by.khomenko.training.task01b.factory;

import by.khomenko.training.task01b.entity.Cone;
import by.khomenko.training.task01b.entity.Vector;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class ConeCreatorTest {

    @Test
    public void testCreateShape() {
        Map<String, Object> createShapeTestMap = new HashMap<>();
        Map<String, Object> createBaseCenterInfo = new HashMap<>();
        Map<String, Object> createDirectionInfo = new HashMap<>();
        createBaseCenterInfo.put("x", 1.0);
        createBaseCenterInfo.put("y", 2.0);
        createBaseCenterInfo.put("z", 3.0);
        createDirectionInfo.put("x", 1.0);
        createDirectionInfo.put("y", 0.0);
        createDirectionInfo.put("z", 0.0);

        createShapeTestMap.put("name", "FirstConeName");
        createShapeTestMap.put("baseCenter", createBaseCenterInfo);
        createShapeTestMap.put("radius", 3.0);
        createShapeTestMap.put("height", 9.0);
        createShapeTestMap.put("direction", createDirectionInfo);

        ConeCreator coneCreatorTest = new ConeCreator();
        Cone createdCone = (Cone) coneCreatorTest.createShape(createShapeTestMap);
        assertNotNull(createdCone);

    }
}