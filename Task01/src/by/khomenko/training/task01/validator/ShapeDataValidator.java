package by.khomenko.training.task01.validator;

import java.util.Map;

public class ShapeDataValidator {

    public boolean validateCone(Map<String, Object> coneParams) {

        if (!validateVector(coneParams.get("baseCenter"))) {
            return false;
        }

        if (!validateVector(coneParams.get("direction"))) {
            return false;
        }

        if (!(coneParams.get("height") instanceof Double)) {
            return false;
        }

        return (coneParams.get("radius") instanceof Double);
    }

    //] Inversion gets better code's understanding here.
    //@SuppressWarnings("squid:S1126")
    private boolean validateVector(Object vectorParams) {

        if (!(vectorParams instanceof Map)) {
            return false;
        }
        // In order not to check exceptions in calling method.
        Map<String, Object> vectorData = (Map<String, Object>) vectorParams;

        if (!(vectorData.get("x") instanceof Double)) {
            return false;
        }

        if (!(vectorData.get("y") instanceof Double)) {
            return false;
        }

        return (vectorData.get("z") instanceof Double);
    }
}
