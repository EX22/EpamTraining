package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.TextComponent;

import java.util.List;

public abstract class CommonParser {

    CommonParser nextCommonParser;

    public void setNextCommonParser(final CommonParser nextCommonParserVal){
        this.nextCommonParser = nextCommonParserVal;
    }

    public abstract List<TextComponent> parseIt(String lines);
}
