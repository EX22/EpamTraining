package by.khomenko.training.task04.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Represents flower's instance.
 */
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
})

public class Flower {

    /**
     * Flower's name.
     */
    private String name;
    /**
     * Preferable for flower type of soil.
     */
    private String soil;
    /**
     * Where this type of flowers were first discovered.
     */
    private String origin;
    /**
     * How a flower looks like.
     */
    private VisualParameters visualParameters = new VisualParameters();
    /**
     * Condition for flower's growing.
     */
    private GrowingTips growingTips = new GrowingTips();
    /**
     * The way a flower multiply.
     */
    private String multiplying;
    /**
     * When particular flower were planted in the greenhouse.
     */
    private Date plantingDate;

    /**
     * Default constructor.
     */
    public Flower() {
    }

    /**
     * @param nameVal             parameter's name value.
     * @param soilVal             parameter's soil value.
     * @param originVal           parameter's origin value.
     * @param visualParametersVal VisualParameters instance's value.
     * @param growingTipsVal      GrowingTips instance's value.
     * @param multiplyingVal      parameter's multiplying value.
     * @param plantingDateVal     parameter's plantingDate value.
     */
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


    /**
     * @return String value of name's variable.
     */
    public String getName() {
        return name;
    }

    /**
     * @param nameVal parameter's name string value.
     */
    public void setName(final String nameVal) {
        this.name = nameVal;
    }

    /**
     * @return String value of soil's variable.
     */
    public String getSoil() {
        return soil;
    }

    /**
     * @param soilVal parameter's soil string value.
     */
    public void setSoil(final String soilVal) {
        this.soil = soilVal;
    }

    /**
     * @return String value of origin's variable.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param originVal parameter's origin string value.
     */
    public void setOrigin(final String originVal) {
        this.origin = originVal;
    }


    /**
     * @return instance of VisualParameters.
     */
    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    /**
     * @param visualParametersVal VisualParameters' instance.
     */
    public void setVisualParameters(
            final VisualParameters visualParametersVal) {
        this.visualParameters = visualParametersVal;
    }

    /**
     * @return instance of GrowingTips.
     */
    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    /**
     * @param growingTipsVal GrowingTips' instance.
     */
    public void setGrowingTips(final GrowingTips growingTipsVal) {
        this.growingTips = growingTipsVal;
    }

    /**
     * @return String value of multiplying variable.
     */
    public String getMultiplying() {
        return multiplying;
    }

    /**
     * @param multiplyingVal parameter's multiplying string value.
     */
    public void setMultiplying(final String multiplyingVal) {
        this.multiplying = multiplyingVal;
    }

    /**
     * @return instance of Date.
     */
    public Date getPlantingDate() {
        return plantingDate;
    }

    /**
     * @return String value of date.
     */
    public String getPlantingDateString() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(plantingDate);
    }

    /**
     * @param plantingDateVal Date's instance.
     */
    public void setPlantingDate(final Date plantingDateVal) {
        this.plantingDate = plantingDateVal;
    }

    /**
     * @return String representation of flower's instance.
     */
    @Override
    public String toString() {
        return "Flower:" + "\n\tname: " + name + "\n\tsoil: " + soil
                + "\n\torigin: " + origin + visualParameters
                + growingTips + "\nMultiplying: " + multiplying
                + "\nPlanting date: " + plantingDate + "\n";
    }

    /**
     * Represents flower's VisualParameters.
     */
    @XmlRootElement
    @XmlType(name = " visual-parameters ", propOrder = {

            "size",
            "leaf-color",
            "stem-color"
    })
    public static class VisualParameters {
        /**
         * Flower's size.
         */
        private int size;
        /**
         * Flower's leafs color.
         */
        private String leafColor;
        /**
         * Flower's stem color.
         */
        private String stemColor;

        /**
         * Default constructor.
         */
        public VisualParameters() {
        }

        /**
         * @param sizeVal      parameter's size value.
         * @param leafColorVal parameter's leafColor value.
         * @param stemColorVal parameter's stemColor value.
         */
        public VisualParameters(final int sizeVal, final String leafColorVal,
                                final String stemColorVal) {
            this.size = sizeVal;
            this.leafColor = leafColorVal;
            this.stemColor = stemColorVal;
        }

        /**
         * @return integer value of size variable.
         */
        public int getSize() {
            return size;
        }

        /**
         * @param sizeVal parameter's size integer value.
         */
        public void setSize(final int sizeVal) {
            this.size = sizeVal;
        }

        /**
         * @return String value of leafColor variable.
         */
        public String getLeafColor() {
            return leafColor;
        }

        /**
         * @param leafColorVal parameter's leafColor string value.
         */
        public void setLeafColor(final String leafColorVal) {
            this.leafColor = leafColorVal;
        }

        /**
         * @return String value of stemColor variable.
         */
        public String getStemColor() {
            return stemColor;
        }

        /**
         * @param stemColorVal parameter's stemColor string value.
         */
        public void setStemColor(final String stemColorVal) {
            this.stemColor = stemColorVal;
        }

        /**
         * @return String representation of VisualParameters' instance.
         */
        public String toString() {
            return "\nVisualParameters:" + "\n\tsize: " + size
                    + "\n\tleaf color: " + leafColor + "\n\tstem color: "
                    + stemColor + "\n";
        }

        /**
         * @param o an instance that needed to compare with.
         * @return Returns true if the argument is equal to current class
         * instance otherwise return false.
         */
        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof VisualParameters)) {
                return false;
            }
            VisualParameters that = (VisualParameters) o;
            return getSize() == that.getSize()
                    && getLeafColor().equals(that.getLeafColor())
                    && getStemColor().equals(that.getStemColor());
        }

        /**
         * @return Returns the hash code of a non-null argument
         * and 0 for a null argument.
         */
        @Override
        public int hashCode() {
            return Objects.hash(getSize(), getLeafColor(), getStemColor());
        }
    }

    /**
     * Represents flower's GrowingTips.
     */
    @XmlRootElement
    @XmlType(name = " growing-tips ", propOrder = {

            "temperature",
            "humidity",
            "light-level",
            "water"
    })
    public static class GrowingTips { // внутренний класс
        /**
         * Preferable temperature for flower's growing.
         */
        private int temperature;
        /**
         * Preferable humidity for flower's growing.
         */
        private int humidity;
        /**
         * Preferable amount of light for flower's growing.
         */
        private String lightLevel;
        /**
         * Preferable amount of water for flower's growing.
         */
        private int water;

        /**
         * Default constructor.
         */
        public GrowingTips() {
        }

        /**
         * @param temperatureVal parameter's temperature value.
         * @param humidityVal    parameter's humidity value.
         * @param lightLevelVal  parameter's lightLevel value.
         * @param waterVal       parameter's water value.
         */
        public GrowingTips(final int temperatureVal, final int humidityVal,
                           final String lightLevelVal, final int waterVal) {
            this.temperature = temperatureVal;
            this.humidity = humidityVal;
            this.lightLevel = lightLevelVal;
            this.water = waterVal;
        }

        /**
         * @return integer value of temperature variable.
         */
        public int getTemperature() {
            return temperature;
        }

        /**
         * @param temperatureVal parameter's temperature integer value.
         */
        public void setTemperature(final int temperatureVal) {
            this.temperature = temperatureVal;
        }

        /**
         * @return integer value of humidity variable.
         */
        public int getHumidity() {
            return humidity;
        }

        /**
         * @param humidityVal parameter's humidity integer value.
         */
        public void setHumidity(final int humidityVal) {
            this.humidity = humidityVal;
        }

        /**
         * @return String value of lightLevel variable.
         */
        public String getLightLevel() {
            return lightLevel;
        }

        /**
         * @param lightLevelVal parameter's lightLevel string value.
         */
        public void setLightLevel(final String lightLevelVal) {
            this.lightLevel = lightLevelVal;
        }

        /**
         * @return integer value of water variable.
         */
        public int getWater() {
            return water;
        }

        /**
         * @param waterVal parameter's water integer value.
         */
        public void setWater(final int waterVal) {
            this.water = waterVal;
        }

        /**
         * @return String representation of GrowingTips' instance.
         */
        @Override
        public String toString() {
            return "GrowingTips: " + "\n\ttemperature: " + temperature
                    + "\n\thumidity: " + humidity + "\n\tlightLevel: "
                    + lightLevel + "\n\twater (ml/week): "
                    + water;
        }

        /**
         * @param o an instance that needed to compare with.
         * @return Returns true if the argument is equal to current class
         * instance otherwise return false.
         */
        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof GrowingTips)) {
                return false;
            }
            GrowingTips that = (GrowingTips) o;
            return getTemperature() == that.getTemperature()
                    && getHumidity() == that.getHumidity()
                    && getWater() == that.getWater()
                    && getLightLevel().equals(that.getLightLevel());
        }

        /**
         * @return Returns the hash code of a non-null argument
         * and 0 for a null argument.
         */
        @Override
        public int hashCode() {
            return Objects.hash(getTemperature(), getHumidity(),
                    getLightLevel(), getWater());
        }
    }

    /**
     * @param o an instance that needed to compare with.
     * @return Returns true if the argument is equal to current class
     * instance otherwise return false.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Flower)) {
            return false;
        }
        Flower flower = (Flower) o;
        return getName().equals(flower.getName())
                && getSoil().equals(flower.getSoil())
                && getOrigin().equals(flower.getOrigin())
                && getVisualParameters().equals(flower.getVisualParameters())
                && getGrowingTips().equals(flower.getGrowingTips())
                && getMultiplying().equals(flower.getMultiplying())
                && getPlantingDate().equals(flower.getPlantingDate());
    }

    /**
     * @return Returns the hash code of a non-null argument
     * and 0 for a null argument.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSoil(), getOrigin(),
                getVisualParameters(), getGrowingTips(), getMultiplying(),
                getPlantingDate());
    }
}
