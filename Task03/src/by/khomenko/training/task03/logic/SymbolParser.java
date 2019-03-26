package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Symbol;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Parsing words into symbols.
 */
public class SymbolParser extends CommonParser {

    /**
     * @param line String value of line that should be parsed.
     * @return list of text components, symbols' instances.
     */
    @Override
    public List<TextComponent> parseIt(final String line) {

        List<TextComponent> symbolList = new ArrayList<>();

        for (int i = 0; i < line.length(); i++) {
            Symbol symbol = new Symbol(line.charAt(i));
            symbolList.add(symbol);
        }
        return symbolList;
    }

}
