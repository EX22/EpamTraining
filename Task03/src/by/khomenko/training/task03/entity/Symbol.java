package by.khomenko.training.task03.entity;

import java.util.Collections;

public class Symbol extends TextComposite {

    private char symbolLeaf;

    public Symbol(char symbolLeafVal) {
        super(Collections.emptyList());
        this.symbolLeaf = symbolLeafVal;
    }

    @Override
    public String getValue() {
        return String.valueOf(symbolLeaf);
    }

    public void setSymbolLeaf(char symbolLeaf) {
        this.symbolLeaf = symbolLeaf;
    }

    public int getSymbolAppearanceCount(char c){

        if (symbolLeaf == c){
            return 1;
        }
        return 0;
    }

}
