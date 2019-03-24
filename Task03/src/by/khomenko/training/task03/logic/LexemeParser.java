package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Lexeme;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends CommonParser {

    private static final String REG_EXP_LEXEME = "[^\\s]+(\\s+|\\z)";

    public LexemeParser() {
        nextCommonParser = new WordExpressionSymbolParser();
    }

    @Override
    public List<TextComponent> parseIt(String line) {

        Pattern pattern = Pattern.compile(REG_EXP_LEXEME, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(line);
        List<TextComponent> lexemeList = new ArrayList<>();
        while (matcher.find()) {
            List<TextComponent> wordExpressionSymbolList = nextCommonParser.parseIt(matcher.group());
            Lexeme lexeme = new Lexeme(wordExpressionSymbolList);
            lexemeList.add(lexeme);

        }
        return lexemeList;

    }

}
