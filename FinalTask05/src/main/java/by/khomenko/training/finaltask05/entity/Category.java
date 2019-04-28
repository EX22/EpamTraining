package by.khomenko.training.finaltask05.entity;

import java.util.Objects;

public class Category extends Entity {

    private String name;
    private String imagePath = "c/imagePath.by";
    private String question;


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

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return getName().equals(category.getName()) &&
                getImagePath().equals(category.getImagePath()) &&
                getQuestion().equals(category.getQuestion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getImagePath(), getQuestion());
    }
}
