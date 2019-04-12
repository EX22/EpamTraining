package by.khomenko.training.task04.staxbuilder;

import by.khomenko.training.task04.entity.Flower;
import by.khomenko.training.task04.entity.FlowerEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * StAX XML parser, implements Builder pattern.
 */
public class FlowersStAXBuilder {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FlowersStAXBuilder.class);

    /**
     * Set contains collection of flower's instances.
     */
    private Set<Flower> flowers = new HashSet<>();
    /**
     * Defines an abstract implementation of a factory for getting streams.
     */
    private XMLInputFactory inputFactory;
    /**
     * Map of enum values with their corresponding String representation.
     */
    private Map<String, FlowerEnum> elementMap;

    /**
     * Constructor, sets all requirements to instantiate class' instance.
     */
    public FlowersStAXBuilder() {

        inputFactory = XMLInputFactory.newInstance();
        EnumSet<FlowerEnum> elementSet
                = EnumSet.range(FlowerEnum.FLOWERS, FlowerEnum.PLANTING_DATE);
        elementMap = new HashMap<>();
        for (FlowerEnum f : elementSet) {
            elementMap.put(f.getValue(), f);
        }

    }

    /**
     * @return Set of Flower's instances.
     */
    public Set<Flower> getFlowers() {
        return flowers;
    }

    /**
     * @param inputStream stream that contains data from XML file.
     */
    public void buildSetFlowers(final InputStream inputStream) {

        XMLStreamReader reader;
        String elementName;
        try {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    elementName = reader.getLocalName();
                    if (getEnumByString(elementName) == FlowerEnum.FLOWER) {
                        Flower flower = buildFlower(reader);
                        flowers.add(flower);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            String message = "StAX parsing error! ";
            LOGGER.error(message, ex.getMessage());
        } catch (ParseException ex) {
            String message = "Date parsing error! ";
            LOGGER.error(message, ex);
        }
    }

    /**
     * @param reader XML data reader.
     * @return instance of Flower.
     * @throws XMLStreamException The base exception for unexpected processing
     *                            errors. Reports well-formedness errors
     *                            as well as unexpected processing conditions.
     * @throws ParseException     Signals that an error has been reached
     *                            unexpectedly while parsing.
     */
    private Flower buildFlower(final XMLStreamReader reader)
            throws XMLStreamException, ParseException {
        Flower flower = new Flower();

        String elementName;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                elementName = reader.getLocalName();
                switch (getEnumByString(elementName)) {

                    case NAME:
                        flower.setName(getXMLText(reader));
                        break;
                    case SOIL:
                        flower.setSoil(getXMLText(reader));
                        break;
                    case ORIGIN:
                        flower.setOrigin(getXMLText(reader));
                        break;
                    case VISUAL_PARAMETERS:
                        flower.setVisualParameters(getXMLVisualParams(reader));
                        break;
                    case GROWING_TIPS:
                        flower.setGrowingTips(getXMLGrowTips(reader));
                        break;
                    case MULTIPLYING:
                        flower.setMultiplying(getXMLText(reader));
                        break;
                    case PLANTING_DATE:
                        flower.setPlantingDate(getPlantingDate(
                                getXMLText(reader)));
                        break;
                    default:
                        break;

                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                elementName = reader.getLocalName();
                if (getEnumByString(elementName) == FlowerEnum.FLOWER) {
                    return flower;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag Flower");
    }

    /**
     * @param reader XML data reader.
     * @return instantiated flower's VisualParameters.
     * @throws XMLStreamException The base exception for unexpected processing
     *                            errors. Reports well-formedness errors
     *                            as well as unexpected processing
     *                            conditions.
     */
    private Flower.VisualParameters
    getXMLVisualParams(final XMLStreamReader reader) throws XMLStreamException {

        Flower.VisualParameters visualParams = new Flower.VisualParameters();
        int type;
        String elementName;
        while (reader.hasNext()) {
            type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                elementName = reader.getLocalName();
                switch (getEnumByString(elementName)) {
                    case SIZE:
                        visualParams.setSize(Integer
                                .parseInt(getXMLText(reader)));
                        break;
                    case LEAF_COLOR:
                        visualParams.setLeafColor(getXMLText(reader));
                        break;
                    case STEM_COLOR:
                        visualParams.setStemColor(getXMLText(reader));
                        break;
                    default:
                        break;
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                elementName = reader.getLocalName();
                if (getEnumByString(elementName)
                        == FlowerEnum.VISUAL_PARAMETERS) {
                    return visualParams;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag"
                + " visual-parameters");
    }


    /**
     * @param reader XML data reader.
     * @return instantiated flower's GrowingTips.
     * @throws XMLStreamException The base exception for unexpected processing
     *                            errors. Reports well-formedness errors
     *                            as well as unexpected processing
     *                            conditions.
     */
    private Flower.GrowingTips getXMLGrowTips(final XMLStreamReader reader)
            throws XMLStreamException {

        Flower.GrowingTips growTips = new Flower.GrowingTips();
        int type;
        String elementName;
        while (reader.hasNext()) {
            type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                elementName = reader.getLocalName();
                switch (getEnumByString(elementName)) {
                    case TEMPERATURE:
                        growTips.setTemperature(Integer
                                .parseInt(getXMLText(reader)));
                        break;
                    case HUMIDITY:
                        growTips.setHumidity(Integer
                                .parseInt(getXMLText(reader)));
                        break;
                    case LIGHT_LEVEL:
                        growTips.setLightLevel(getXMLText(reader));
                        break;
                    case WATER:
                        growTips.setWater(Integer.parseInt(getXMLText(reader)));
                        break;
                    default:
                        break;
                }
            } else if (type == XMLStreamConstants.END_ELEMENT) {
                elementName = reader.getLocalName();
                if (getEnumByString(elementName) == FlowerEnum.GROWING_TIPS) {
                    return growTips;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag growing-tips");
    }


    /**
     * @param reader XML data reader.
     * @return XML tag's content.
     * @throws XMLStreamException The base exception for unexpected processing
     *                            errors. Reports well-formedness errors
     *                            as well as unexpected processing
     *                            conditions.
     */
    private String getXMLText(final XMLStreamReader reader)
            throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    /**
     * @param value XML tag's value.
     * @return corresponding enum element.
     */
    private FlowerEnum getEnumByString(final String value) {

        return elementMap.get(value);
    }

    /**
     * @param stringDate Date tag's content.
     * @return Date instance.
     * @throws ParseException Signals that an error has been reached
     *                        unexpectedly while parsing.
     */
    private Date getPlantingDate(final String stringDate)
            throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.parse(stringDate);
    }
}
