package by.khomenko.training.finaltask05.entity;

import java.util.Objects;

public class RecognizedImg extends Entity {

    private Integer imageId;
    private Integer userId;
    private String answer;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(final Integer imageIdVal) {
        this.imageId = imageIdVal;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userIdVal) {
        this.userId = userIdVal;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(final String answerVal) {
        this.answer = answerVal;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecognizedImg)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        RecognizedImg that = (RecognizedImg) o;
        return getImageId().equals(that.getImageId())
                && getUserId().equals(that.getUserId())
                && getAnswer().equals(that.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getImageId(), getUserId(),
                getAnswer());
    }
}
