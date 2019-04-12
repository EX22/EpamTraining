package by.khomenko.training.task04.saxbuilder;

import by.khomenko.training.task04.entity.Flower;
import by.khomenko.training.task04.entity.FlowerEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * SAX event handler.
 */
public class FlowerHandler extends DefaultHandler {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FlowerHandler.class);

    /**
     * Set contains collection of flower's instances.
     */
    private Set<Flower> flowers;
    /**
     * Utility Flower's variable.
     */
    private Flower current = null;
    /**
     * Flower's enumeration utility variable.
     */
    private FlowerEnum currentEnum = null;
    /**
     * Map of enum values with their corresponding String representation.
     */
    private Map<String, FlowerEnum> elementMap;

    /**
     * Constructor, sets all requirements to instantiate class' instance.
     */
    public FlowerHandler() {
        flowers = new HashSet<>();
        EnumSet<FlowerEnum> elementSet
                = EnumSet.range(FlowerEnum.NAME, FlowerEnum.PLANTING_DATE);
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
     * @param uri       nameSpace's unique name.
     * @param localName element's name without prefix.
     * @param qName     element's full name with prefix.
     * @param attrs     attributes' list
     */
    @Override
    public void startElement(final String uri, final String localName,
                             final String qName, final Attributes attrs) {
        if ("flower".equals(localName)) {
            current = new Flower();
            current.setName(attrs.getValue(0));
        } else {

            FlowerEnum temp = elementMap.get(localName);

            if (temp != null) {
                currentEnum = temp;
            }
        }
    }

    /**
     * @param uri       nameSpace's unique name.
     * @param localName element's name without prefix.
     * @param qName     element's full name with prefix.
     */
    @Override
    public void endElement(final String uri, final String localName,
                           final String qName) {
        if ("flower".equals(localName)) {
            flowers.add(current);
        }
    }

    /**
     * @param ch     character having read from tag's body.
     * @param start  start character sequence for reading.
     * @param length length character sequence foe reading.
     */
    @Override
    public void characters(final char[] ch, final int start, final int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    current.setName(s);
                    break;
                case SOIL:
                    current.setSoil(s);
                    break;
                case ORIGIN:
                    current.setOrigin(s);
                    break;
                case VISUAL_PARAMETERS:
                    break;
                case SIZE:
                    current.getVisualParameters().setSize(Integer.parseInt(s));
                    break;
                case LEAF_COLOR:
                    current.getVisualParameters().setLeafColor(s);
                    break;
                case STEM_COLOR:
                    current.getVisualParameters().setStemColor(s);
                    break;
                case GROWING_TIPS:
                    break;
                case TEMPERATURE:
                    current.getGrowingTips()
                            .setTemperature(Integer.parseInt(s));
                    break;
                case HUMIDITY:
                    current.getGrowingTips().setHumidity(Integer.parseInt(s));
                    break;
                case LIGHT_LEVEL:
                    current.getGrowingTips().setLightLevel(s);
                    break;
                case WATER:
                    current.getGrowingTips().setWater(Integer.parseInt(s));
                    break;
                case MULTIPLYING:
                    current.setMultiplying(s);
                    break;
                case PLANTING_DATE:
                    current.setPlantingDate(getPlantingDate(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(),
                            currentEnum.name());
            }
        }
        currentEnum = null;
    }


    /**
     * @param stringDate Date tag's content.
     * @return Date instance.
     */
    private Date getPlantingDate(final String stringDate) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            String message = "Date parsing error! ";
            LOGGER.error(message, e);
        }

        return date;
    }
}
