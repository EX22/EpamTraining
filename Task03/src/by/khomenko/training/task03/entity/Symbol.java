package by.khomenko.training.task03.entity;

import java.util.Objects;

/**
 * Represents a symbol's instance, a terminal node of text's composite
 * structure.
 */
public class Symbol extends TextComposite {

    /**
     * Char value of symbol.
     */
    private char symbolLeaf;

    /**
     * @param symbolLeafVal value of symbol to instantiate a class instance.
     */
    public Symbol(final char symbolLeafVal) {
        super();
        this.symbolLeaf = symbolLeafVal;
    }

    /**
     * @return String value of symbol.
     */
    @Override
    public String getValue() {
        return String.valueOf(symbolLeaf);
    }

    /**
     * @return String value of symbol.
     */
    @Override
    public String getTextValue() {
        return String.valueOf(symbolLeaf);
    }

    /**
     * @param c required symbol to find.
     * @return int value of " 1 " if passed to method character is equal to
     * char value of Symbol instance.
     */
    @Override
    public int getSymbolAppearanceCount(final char c) {

        if (symbolLeaf == c) {
            return 1;
        }
        return 0;
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
        if (!(o instanceof Symbol)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Symbol symbol = (Symbol) o;
        return symbolLeaf == symbol.symbolLeaf;
    }

    /**
     * @return Hash code of this instance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), symbolLeaf);
    }
}
