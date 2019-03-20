package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Lexeme;
import by.khomenko.training.task03.entity.TextComponent;
import by.khomenko.training.task03.entity.Word;

import java.util.ArrayList;
import java.util.List;

public class LexemeParser extends CommonParser {

    private static final String REG_EXP_LEXEME = "\\s+";

    @Override
    public List<TextComponent> parseIt(String line) {
        //if (nextCommonParser instanceof LexemeParser) {
            String[] lexemeStrings = splitter(line);
            List<TextComponent> lexemeList = new ArrayList<>();
            for (String lexemeString : lexemeStrings) {
                Lexeme lexeme = new Lexeme(parseLexeme(lexemeString));
                lexemeList.add(lexeme);
            }
            return lexemeList;
        //}
        //return new ArrayList<>();
    }

    private String[] splitter(String string){
        return string.split(REG_EXP_LEXEME);
    }

    //TODO This method needed to be changed.
    private List<TextComponent> parseLexeme(String lexemeString){
        List<TextComponent> componentList = new ArrayList<>();
        Word word = new Word(lexemeString);
        componentList.add(word);
        return componentList;
    }
}
