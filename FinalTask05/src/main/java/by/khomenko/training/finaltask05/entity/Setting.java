package by.khomenko.training.finaltask05.entity;

public class Setting extends Entity {

    private String name;
    private String value;

    public Setting(final String nameVal, final String val) {
        this.name = nameVal;
        this.value = val;
    }

    public String getName() {
        return name;
    }

    public void setName(final String nameVal) {
        this.name = nameVal;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String val) {
        this.value = val;
    }
}
