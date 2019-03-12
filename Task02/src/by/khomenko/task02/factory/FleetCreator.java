package by.khomenko.task02.factory;

import by.khomenko.task02.entity.Ship;

import java.util.Map;

/**
 * Creates fleet of ships.
 */
public class FleetCreator {

    /**
     * @param parameters incoming parameters for fleet of ships creation.
     * @return array ship's instances if data for their creation is valid.
     */
    public Ship[] createFleet(final Map<String, Object> parameters) {

        Ship[] fleet = new Ship[(Integer) parameters.get("Fleet")];

        for (int i = 0; i < fleet.length; i++) {
            int shipSize
                    = (int) (Math.random()
                    * (Integer) parameters.get("MaxShipStorageSize"))
                    + (Integer) parameters.get("MinShipStorageSize");
            fleet[i] = new Ship(shipSize, ("Ship No " + i));
        }
        return fleet;

    }
}
