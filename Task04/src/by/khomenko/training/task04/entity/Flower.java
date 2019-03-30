package by.khomenko.training.task04.entity;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Flower", propOrder = {
        "name",
        "soil",
        "origin",
        "visual-parameters",
        "growing-tips",
        "multiplying"
}) // задание последовательности элементов XML

public class Flower {
    @XmlElement(required = true)
    private String name;
    @XmlAttribute(required = true)
    private String soil;
    @XmlElement(required = true)
    private String origin;
    @XmlElement(required = true)
    private VisualParameters visualParameters = new VisualParameters();
    @XmlElement(required = true)
    private GrowingTips growingTips = new GrowingTips();
    @XmlElement(required = true)
    private String multiplying;

    public Flower() {
    }

    public Flower(String name, String soil, String origin,
                  VisualParameters visualParameters, GrowingTips growingTips,
                  String multiplying) {
        this.name = name;
        this.soil = soil;
        this.origin = origin;
        this.visualParameters = visualParameters;
        this.growingTips = growingTips;
        this.multiplying = multiplying;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public String getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(String multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public String toString() {
        return "Flower{" + "name='" + name + '\'' + ", soil='" + soil
                + '\'' + ", origin='" + origin + '\'' + ", visualParameters="
                + visualParameters + ", growingTips=" + growingTips
                + ", multiplying='" + multiplying + '\'' + '}';
    }

    @XmlRootElement
    @XmlType(name = " visual-parameters ", propOrder = {

            "size",
            "leaf-color",
            "stem-color"
    })
    public static class VisualParameters { // внутренний класс
        private int size;
        private String leafColor;
        private String stemColor;

        public VisualParameters() {// необходим для маршаллизации/демаршалиизации XML
        }

        public VisualParameters(int size, String leafColor, String stemColor) {
            this.size = size;
            this.leafColor = leafColor;
            this.stemColor = stemColor;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getLeafColor() {
            return leafColor;
        }

        public void setLeafColor(String leafColor) {
            this.leafColor = leafColor;
        }

        public String getStemColor() {
            return stemColor;
        }

        public void setStemColor(String stemColor) {
            this.stemColor = stemColor;
        }

        public String toString() {
            return "\nVisualParameters:" + "\n\tSize: " + size
                    + "\n\tLeafColor: " + leafColor + "\n\tStemColor: "
                    + stemColor + "\n";
        }
    }

    @XmlRootElement
    @XmlType(name = " growing-tips ", propOrder = {

            "temperature",
            "humidity",
            "light-level",
            "water"
    })
    public static class GrowingTips { // внутренний класс
        private int temperature;
        private int humidity;
        private String lightLevel;
        private int water;

        public GrowingTips() {
        }

        public GrowingTips(int temperature, int humidity, String lightLevel,
                           int water) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.lightLevel = lightLevel;
            this.water = water;
        }

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public String getLightLevel() {
            return lightLevel;
        }

        public void setLightLevel(String lightLevel) {
            this.lightLevel = lightLevel;
        }

        public int getWater() {
            return water;
        }

        public void setWater(int water) {
            this.water = water;
        }

        @Override
        public String toString() {
            return " GrowingTips{" + "temperature=" + temperature
                    + ", humidity=" + humidity + ", lightLevel='"
                    + lightLevel + '\'' + ", water=" + water + '}';
        }
    }

}
