package by.khomenko.task02.factory;

import by.khomenko.task02.entity.Port;
import by.khomenko.task02.validator.PortDataValidator;

import java.util.Map;

/**
 * Creates instance of port.
 */
public class PortCreator {

    /**
     * @param parameters incoming parameters for port's creation.
     * @return instance of port if data for its creation is valid,
     * otherwise return null;
     */
    public Port createPort(final Map<String, Object> parameters) {

        PortDataValidator portDataValidator = new PortDataValidator();

        if (portDataValidator.validatePort(parameters)) {
            return new Port((Integer) parameters.get("PortStorageSize"),
                    (Integer) parameters.get("AmountOfDocks"));
        }

        return null;
    }
}
