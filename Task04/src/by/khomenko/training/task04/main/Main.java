package by.khomenko.training.task04.main;

import by.khomenko.training.task04.dombuilder.FlowersDOMBuilder;
import by.khomenko.training.task04.saxbuilder.FlowersSAXBuilder;
import by.khomenko.training.task04.staxbuilder.FlowersStAXBuilder;

public class Main {
    public static void main(String[] args) {

        /*FlowersSAXBuilder saxBuilder = new FlowersSAXBuilder();
        saxBuilder.buildSetFlowers("data/flowers.xml");
        System.out.println(saxBuilder.getFlowers());*/

        FlowersDOMBuilder domBuilder = new FlowersDOMBuilder();
        domBuilder.buildSetFlowers("data/flowers.xml");
        System.out.println(domBuilder.getFlowers());

        /*FlowersStAXBuilder staxBuilder = new FlowersStAXBuilder();
        staxBuilder.buildSetFlowers("data/flowers.xml");
        System.out.println(staxBuilder.getFlowers());*/
    }
}
