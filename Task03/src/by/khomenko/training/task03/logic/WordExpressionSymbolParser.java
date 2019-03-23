package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.BinaryExpression;
import by.khomenko.training.task03.entity.Symbol;
import by.khomenko.training.task03.entity.TextComponent;
import by.khomenko.training.task03.entity.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordExpressionSymbolParser extends CommonParser {

    private static final String REG_EXP_BIN_EXPRESSION = "[0-9()~&|^<>]{2,}";
    private static final String REG_EXP_WORD = "\\w+";
    private static final String REG_EXP_PUNCTUATION = "[,.?!()'\"\\-]|\\n|\\.{3}";
    private static final String REG_EXP_SYMBOLS = ".+";
    private static final String REG_EXP = "(" + REG_EXP_BIN_EXPRESSION + ")|("
            + REG_EXP_WORD + ")|(" + REG_EXP_PUNCTUATION + ")";

    public WordExpressionSymbolParser() {
        nextCommonParser = new SymbolParser();
    }

    @Override
    public List<TextComponent> parseIt(String line) {

        Pattern pattern = Pattern.compile(REG_EXP);
        Matcher matcher = pattern.matcher(line);
        List<TextComponent> textComponentList = new ArrayList<>();

        while (matcher.find()) {
            List<TextComponent> symbolList = nextCommonParser.parseIt(matcher.group(0));

            if (matcher.group(1) != null) {

                textComponentList.add(new BinaryExpression(symbolList));

            }
            if (matcher.group(2) != null) {

                textComponentList.add(new Word(symbolList));

            }
            if (matcher.group(3) != null) {

                textComponentList.addAll(symbolList);
            }

        }

        return textComponentList;
    }

}
