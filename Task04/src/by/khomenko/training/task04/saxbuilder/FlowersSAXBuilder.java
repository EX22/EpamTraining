package by.khomenko.training.task04.saxbuilder;

import by.khomenko.training.task04.entity.Flower;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class FlowersSAXBuilder {
    private Set<Flower> flowers;
    private FlowerHandler flowerHandler;
    private XMLReader reader;

    public FlowersSAXBuilder() {
        // создание SAX-анализатора
        flowerHandler = new FlowerHandler();
        try {
            // создание объекта-обработчика
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(flowerHandler);
        } catch (SAXException e) {
            System.err.print("SAX parser's error: " + e);
        }
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public void buildSetFlowers(String fileName) {
        try {
            // разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("SAX parser's error: " + e);
        } catch (IOException e) {
            System.err.print("I/О stream's error: " + e);
        }
        flowers = flowerHandler.getFlowers();
    }
}
