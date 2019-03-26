package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.BinaryExpression;
import by.khomenko.training.task03.entity.TextComponent;
import by.khomenko.training.task03.entity.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parsing lexemes into words, binary expressions and symbols.
 */
public class WordExpressionSymbolParser extends CommonParser {

    /**
     * Regular expression for parsing lexemes into binary expressions.
     */
    private static final String REG_EXP_BIN_EXPRESSION = "[0-9()~&|^<>]{2,}";
    /**
     * Regular expression for parsing lexemes into words.
     */
    private static final String REG_EXP_WORD = "\\w+";
    /**
     * Regular expression for parsing lexemes into punctuation symbols.
     */
    private static final String REG_EXP_PUNCTUATION
            = "[,.?!()'\"\\-]|\\n|\\.{3}";
    /**
     * Regular expression that groups regular expression declared above in
     * particular order.
     */
    private static final String REG_EXP = "(" + REG_EXP_BIN_EXPRESSION + ")|("
            + REG_EXP_WORD + ")|(" + REG_EXP_PUNCTUATION + ")";
    /**
     * Represents matched group that includes all matched elements.
     */
    private static final int MATCHED_GROUP = 0;
    /**
     * Represents binary expressions matched group.
     */
    private static final int BIN_EXPRESSION_GROUP = 1;
    /**
     * Represents words matched group.
     */
    private static final int WORD_GROUP = 2;
    /**
     * Represents punctuation expression matched group.
     */
    private static final int PUNCTUATION_GROUP = 3;

    /**
     * Instantiate the next parser's instance.
     */
    public WordExpressionSymbolParser() {
        setNextCommonParser(new SymbolParser());
    }

    /**
     * @param line String value of line that should be parsed.
     * @return list of text components, words, binary expressions and
     * punctuation symbol's instances.
     */
    @Override
    public List<TextComponent> parseIt(final String line) {

        Pattern pattern = Pattern.compile(REG_EXP);
        Matcher matcher = pattern.matcher(line);
        List<TextComponent> textComponentList = new ArrayList<>();

        while (matcher.find()) {
            List<TextComponent> symbolList =
                    getNextCommonParser().parseIt(matcher.group(MATCHED_GROUP));
            if (matcher.group(BIN_EXPRESSION_GROUP) != null) {
                textComponentList.add(new BinaryExpression(symbolList));
            } else if (matcher.group(WORD_GROUP) != null) {
                textComponentList.add(new Word(symbolList));
            } else if (matcher.group(PUNCTUATION_GROUP) != null) {
                textComponentList.addAll(symbolList);
            }
        }

        return textComponentList;
    }

}
