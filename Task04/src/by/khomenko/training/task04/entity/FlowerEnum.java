package by.khomenko.training.task04.entity;

public enum FlowerEnum {
    FLOWERS("flowers"),
    FLOWER("flower"),
    NAME("name"),
    SOIL("soil"),
    ORIGIN("origin"),
    VISUAL_PARAMETERS("visual-parameters"),
    SIZE("size"),
    LEAF_COLOR("leaf-color"),
    STEM_COLOR("stem-color"),
    GROWING_TIPS("growing-tips"),
    TEMPERATURE("temperature"),
    HUMIDITY("humidity"),
    LIGHT_LEVEL("light-level"),
    WATER("water"),
    MULTIPLYING("multiplying");

    private String value;

    FlowerEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
