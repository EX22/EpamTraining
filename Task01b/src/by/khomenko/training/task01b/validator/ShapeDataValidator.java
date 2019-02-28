package by.khomenko.training.task01b.validator;

import java.util.Map;

/**
 * Validates data for shapes creation.
 */
public class ShapeDataValidator {

    /**
     * Validates data for cone creation.
     *
     * @param coneParams parameters for cone creation.
     * @return boolean true if there are appropriate amount of parameters and
     * they have proper format for shape's instance creation,
     * otherwise return false.
     */
    public boolean validateCone(final Map<String, Object> coneParams) {

        if (!(coneParams.get("name") instanceof String)) {
            return false;
        }

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

    /**
     * @param vectorParams parameters for vector creation.
     * @return boolean true if there are appropriate amount of parameters
     * and they have proper format for vector's instance creation,
     * otherwise return false.
     */
    // Inversion gets better code's understanding here.
    //@SuppressWarnings("squid:S1126")
    private boolean validateVector(final Object vectorParams) {

        if (!(vectorParams instanceof Map)) {
            return false;
        }

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
