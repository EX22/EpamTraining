package by.khomenko.training.task04.saxbuilder;

import by.khomenko.training.task04.entity.Flower;
import by.khomenko.training.task04.entity.FlowerEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class FlowerHandler extends DefaultHandler {

    private Set<Flower> flowers;
    private Flower current = null;
    private FlowerEnum currentEnum = null;
    private Map<String, FlowerEnum> elementMap;

    public FlowerHandler() {
        flowers = new HashSet<>();
        EnumSet<FlowerEnum> elementSet = EnumSet.range(FlowerEnum.NAME, FlowerEnum.MULTIPLYING);
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
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
