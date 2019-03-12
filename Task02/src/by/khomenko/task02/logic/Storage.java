package by.khomenko.task02.logic;

import by.khomenko.task02.entity.Container;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Stores, puts in and takes out cargo.
 */
public class Storage {

    /**
     * Amount of cargo the storage currently has got.
     */
    private int currentLoad;
    /**
     * Array for storing container's item.
     */
    private final Container[] items;
    /**
     * Tool for controlling access to a shared resource by multiple threads.
     */
    private final Lock lock = new ReentrantLock();
    /**
     * Provide a means for one thread to suspend execution (to "wait")
     * until notified by another thread that loading state condition
     * may now be true.
     */
    private final Condition notFull = lock.newCondition();
    /**
     * Provide a means for one thread to suspend execution (to "wait")
     * until notified by another thread that loading state condition
     * may now be true.
     */
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
