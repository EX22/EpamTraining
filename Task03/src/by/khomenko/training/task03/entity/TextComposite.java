package by.khomenko.training.task03.entity;

import java.util.List;
import java.util.Objects;

public abstract class TextComposite implements TextComponent {

    protected List<TextComponent> list;

    public TextComposite(final List<TextComponent> listVal) {
        this.list = listVal;
    }

    public void addComponent(final TextComponent textComponent){
        list.add(textComponent);
    }

    public TextComponent getComponent(final int index){
        return list.get(index);
    }

    public void removeComponent(final TextComponent textComponent){
        list.remove(textComponent);
    }

    public int getSymbolAppearanceCount(char c){

        return list.stream().map(x -> x.getSymbolAppearanceCount(c))
                .reduce(0,(left, right) -> left + right);
    }

    public int getComponentsCount(){
        return list.size();
    }

    public int getLengthExceptPunctuation(){
        list.stream().filter(x -> !(x instanceof Symbol)).map(x -> x.getValue().length());

        return 0;
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
