package by.khomenko.training.task01b.entity;

import java.util.Observable;

/**
 * Parent entity for all possible shape's entities.
 */
public abstract class DefaultShape extends Observable {

    private Integer id;

    public DefaultShape() {
        super();
        setChanged();
    }

    /**
     * @return Integer value shape's id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param idVal shape's id value.
     */
    public void setId(final Integer idVal) {
        this.id = idVal;
    }


}
