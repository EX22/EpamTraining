package by.khomenko.training.task04.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
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

    public Flower(final String nameVal, final String soilVal,
                  final String originVal,
                  final VisualParameters visualParametersVal,
                  final GrowingTips growingTipsVal,
                  final String multiplyingVal, final Date plantingDateVal) {
        this.name = nameVal;
        this.soil = soilVal;
        this.origin = originVal;
        this.visualParameters = visualParametersVal;
        this.growingTips = growingTipsVal;
        this.multiplying = multiplyingVal;
        this.plantingDate = plantingDateVal;
    }


    public String getName() {
        return name;
    }

    public void setName(final String nameVal) {
        this.name = nameVal;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(final String soilVal) {
        this.soil = soilVal;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String originVal) {
        this.origin = originVal;
    }


    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(
            final VisualParameters visualParametersVal) {
        this.visualParameters = visualParametersVal;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(final GrowingTips growingTipsVal) {
        this.growingTips = growingTipsVal;
    }

    public String getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(final String multiplyingVal) {
        this.multiplying = multiplyingVal;
    }

    public Date getPlantingDate() {
        return plantingDate;
    }

    public String getPlantingDateString() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(plantingDate);
    }

    public void setPlantingDate(final Date plantingDateVal) {
        this.plantingDate = plantingDateVal;
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
    public static class VisualParameters {
        private int size;
        private String leafColor;
        private String stemColor;

        public VisualParameters() {
        }

        public VisualParameters(final int sizeVal, final String leafColorVal,
                                final String stemColorVal) {
            this.size = sizeVal;
            this.leafColor = leafColorVal;
            this.stemColor = stemColorVal;
        }

        public int getSize() {
            return size;
        }

        public void setSize(final int sizeVal) {
            this.size = sizeVal;
        }

        public String getLeafColor() {
            return leafColor;
        }

        public void setLeafColor(final String leafColorVal) {
            this.leafColor = leafColorVal;
        }

        public String getStemColor() {
            return stemColor;
        }

        public void setStemColor(final String stemColorVal) {
            this.stemColor = stemColorVal;
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

        public GrowingTips(final int temperatureVal, final int humidityVal,
                           final String lightLevelVal, final int waterVal) {
            this.temperature = temperatureVal;
            this.humidity = humidityVal;
            this.lightLevel = lightLevelVal;
            this.water = waterVal;
        }

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(final int temperatureVal) {
            this.temperature = temperatureVal;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(final int humidityVal) {
            this.humidity = humidityVal;
        }

        public String getLightLevel() {
            return lightLevel;
        }

        public void setLightLevel(final String lightLevelVal) {
            this.lightLevel = lightLevelVal;
        }

        public int getWater() {
            return water;
        }

        public void setWater(final int waterVal) {
            this.water = waterVal;
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
