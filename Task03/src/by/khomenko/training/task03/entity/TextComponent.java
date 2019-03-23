package by.khomenko.training.task03.entity;

import java.util.List;

public interface TextComponent {
    String getValue();
    int getSymbolAppearanceCount(char c);
    int getComponentsCount();
    int getLengthExceptPunctuation();

    //TODO Consider not to add methods below in this interface!
    /*void addComponent();
    TextComponent getComponent();
    void removeComponent();*/
}
