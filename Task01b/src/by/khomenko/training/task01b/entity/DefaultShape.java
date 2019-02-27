package by.khomenko.training.task01b.entity;

import java.util.Observable;

public abstract class DefaultShape extends Observable {
    private Integer id;

    public DefaultShape() {
        super();
        setChanged();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
