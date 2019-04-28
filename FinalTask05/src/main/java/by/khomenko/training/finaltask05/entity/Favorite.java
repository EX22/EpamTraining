package by.khomenko.training.finaltask05.entity;

import java.util.Objects;

public class Favorite extends Entity {

    private Integer userId;
    private Integer categoryId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userIdentityVal) {
        this.userId = userIdentityVal;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(final Integer categoryIdentityVal) {
        this.categoryId = categoryIdentityVal;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Favorite)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Favorite favorite = (Favorite) o;
        return getUserId().equals(favorite.getUserId())
                && getCategoryId().equals(favorite.getCategoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUserId(), getCategoryId());
    }
}
