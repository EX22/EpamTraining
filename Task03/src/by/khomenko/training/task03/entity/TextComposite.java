package by.khomenko.training.task03.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Defines behaviour for components having children.
 * Stores child components.
 * Implements child related operations in the component interface.
 */
public abstract class TextComposite implements TextComponent {

    /**
     * List of child components.
     */
    protected List<TextComponent> list;
    /**
     * Default delimiter for child components.
     */
    private String delimiter = "";
    /**
     * Default prefix for child components.
     */
    private String prefix = "";
    /**
     * Default suffix for child components.
     */
    private String suffix = "";

    /**
     * Default constructor.
     */
    public TextComposite() {
        this.list = Collections.emptyList();
    }

    /**
     * @param listVal list of text components.
     */
    public TextComposite(final List<TextComponent> listVal) {
        this.list = listVal;
    }

    /**
     * @param listVal      list of text components.
     * @param delimiterVal defined delimiter for for particular child component.
     * @param prefixVal    defined prefix for for particular child component.
     * @param suffixVal    defined suffix for for particular child component.
     */
    public TextComposite(final List<TextComponent> listVal,
                         final String delimiterVal,
                         final String prefixVal, final String suffixVal) {

        this.list = listVal;
        this.delimiter = delimiterVal;
        this.prefix = prefixVal;
        this.suffix = suffixVal;

    }

    /**
     * @return String or calculated value of child component.
     */
    @Override
    public String getValue() {
        StringJoiner result = new StringJoiner(delimiter, prefix, suffix);
        for (TextComponent textComponent : list) {
            result.add(textComponent.getValue());
        }
        return result.toString();
    }

    /**
     * @return String value of child component.
     */
    @Override
    public String getTextValue() {
        StringJoiner result = new StringJoiner(delimiter, prefix, suffix);
        for (TextComponent textComponent : list) {
            result.add(textComponent.getTextValue());
        }
        return result.toString();
    }

    /**
     * @param c required to find character.
     * @return int value of required symbol appears in particular text
     * component.
     */
    @Override
    public int getSymbolAppearanceCount(final char c) {

        return list.stream().map(x -> x.getSymbolAppearanceCount(c))
                .reduce(0, (left, right) -> left + right);
    }

    /**
     * @return int value of components in particular text component.
     */
    @Override
    public int getComponentsCount() {
        return list.size();
    }

    /**
     * @param typeKey required instance's type.
     * @return list of text components matching with passed to method type.
     */
    @Override
    public List<TextComponent> getAllTextComponentsOfType(final Class typeKey) {
        List<TextComponent> resultedList = new ArrayList<>();
        for (TextComponent textComponent : list) {
            if (typeKey.isInstance(textComponent)) {
                resultedList.add(textComponent);
            } else {
                resultedList.addAll(textComponent.
                        getAllTextComponentsOfType(typeKey));
            }
        }
        return resultedList;
    }

    /**
     * @param comparator required for text's components sort comparator.
     */
    @Override
    public void sortTextComponents(final Comparator<TextComponent> comparator) {
        list.sort(comparator);
    }

    /**
     * @param o instance that we check equality to current instance.
     * @return true if passed parameter is equals to this instance.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TextComposite)) {
            return false;
        }
        TextComposite that = (TextComposite) o;
        return list.equals(that.list);
    }

    /**
     * @return Hash code of this instance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
