package by.khomenko.training.finaltask05.entity;

import java.util.Objects;

public class RecognizedImgs extends Entity {

    private Integer imageId;
    private Integer userId;
    private String answer;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecognizedImgs)) return false;
        if (!super.equals(o)) return false;
        RecognizedImgs that = (RecognizedImgs) o;
        return getImageId().equals(that.getImageId()) &&
                getUserId().equals(that.getUserId()) &&
                getAnswer().equals(that.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getImageId(), getUserId(), getAnswer());
    }
}
