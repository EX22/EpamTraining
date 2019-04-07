package by.khomenko.training.task04.dombuilder;

import by.khomenko.training.task04.entity.Flower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FlowersDOMBuilder {

    private static final Logger LOGGER = LogManager.getLogger(FlowersDOMBuilder.class);

    private Set<Flower> flowers;
    private DocumentBuilder docBuilder;

    public FlowersDOMBuilder() {
        this.flowers = new HashSet<>();
        // создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            String message = "Parser's configuration error: ";
            LOGGER.error(message, e);
        }
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public void buildSetFlowers(InputStream inputStream) {
        Document doc;
        try {
            // parsing XML-документа и создание древовидной структуры
            doc = docBuilder.parse(inputStream);
            Element root = doc.getDocumentElement();
            // получение списка дочерних элементов <flower>
            NodeList flowersList = root.getElementsByTagName("flower");
            for (int i = 0; i < flowersList.getLength(); i++) {
                Element studentElement = (Element) flowersList.item(i);
                Flower flower = buildFlower(studentElement);
                flowers.add(flower);
            }
        } catch (IOException e) {
            String message = "File or I/O error: ";
            LOGGER.error(message, e);
        } catch (SAXException e) {
            String message = "Parsing failure: ";
            LOGGER.error(message, e);
        } catch (ParseException e) {
            String message = "Date parsing error! ";
            LOGGER.error(message, e);
        }
    }

    private Flower buildFlower(Element flowerElement) throws ParseException {

        Flower flower = new Flower();

        flower.setName(getElementTextContent(flowerElement, "name"));
        flower.setSoil(getElementTextContent(flowerElement, "soil"));
        flower.setOrigin(getElementTextContent(flowerElement, "origin"));

        Flower.VisualParameters visualParameters = flower.getVisualParameters();
        Element visualParamsElement = (Element) flowerElement.getElementsByTagName("visual-parameters").item(0);

        visualParameters.setSize(Integer.parseInt(getElementTextContent(visualParamsElement, "size")));
        visualParameters.setLeafColor(getElementTextContent(visualParamsElement, "leaf-color"));
        visualParameters.setStemColor(getElementTextContent(visualParamsElement, "stem-color"));

        Flower.GrowingTips growingTips = flower.getGrowingTips();
        Element growTipsElement = (Element) flowerElement.getElementsByTagName("growing-tips").item(0);

        growingTips.setTemperature(Integer.parseInt(getElementTextContent(growTipsElement, "temperature")));
        growingTips.setHumidity(Integer.parseInt(getElementTextContent(growTipsElement, "humidity")));
        growingTips.setLightLevel(getElementTextContent(growTipsElement, "light-level"));
        growingTips.setWater(Integer.parseInt(getElementTextContent(growTipsElement, "water")));

        flower.setMultiplying(getElementTextContent(flowerElement, "multiplying"));

        flower.setPlantingDate(getPlantingDate(getElementTextContent(flowerElement, "planting-date")));

        return flower;
    }

    // получение текстового содержимого тега
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }

    private Date getPlantingDate(String stringDate) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.parse(stringDate);
    }
}
