package by.khomenko.task02.validator;

import java.util.Map;

/**
 * Validates data for port's creation.
 */
public class ExperimentDataValidator {

    /**
     * Validates data for an experiment's creation.
     *
     * @param expParams parameters for an experiment's creation.
     * @return boolean true if there are appropriate amount of parameters and
     * they have proper format for fleet's creation and port's instance
     * creation, otherwise return false.
     */
    public boolean validateExperiment(final Map<String, Object> expParams) {

        if (!validatePort(expParams.get("PortData"))) {
            return false;
        }

        return validateFleet(expParams.get("FleetData"));

    }

    /**
     * @param portParams parameters for port's creation.
     * @return boolean true if there are appropriate amount of parameters and
     * they have proper format for port's instance
     * creation, otherwise return false.
     */
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

    /**
     * @param fleetParams parameters for fleet's creation.
     * @return boolean true if there are appropriate amount of parameters and
     * they have proper format for fleet's creation, otherwise return false.
     */
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
