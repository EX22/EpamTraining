package by.khomenko.training.finaltask05.entity;

public class Favorite extends Entity {

    private Integer userId;
    private Integer categoryId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userIdentity) {
        this.userId = userIdentity;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(final Integer categoryIdentity) {
        this.categoryId = categoryIdentity;
    }
}
