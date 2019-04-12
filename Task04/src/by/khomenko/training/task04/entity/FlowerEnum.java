package by.khomenko.training.task04.entity;

/**
 * Enumeration of tag that might be met in flowers.xml file.
 */
public enum FlowerEnum {
    /**
     * Constant corresponds flowers tag.
     */
    FLOWERS("flowers"),
    /**
     * Constant corresponds flower tag.
     */
    FLOWER("flower"),
    /**
     * Constant corresponds name tag.
     */
    NAME("name"),
    /**
     * Constant corresponds soil tag.
     */
    SOIL("soil"),
    /**
     * Constant corresponds origin tag.
     */
    ORIGIN("origin"),
    /**
     * Constant corresponds visual-parameters tag.
     */
    VISUAL_PARAMETERS("visual-parameters"),
    /**
     * Constant corresponds size tag.
     */
    SIZE("size"),
    /**
     * Constant corresponds leaf-color tag.
     */
    LEAF_COLOR("leaf-color"),
    /**
     * Constant corresponds stem-color tag.
     */
    STEM_COLOR("stem-color"),
    /**
     * Constant corresponds growing-tips tag.
     */
    GROWING_TIPS("growing-tips"),
    /**
     * Constant corresponds temperature tag.
     */
    TEMPERATURE("temperature"),
    /**
     * Constant corresponds humidity tag.
     */
    HUMIDITY("humidity"),
    /**
     * Constant corresponds light-level tag.
     */
    LIGHT_LEVEL("light-level"),
    /**
     * Constant corresponds water tag.
     */
    WATER("water"),
    /**
     * Constant corresponds multilying tag.
     */
    MULTIPLYING("multiplying"),
    /**
     * Constant corresponds planting-date tag.
     */
    PLANTING_DATE("planting-date");

    /**
     * Enumeration's variable.
     */
    private String value;

    /**
     * @param valueVal String value of enumeration's variable.
     */
    FlowerEnum(final String valueVal) {
        this.value = valueVal;
    }

    /**
     * @return String value of enumeration's constant.
     */
    public String getValue() {
        return value;
    }
}
