package by.khomenko.training.task03.entity;

import java.util.List;
import java.util.Objects;

public class Symbol implements TextComponent {
    //TODO Add char value field, remove String from here
    private String symbolLeaf;

    public Symbol(final String symbolLeafVal) {
        this.symbolLeaf = symbolLeafVal;
    }
    //TODO return String.valueOf() here.
    @Override
    public String getValue() {
        return symbolLeaf;
    }

    @Override
    public List<TextComponent> getComponentsList() {
        //TODO Probably some exception thrown here. Return an empty list here instead of null.
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Symbol)) return false;
        Symbol symbol = (Symbol) o;
        return symbolLeaf.equals(symbol.symbolLeaf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbolLeaf);
    }
}
