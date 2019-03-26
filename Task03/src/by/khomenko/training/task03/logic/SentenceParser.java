package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.Sentence;
import by.khomenko.training.task03.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parsing paragraphs into sentences.
 */
public class SentenceParser extends CommonParser {

    /**
     * Regular expression for parsing paragraphs into sentences.
     */
    private static final String REG_EXP_SENTENCE
            = "[A-Z][^.?!]+([?!]|\\.{1,3})";

    /**
     * Instantiate the next parser's instance.
     */
    public SentenceParser() {
        setNextCommonParser(new LexemeParser());
    }

    /**
     * @param line String value of line that should be parsed.
     * @return list of text components, sentences' instances.
     */
    @Override
    public List<TextComponent> parseIt(final String line) {

        Pattern pattern = Pattern.compile(REG_EXP_SENTENCE, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(line);
        List<TextComponent> sentenceList = new ArrayList<>();
        while (matcher.find()) {
            List<TextComponent> lexemeList
                    = getNextCommonParser().parseIt(matcher.group());
            Sentence sentence = new Sentence(lexemeList);
            sentenceList.add(sentence);

        }

        return sentenceList;

    }

}
