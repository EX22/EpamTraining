package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Sentence;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class SentenceParser extends CommonParser {

    //TODO Find out proper regexp.
    private static final String REG_EXP_SENTENCE = "[.?!] ";

    @Override
    public List<TextComponent> parseIt(String line) {
        if (nextCommonParser instanceof LexemeParser) {
            String[] sentencesStringsArr = splitter(line);
            List<TextComponent> sentenceList = new ArrayList<>();
            for (String sentenceString : sentencesStringsArr) {
                List<TextComponent> lexemeList = nextCommonParser.parseIt(sentenceString);
                Sentence sentence = new Sentence(lexemeList);
                sentenceList.add(sentence);
            }
            return sentenceList;
        }
        return new ArrayList<>();
    }

    private String[] splitter(String string){
        return string.split(REG_EXP_SENTENCE);
    }
}
