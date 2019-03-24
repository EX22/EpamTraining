package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.comparator.AlphabeticOrderComparator;
import by.khomenko.training.task03.comparator.ParagraphComparator;
import by.khomenko.training.task03.comparator.SymbolAppearanceComparator;
import by.khomenko.training.task03.comparator.WordComparator;
import by.khomenko.training.task03.entity.Lexeme;
import by.khomenko.training.task03.entity.Sentence;
import by.khomenko.training.task03.entity.Text;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.*;

public class Functionality {

    public TextComponent
    sortParagraphBySentencesAmount(TextComponent textComponent) {

        textComponent.sortTextComponents(new ParagraphComparator());

        return textComponent;
    }

    public TextComponent sortWordsByLength(TextComponent textComponent) {

        Comparator<TextComponent> comparator = new WordComparator();
        for (TextComponent t : textComponent.getAllTextComponentsOfType(Sentence.class)) {
            t.sortTextComponents(comparator);
        }

        return textComponent;
    }

    public List<TextComponent> sortLexemeBySymbol(final TextComponent textComponent,
                                                  final char toFind) {

        Comparator<TextComponent> alphabeticOrderComparator = new AlphabeticOrderComparator();
        Comparator<TextComponent> lexemeComparator = new SymbolAppearanceComparator(toFind);
        List<TextComponent> listToSort = textComponent.getAllTextComponentsOfType(Lexeme.class);
        listToSort.sort(lexemeComparator.reversed().thenComparing(alphabeticOrderComparator));

        return listToSort;
    }

    public String restoreText(TextComponent textComponent) {

        return textComponent.getValue();
    }

}
