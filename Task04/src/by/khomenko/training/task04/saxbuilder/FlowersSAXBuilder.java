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

/**
 * SAX XML parser, implements Builder pattern.
 */
public class FlowersSAXBuilder {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FlowersSAXBuilder.class);

    /**
     * Set contains collection of flower's instances.
     */
    private Set<Flower> flowers;

    /**
     * SAX event handler.
     */
    private FlowerHandler flowerHandler;
    /**
     * Reader for XML document.
     */
    private XMLReader reader;

    /**
     * Constructor, sets all requirements to instantiate class' instance,
     * including factory for creating an XML reader.
     */
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

    /**
     * @return Set Flower's instances.
     */
    public Set<Flower> getFlowers() {
        return flowers;
    }

    /**
     * @param inputStream stream that contains data from XML file.
     */
    public void buildSetFlowers(final InputStream inputStream) {
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
