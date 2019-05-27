package by.khomenko.training.finaltask05.entity;

import java.util.Objects;

public class Category extends Entity {

    private String name;
    private String imagePath;
    private String question;

    public Category() {
    }

    public Category(Integer id, String name, String imagePath,
                    String question) {
        this.setId(id);
        this.name = name;
        this.imagePath = imagePath;
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public void setName(final String nameVal) {
        this.name = nameVal;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(final String imagePathVal) {
        this.imagePath = imagePathVal;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(final String questionVal) {
        this.question = questionVal;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Category category = (Category) o;
        return getName().equals(category.getName())
                && getImagePath().equals(category.getImagePath())
                && getQuestion().equals(category.getQuestion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getImagePath(),
                getQuestion());
    }
}
