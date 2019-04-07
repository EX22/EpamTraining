package by.khomenko.training.task04.saxbuilder;

import by.khomenko.training.task04.entity.Flower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class FlowersSAXBuilder {

    private static final Logger LOGGER = LogManager.getLogger(FlowersSAXBuilder.class);

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
            String message = "SAX parser's error: ";
            LOGGER.error(message, e);
        }
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public void buildSetFlowers(InputStream inputStream) {
        try {
            // разбор XML-документа
            reader.parse(new InputSource(inputStream));
        } catch (SAXException e) {
            String message = "SAX parser's error: ";
            LOGGER.error(message, e);
        } catch (IOException e) {
            String message = "I/О stream's error: ";
            LOGGER.error(message, e);
        }

        flowers = flowerHandler.getFlowers();
    }
}
