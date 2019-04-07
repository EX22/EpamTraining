package by.khomenko.training.task04.saxbuilder;

import by.khomenko.training.task04.entity.Flower;
import by.khomenko.training.task04.entity.FlowerEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FlowerHandler extends DefaultHandler {

    private static final Logger LOGGER = LogManager.getLogger(FlowerHandler.class);

    private Set<Flower> flowers;
    private Flower current = null;
    private FlowerEnum currentEnum = null;
    private Map<String, FlowerEnum> elementMap;

    public FlowerHandler() {
        flowers = new HashSet<>();
        EnumSet<FlowerEnum> elementSet = EnumSet.range(FlowerEnum.NAME, FlowerEnum.PLANTING_DATE);
        elementMap = new HashMap<>();
        for (FlowerEnum f : elementSet) {
            elementMap.put(f.getValue(), f);
        }
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
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
    public void endElement(String uri, String localName, String qName) {
        if ("flower".equals(localName)) {
            flowers.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
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
                    //TODO Consider to put it in outer getVisualParameters method!
                    //current.setVisualParameters();
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
                    //TODO Consider to put it in outer getGrowingTips method!
                    //current.setGrowingTips();
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
    //TODO Not used method! Needed to be changed in case of using.
    private Flower.VisualParameters getVisualParameters(FlowerEnum currentEnum, String s){

        Flower.VisualParameters visualParams = new Flower.VisualParameters();
        if (currentEnum != null){
            switch (currentEnum){
                case SIZE:
                    visualParams.setSize(Integer.parseInt(s));
                    break;
                case LEAF_COLOR:
                    visualParams.setLeafColor(s);
                    break;
                case STEM_COLOR:
                    visualParams.setStemColor(s);
                    break;
                default:
                    break;
            }
        }
        return visualParams;
    }

    //TODO Not used method! Needed to be changed in case of using.
    private Flower.GrowingTips getGrowingTips(FlowerEnum currentEnum, String s){

        Flower.GrowingTips growingTips = new Flower.GrowingTips();
        if (currentEnum != null){
            switch (currentEnum){
                case TEMPERATURE:
                    growingTips.setTemperature(Integer.parseInt(s));
                    break;
                case HUMIDITY:
                    growingTips.setHumidity(Integer.parseInt(s));
                    break;
                case LIGHT_LEVEL:
                    growingTips.setLightLevel(s);
                    break;
                case WATER:
                    growingTips.setWater(Integer.parseInt(s));
                    break;
                default:
                    break;
            }
        }
        return growingTips;
    }

    private Date getPlantingDate(String stringDate) {
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
