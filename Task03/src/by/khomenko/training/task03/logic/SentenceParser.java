package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Sentence;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends CommonParser {

    private static final String REG_EXP_SENTENCE = "[A-Z][^.?!]+([?!]|\\.{1,3})";

    public SentenceParser() {
        nextCommonParser = new LexemeParser();
    }

    @Override
    public List<TextComponent> parseIt(String line) {

        Pattern pattern = Pattern.compile(REG_EXP_SENTENCE, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(line);
        List<TextComponent> sentenceList = new ArrayList<>();
        while (matcher.find()){
            List<TextComponent> lexemeList = nextCommonParser.parseIt(matcher.group());
            Sentence sentence = new Sentence(lexemeList);
            sentenceList.add(sentence);

        }

            return sentenceList;

    }

}
