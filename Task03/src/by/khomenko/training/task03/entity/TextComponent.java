package by.khomenko.training.task03.entity;

import java.util.Comparator;
import java.util.List;

public interface TextComponent {
    String getValue();
    String getTextValue();
    int getSymbolAppearanceCount(char c);
    int getComponentsCount();
    List <TextComponent> getAllTextComponentsOfType(Class typeKey);
    void sortTextComponents(Comparator<TextComponent> comparator);

}
