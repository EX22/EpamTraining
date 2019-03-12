package by.khomenko.task02.entity;


import by.khomenko.task02.logic.Storage;

/**
 * Entity represents ship with storage for cargo.
 */
public class Ship {

    /**
     * Amount of cargo that ship can carries.
     */
    private Storage storage;

    /**
     * Ship's name.
     */
    private String name;

    /**
     * @param storageSize integer value of the ship's cargo storage capacity.
     * @param nameVal     string value of ship's name.
     */
    public Ship(final int storageSize, final String nameVal) {
        storage = new Storage(storageSize);
        this.name = nameVal;
    }

    /**
     * @return integer value of ship's cargo storage capacity.
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * @return string value of ship's name.
     */
    public String getName() {
        return name;
    }

}
