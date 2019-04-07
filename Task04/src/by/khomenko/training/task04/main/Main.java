package by.khomenko.training.task04.main;

import by.khomenko.training.task04.dombuilder.FlowersDOMBuilder;
import by.khomenko.training.task04.saxbuilder.FlowersSAXBuilder;
import by.khomenko.training.task04.staxbuilder.FlowersStAXBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String FILE_PATH = "data/flowers.xml";


    public static void main(String[] args) {

        try (InputStream inputStream = new FileInputStream(new File(FILE_PATH))) {

            FlowersSAXBuilder saxBuilder = new FlowersSAXBuilder();
            saxBuilder.buildSetFlowers(inputStream);
            System.out.println(saxBuilder.getFlowers());

            /*FlowersDOMBuilder domBuilder = new FlowersDOMBuilder();
            domBuilder.buildSetFlowers(inputStream);
            System.out.println(domBuilder.getFlowers());*/

            /*FlowersStAXBuilder staxBuilder = new FlowersStAXBuilder();
            staxBuilder.buildSetFlowers(inputStream);
            System.out.println(staxBuilder.getFlowers());*/

        } catch (IOException e) {
            String message = "File or I/O error: ";
            LOGGER.error(message, e);
        }


    }
}
