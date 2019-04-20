package by.khomenko.training.finaltask05.entity;

public class Category extends Entity {

    private String name;
    private String imagePath = "c/imagePath.by";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
