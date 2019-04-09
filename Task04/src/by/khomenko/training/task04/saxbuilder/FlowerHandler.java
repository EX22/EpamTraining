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

public class FlowerHandler extends DefaultHandler {

    private static final Logger LOGGER
            = LogManager.getLogger(FlowerHandler.class);

    private Set<Flower> flowers;
    private Flower current = null;
    private FlowerEnum currentEnum = null;
    private Map<String, FlowerEnum> elementMap;

    public FlowerHandler() {
        flowers = new HashSet<>();
        EnumSet<FlowerEnum> elementSet
                = EnumSet.range(FlowerEnum.NAME, FlowerEnum.PLANTING_DATE);
        elementMap = new HashMap<>();
        for (FlowerEnum f : elementSet) {
            elementMap.put(f.getValue(), f);
        }
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

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

    @Override
    public void endElement(final String uri, final String localName,
                           final String qName) {
        if ("flower".equals(localName)) {
            flowers.add(current);
        }
    }

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
                    current.getGrowingTips().setTemperature(Integer.parseInt(s));
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
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }


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
