package by.khomenko.training.task04.entity;

import javax.xml.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Flower", propOrder = {
        "name",
        "soil",
        "origin",
        "visual-parameters",
        "growing-tips",
        "multiplying",
        "planting-date"
}) // задание последовательности элементов XML

public class Flower {

    private String name;
    private String soil;
    private String origin;
    private VisualParameters visualParameters = new VisualParameters();
    private GrowingTips growingTips = new GrowingTips();
    private String multiplying;
    private Date plantingDate;

    public Flower() {
    }

    public Flower(String name, String soil, String origin,
                  VisualParameters visualParameters, GrowingTips growingTips,
                  String multiplying, Date plantingDate) {
        this.name = name;
        this.soil = soil;
        this.origin = origin;
        this.visualParameters = visualParameters;
        this.growingTips = growingTips;
        this.multiplying = multiplying;
        this.plantingDate = plantingDate;
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

    public Date getPlantingDate() {
        return plantingDate;
    }

    public String getPlantingDateString(){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(plantingDate);
    }

    public void setPlantingDate(Date plantingDate) {
        this.plantingDate = plantingDate;
    }

    @Override
    public String toString() {
        return "Flower:" + "\n\tname: " + name + "\n\tsoil: " + soil
                + "\n\torigin: " + origin + visualParameters
                + growingTips + "\nMultiplying: " + multiplying
                + "\nPlanting date: " + plantingDate + "\n";
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
            return "\nVisualParameters:" + "\n\tsize: " + size
                    + "\n\tleaf color: " + leafColor + "\n\tstem color: "
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
            return "GrowingTips: " + "\n\ttemperature: " + temperature
                    + "\n\thumidity: " + humidity + "\n\tlightLevel: "
                    + lightLevel + "\n\twater (ml/week): "
                    + water;
        }
    }

}
