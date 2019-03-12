package by.khomenko.task02.logic;

import by.khomenko.task02.entity.Port;
import by.khomenko.task02.entity.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Represents cargo service spot for one ship.
 */
public class Dock implements Callable<String> {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER = LogManager.getLogger(Dock.class);

    /**
     * Instance of ship that the dock to perform operations of load or unload.
     */
    private final Ship ship;

    /**
     * @param shipVal Instance of ship that comes to the port's dock for cargo
     *                service.
     */
    public Dock(final Ship shipVal) {
        this.ship = shipVal;
    }

    /**
     * Performs load or unload operations with cargo that ships or port
     * are carrying.
     *
     * @return string value of ship's name that have been served.
     * @throws Exception if unable to perform inner operations.
     */
    @Override
    public String call() throws Exception {
        Port port = Port.getInstance();
        while (!ship.getStorage().isEmpty()) {

            port.getStorage().put(ship.getStorage().take());
            TimeUnit.MILLISECONDS.sleep(1);
        }


        while (!ship.getStorage().isFull()) {

            ship.getStorage().put(port.getStorage().take());
            TimeUnit.MILLISECONDS.sleep(2);

        }

        LOGGER.info(ship.getName() + " was served. ");

        return ship.getName();
    }
}
