package by.khomenko.training.task03.entity;

import java.util.List;
import java.util.Objects;

public class Word implements TextComponent {
    //TODO Do not use String here, change to char collection.
    private String wordLeaf;

    public Word(final String wordLeafVal) {
        this.wordLeaf = wordLeafVal;
    }

    @Override
    public String getValue() {
        return wordLeaf;
    }

    @Override
    public List<TextComponent> getComponentsList() {
        //TODO Probably some exception thrown here.
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        return wordLeaf.equals(word.wordLeaf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordLeaf);
    }
}
