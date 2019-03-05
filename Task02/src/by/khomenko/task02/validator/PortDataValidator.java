package by.khomenko.task02.validator;

import java.util.Map;

/**
 * Validates data for ports creation.
 */
public class PortDataValidator {

    /**
     * Validates data for ports creation.
     *
     * @param portParams parameters for ports creation.
     * @return boolean true if there are appropriate amount of parameters and
     * they have proper format for port's instance creation,
     * otherwise return false.
     */
    public boolean validatePort(final Map<String, Object> portParams) {

        if (!(portParams.get("PortStorageSize") instanceof Integer)) {
            return false;
        }

        if (!(portParams.get("AmountOfDocks") instanceof Integer)) {
            return false;
        }

        if (!(portParams.get("Fleet") instanceof Integer)) {
            return false;
        }

        if (!(portParams.get("MinShipStorageSize") instanceof Integer)) {
            return false;
        }

        return (portParams.get("MaxShipStorageSize") instanceof Integer);
    }
}
