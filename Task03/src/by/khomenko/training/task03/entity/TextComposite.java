package by.khomenko.training.task03.entity;

import java.util.List;

public abstract class TextComposite implements TextComponent {

    protected List<TextComponent> list;

    public TextComposite(List<TextComponent> listVal) {
        this.list = listVal;
    }


    public void addComponent(final TextComponent textComponent){
        list.add(textComponent);
    }

    public List<TextComponent> getComponentsList(){
        return list;
    }

    public void removeComponent(final TextComponent textComponent){
        list.remove(textComponent);
    }

}
