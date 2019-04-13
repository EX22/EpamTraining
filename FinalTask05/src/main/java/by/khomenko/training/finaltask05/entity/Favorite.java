package by.khomenko.training.finaltask05.entity;

public class Favorite extends Entity {

    private Integer userIdentity;
    private Integer categoryIdentity;

    public Integer getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(Integer userIdentity) {
        this.userIdentity = userIdentity;
    }

    public Integer getCategoryIdentity() {
        return categoryIdentity;
    }

    public void setCategoryIdentity(Integer categoryIdentity) {
        this.categoryIdentity = categoryIdentity;
    }
}
