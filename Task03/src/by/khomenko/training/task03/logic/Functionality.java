package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.comparator.AlphabeticOrderComparator;
import by.khomenko.training.task03.comparator.ParagraphComparator;
import by.khomenko.training.task03.comparator.SymbolAppearanceComparator;
import by.khomenko.training.task03.comparator.WordComparator;
import by.khomenko.training.task03.entity.Lexeme;
import by.khomenko.training.task03.entity.Sentence;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.Comparator;
import java.util.List;

/**
 * Performs functionality that required in the task conditions.
 * In particular :
 * sort paragraphs by sentences amount, sort words in each sentences by length,
 * sort lexemes in a whole text in descending order according to given symbol
 * appearance,
 * restore initial text with calculated binary expressions.
 */
public class Functionality {

    /**
     * @param textComponent TextComponent's entity that's content is needed
     *                      to be sort.
     * @return TextComponent's entity which contains list of paragraphs sorted
     * by sentences amount.
     */
    public TextComponent
    sortParagraphBySentencesAmount(final TextComponent textComponent) {

        textComponent.sortTextComponents(new ParagraphComparator());

        return textComponent;
    }

    /**
     * This sort method counts '\n' symbol as a word's part.
     *
     * @param textComponent TextComponent's entity that's content is needed
     *                      to be sort.
     * @return TextComponent's entity which contains list of words sorted by
     * length.
     */
    public TextComponent sortWordsByLength(final TextComponent textComponent) {

        Comparator<TextComponent> comparator = new WordComparator();
        for (TextComponent t : textComponent.
                getAllTextComponentsOfType(Sentence.class)) {
            t.sortTextComponents(comparator);
        }

        return textComponent;
    }

    /**
     * @param textComponent TextComponent's entity that's content is needed
     *                      *                      to be sort.
     * @param toFind        char value of symbol according to appearance of
     *                      which lexemes will be sorted.
     * @return List of TextComponents, lexemes in particular, sorted in
     * descending order according to appearance of required symbol and then
     * in alphabetic order if symbol appearance in them are equal.
     */
    public List<TextComponent>
    sortLexemeBySymbol(final TextComponent textComponent, final char toFind) {

        Comparator<TextComponent> alphabeticOrderComparator
                = new AlphabeticOrderComparator();
        Comparator<TextComponent> lexemeComparator
                = new SymbolAppearanceComparator(toFind);
        List<TextComponent> listToSort
                = textComponent.getAllTextComponentsOfType(Lexeme.class);
        listToSort.sort(lexemeComparator.reversed().
                thenComparing(alphabeticOrderComparator));

        return listToSort;
    }

    /**
     * @param textComponent TextComponent's entity that's content is needed
     *                      to be restored.
     * @return String value of restored TextComponent's entity. The whole text
     * in this particular task requirements.
     */
    public String restoreText(final TextComponent textComponent) {

        return textComponent.getValue();
    }

}
