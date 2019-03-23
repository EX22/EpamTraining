package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.comparator.AlphabeticOrderComparator;
import by.khomenko.training.task03.comparator.ParagraphComparator;
import by.khomenko.training.task03.comparator.SymbolAppearanceComparator;
import by.khomenko.training.task03.comparator.WordComparator;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.*;

public class Functionality {

    //TODO Two methods below probably should be united.
    public List<TextComponent>
    sortParagraphBySentencesAmount(final List<TextComponent> paragraphList) {
        Comparator<TextComponent> comparator = new ParagraphComparator();
        paragraphList.sort(comparator);

        return paragraphList;
    }

    public List<TextComponent>
    sortWordsByLength(final List<TextComponent> sentencesList) {
        Comparator<TextComponent> comparator = new WordComparator();
        sentencesList.sort(comparator);

        return sentencesList;
    }

    public void sortLexemeBySymbol(final List<TextComponent> textComponents,
                                   final char toFind) {
        Comparator<TextComponent> alphabeticOrderComparator = new AlphabeticOrderComparator();
        Comparator<TextComponent> lexemeComparator = new SymbolAppearanceComparator(toFind);
        textComponents.sort(lexemeComparator.reversed().thenComparing(alphabeticOrderComparator));

    }


    public void restoreInitialText() {

    }

}
