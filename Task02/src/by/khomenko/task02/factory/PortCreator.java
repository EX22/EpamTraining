package by.khomenko.task02.factory;

import by.khomenko.task02.entity.Port;


import java.util.Map;

/**
 * Creates instance of port.
 */
public class PortCreator {

    /**
     * @param parameters incoming parameters for port's creation.
     * @return instance of port if data for its creation is valid.
     */
    public Port createPort(final Map<String, Object> parameters) {
        return new Port((Integer) parameters.get("PortStorageSize"),
                (Integer) parameters.get("AmountOfDocks"));

    }
}
