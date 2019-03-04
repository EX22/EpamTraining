package by.khomenko.training.task01b.validator;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class ShapeDataValidatorTest {

    @Test
    public void testValidateCone() {
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

        ShapeDataValidator shapeDataValidatorTest = new ShapeDataValidator();
        assertTrue(shapeDataValidatorTest.validateCone(createShapeTestMap));
    }
}