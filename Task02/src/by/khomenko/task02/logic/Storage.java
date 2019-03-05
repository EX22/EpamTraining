package by.khomenko.task02.logic;

import by.khomenko.task02.entity.Container;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Stores, puts in and takes out cargo.
 */
public class Storage {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER = LogManager.getLogger(Storage.class);

    /**
     * Amount of cargo the storage currently has got.
     */
    private int currentLoad;
    /**
     * Array for storing container's item.
     */
    private final Container[] items;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    /**
     * Index of container that was put.
     */
    private int putIndex;
    /**
     * Index of container that was taken.
     */
    private int takeIndex;

    /**
     * @param capacity integer value of storage's capacity.
     */
    public Storage(final int capacity) {
        this.items = new Container[capacity];
    }

    /**
     * @return boolean true if storage is empty, otherwise return false.
     */
    public boolean isEmpty() {
        return currentLoad == 0;
    }

    /**
     * @return boolean true if storage is full, otherwise return false.
     */
    public boolean isFull() {
        return currentLoad == items.length;
    }

    /**
     * @return integer value of storage's capacity.
     */
    public int getCapacity() {
        return items.length;
    }

    /**
     * Performs an operation of putting container into the storage.
     *
     * @param container Instance of container that is putting into the storage.
     * @throws InterruptedException if the current thread is interrupted.
     */
    public void put(final Container container) throws InterruptedException {
        lock.lock();
        try {
            while (currentLoad == items.length) {
                notFull.await();
            }
            items[putIndex] = container;
            putIndex++;
            if (putIndex == items.length) {
                putIndex = 0;
            }
            currentLoad++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Performs an operation of taking container from the storage.
     *
     * @return Instance of container that is taking from the storage.
     * @throws InterruptedException if the current thread is interrupted.
     */
    public Container take() throws InterruptedException {
        lock.lock();
        try {
            while (currentLoad == 0) {
                notEmpty.await();
            }
            Container container = items[takeIndex];
            takeIndex++;
            if (takeIndex == items.length) {
                takeIndex = 0;
            }
            currentLoad--;
            notFull.signal();
            return container;
        } finally {
            lock.unlock();
        }
    }
}
