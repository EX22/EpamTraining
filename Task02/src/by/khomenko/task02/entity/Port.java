package by.khomenko.task02.entity;

import by.khomenko.task02.logic.Storage;

/**
 * Entity represents port with docks and storage for ship's cargo service.
 */
public class Port {

    /**
     * Amount of cargo that port can got in general.
     */
    private Storage storage;
    /**
     * Amount of places/docks where ships can get cargo service one by one.
     */
    private int amountOfDocks;

    /**
     * @param storageSize      integer value of the whole port's cargo storage
     *                         capacity.
     * @param amountOfDocksVal integer value of amount of docks in the port.
     */
    public Port(final int storageSize, final int amountOfDocksVal) {

        this.storage = new Storage(storageSize);
        this.amountOfDocks = amountOfDocksVal;
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
    public int getAmountOfDocks() {
        return amountOfDocks;
    }
}
