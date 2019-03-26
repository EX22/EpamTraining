package by.khomenko.training.task03.entity;

import java.util.Comparator;
import java.util.List;

/**
 * Declares interface for objects in composition.
 * Implements default behaviour for the interface common to all classes
 * as appropriate.
 * Declares an interface for accessing and managing its child components.
 */
public interface TextComponent {
    /**
     * @return String or calculated value of child components.
     */
    String getValue();

    /**
     * @return String value of child components.
     */
    String getTextValue();

    /**
     * @param c required to find character.
     * @return int value of required symbol appears in text component.
     */
    int getSymbolAppearanceCount(char c);

    /**
     * @return int value of components in text component.
     */
    int getComponentsCount();

    /**
     * @param typeKey required instance's type.
     * @return list of text components matching with passed to method type.
     */
    List<TextComponent> getAllTextComponentsOfType(Class typeKey);

    /**
     * @param comparator required for sort comparator.
     */
    void sortTextComponents(Comparator<TextComponent> comparator);

}
