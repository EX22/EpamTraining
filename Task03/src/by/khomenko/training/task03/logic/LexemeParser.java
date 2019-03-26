package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Lexeme;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parsing sentences into lexemes.
 */
public class LexemeParser extends CommonParser {

    /**
     * Regular expression for parsing sentences into lexemes.
     */
    private static final String REG_EXP_LEXEME = "[^\\s]+(\\s+|\\z)";

    /**
     * Instantiate the next parser's instance.
     */
    public LexemeParser() {
        setNextCommonParser(new WordExpressionSymbolParser());
    }

    /**
     * @param line String value of line that should be parsed.
     * @return list of text components, lexeme's instances.
     */
    @Override
    public List<TextComponent> parseIt(final String line) {

        Pattern pattern = Pattern.compile(REG_EXP_LEXEME, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(line);
        List<TextComponent> lexemeList = new ArrayList<>();
        while (matcher.find()) {
            List<TextComponent> wordExpressionSymbolList
                    = getNextCommonParser().parseIt(matcher.group());
            Lexeme lexeme = new Lexeme(wordExpressionSymbolList);
            lexemeList.add(lexeme);

        }
        return lexemeList;

    }

}
