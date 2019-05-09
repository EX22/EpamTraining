package by.khomenko.training.finaltask05.entity;

import java.util.Objects;

public class Image extends Entity {

    private String path;
    private Integer userId;
    private Integer categoryId;

    public Image() {
    }

    public Image(final String pathVal, final Integer userIdVal,
                 final Integer categoryIdVal) {
        this.path = pathVal;
        this.userId = userIdVal;
        this.categoryId = categoryIdVal;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String pathVal) {
        this.path = pathVal;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(final Integer categoryIdVal) {
        this.categoryId = categoryIdVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Image)) {
            return false;
        }
        if (!super.equals(o)){
            return false;
        }
        Image image = (Image) o;
        return getPath().equals(image.getPath())
                && getUserId().equals(image.getUserId())
                && getCategoryId().equals(image.getCategoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPath(), getUserId(),
                getCategoryId());
    }
}
