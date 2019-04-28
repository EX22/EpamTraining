package by.khomenko.training.finaltask05.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private Integer id;

    public Integer getIdentity() {
        return id;
    }

    public void setIdentity(final Integer identity) {
        this.id = identity;
    }

    @Override
    public boolean equals(final Object object) {
        if (object != null) {
            if (object != this) {
                if (object.getClass() == getClass() && id != null) {
                    return id.equals(((Entity) object).id);
                }
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}



