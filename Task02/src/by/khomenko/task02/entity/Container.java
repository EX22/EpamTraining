package by.khomenko.task02.entity;

/**
 * Cargo stuff entity that ships transport from and into a port.
 * Represents one cargo item.
 */
public class Container {

    /**
     * Description of what type of goods containers contains.
     */
    private String description;

    /**
     * @param descriptionVal String value of description what container's
     *                       got inside.
     */
    public Container(final String descriptionVal) {
        this.description = descriptionVal;
    }

    /**
     * @return String value of description what container's
     * got inside.
     */
    public String getDescription() {
        return description;
    }
}
