package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.entity.TextComponent;

import java.util.Comparator;
import java.util.List;

public class Functionality {

    //TODO Two methods below should be united.
    public List<TextComponent>
    sortParagraphBySentencesAmount(final List<TextComponent> paragraphList,
                                   Comparator<TextComponent> comparator) {
        paragraphList.sort(comparator);

        return paragraphList;
    }

    public List<TextComponent>
    sortWordsByLength(final List<TextComponent> sentencesList,
                      Comparator<TextComponent> comparator) {
        sentencesList.sort(comparator);

        return sentencesList;
    }

    public void sortLexemeBySymbol(final List<TextComponent> textComponents,
                                   final char symbol) {

        //str.chars().filter(num -> num == '$').count()


        /*public int countChar(String str, char c){
            int count = 0;

            for(int i=0; i < str.length(); i++){
                if(str.charAt(i) == c)
                count++;
            }
               return count;
        }*/
    }

    public void restoreInitialText() {

    }

}
