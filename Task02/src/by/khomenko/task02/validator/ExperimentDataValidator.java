package by.khomenko.task02.validator;

import java.util.Map;

/**
 * Validates data for ports creation.
 */
public class ExperimentDataValidator {

    /**
     * Validates data for ports creation.
     *
     * @param expParams parameters for ports creation.
     * @return boolean true if there are appropriate amount of parameters and
     * they have proper format for port's instance creation,
     * otherwise return false.
     */
    public boolean validateExperiment(final Map<String, Object> expParams) {

        if (!validatePort(expParams.get("PortData"))) {
            return false;
        }

        return validateFleet(expParams.get("FleetData"));

    }

    private boolean validatePort(final Object portParams) {

        if (!(portParams instanceof Map)) {
            return false;
        }

        Map<String, Object> portData = (Map<String, Object>) portParams;

        if (!(portData.get("PortStorageSize") instanceof Integer)) {
            return false;
        }

        return (portData.get("AmountOfDocks") instanceof Integer);
    }

    private boolean validateFleet(final Object fleetParams) {

        if (!(fleetParams instanceof Map)) {
            return false;
        }

        Map<String, Object> fleetData = (Map<String, Object>) fleetParams;

        if (!(fleetData.get("Fleet") instanceof Integer)) {
            return false;
        }

        if (!(fleetData.get("MinShipStorageSize") instanceof Integer)) {
            return false;
        }

        return (fleetData.get("MaxShipStorageSize") instanceof Integer);

    }
}
