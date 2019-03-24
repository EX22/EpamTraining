package by.khomenko.training.task03.entity;

public class Symbol extends TextComposite {

    private char symbolLeaf;

    public Symbol(char symbolLeafVal) {
        super();
        this.symbolLeaf = symbolLeafVal;
    }

    @Override
    public String getValue() {
        return String.valueOf(symbolLeaf);
    }

    @Override
    public String getTextValue() {
        return String.valueOf(symbolLeaf);
    }

    @Override
    public int getSymbolAppearanceCount(char c){

        if (symbolLeaf == c){
            return 1;
        }
        return 0;
    }

}
