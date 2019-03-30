package by.khomenko.training.task04.dombuilder;

import by.khomenko.training.task04.entity.Flower;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FlowersDOMBuilder {

    private Set<Flower> flowers;
    private DocumentBuilder docBuilder;

    public FlowersDOMBuilder() {
        this.flowers = new HashSet<>();
        // создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Parser's configuration error: " + e);
        }
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    public void buildSetFlowers(String fileName) {
        Document doc;
        try {
// parsing XML-документа и создание древовидной структуры
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            // получение списка дочерних элементов <student>
            NodeList flowersList = root.getElementsByTagName("flower");
            for (int i = 0; i < flowersList.getLength(); i++) {
                Element studentElement = (Element) flowersList.item(i);
                Flower flower = buildFlower(studentElement);
                flowers.add(flower);
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }

    private Flower buildFlower(Element flowerElement) {

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

        return flower;
    }

    // получение текстового содержимого тега
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
