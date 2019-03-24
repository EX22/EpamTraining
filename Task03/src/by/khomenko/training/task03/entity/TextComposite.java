package by.khomenko.training.task03.entity;

import java.util.*;

public abstract class TextComposite implements TextComponent {

    protected List<TextComponent> list;
    protected String delimiter = "";
    protected String prefix = "";
    protected String suffix = "";

    public TextComposite() {
        this.list = Collections.emptyList();
    }

    public TextComposite(final List<TextComponent> listVal) {
        this.list = listVal;
    }

    public TextComposite(final List<TextComponent> listVal,
                         final String delimiterVal,
                         final String prefixVal, final String suffixVal) {

        this.list = listVal;
        this.delimiter = delimiterVal;
        this.prefix = prefixVal;
        this.suffix = suffixVal;

    }

    @Override
    public String getValue() {
        StringJoiner result = new StringJoiner(delimiter, prefix, suffix);
        for (TextComponent textComponent : list) {
            result.add(textComponent.getValue());
        }
        return result.toString();
    }

    @Override
    public String getTextValue() {
        StringJoiner result = new StringJoiner(delimiter, prefix, suffix);
        for (TextComponent textComponent : list) {
            result.add(textComponent.getTextValue());
        }
        return result.toString();
    }

    @Override
    public int getSymbolAppearanceCount(char c) {

        return list.stream().map(x -> x.getSymbolAppearanceCount(c))
                .reduce(0, (left, right) -> left + right);
    }

    @Override
    public int getComponentsCount() {
        return list.size();
    }

    @Override
    public List<TextComponent> getAllTextComponentsOfType(Class typeKey) {
        List<TextComponent> resultedList = new ArrayList<>();
        for (TextComponent textComponent : list) {
            if (typeKey.isInstance(textComponent)) {
                resultedList.add(textComponent);
            } else {
                resultedList.addAll(textComponent.getAllTextComponentsOfType(typeKey));
            }
        }
        return resultedList;
    }

    @Override
    public void sortTextComponents(Comparator<TextComponent> comparator) {
        list.sort(comparator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextComposite)) return false;
        TextComposite that = (TextComposite) o;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
