package by.khomenko.task02.entity;

import by.khomenko.task02.logic.Storage;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Entity represents thread safe singleton class Port with storage.
 */
public class Port {

    /**
     * Instance of Port type variable.
     */
    private static Port instance;
    /**
     * Tool for controlling access to a shared resource by multiple threads.
     */
    private static final Lock LOCK = new ReentrantLock();

    /**
     * @param parameters incoming parameters for Port instance initialization.
     */
    public static void setParameters(final Map<String, Object> parameters) {

        size = ((Integer) parameters.get("PortStorageSize"));
        amountOfDocks = ((Integer) parameters.get("AmountOfDocks"));

    }

    /**
     * @return instance of Port.
     */
    public static Port getInstance() {
        LOCK.lock();
        if (instance == null) {
            instance = new Port(size);
        }
        LOCK.unlock();
        return instance;
    }

    /**
     * Port's storage size.
     */
    private static int size;

    /**
     * Amount of cargo that port can got in general.
     */
    private Storage storage;
    /**
     * Amount of places/docks where ships can get cargo service one by one.
     */
    private static int amountOfDocks;

    /**
     * @param storageSize integer value of the whole port's cargo storage
     *                    capacity.
     */
    public Port(final int storageSize) {

        this.storage = new Storage(storageSize);

    }

    /**
     * @return integer value of port's cargo storage capacity.
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * @return integer value, amount of docks that port's got.
     */
    public static int getAmountOfDocks() {
        return amountOfDocks;
    }
}
