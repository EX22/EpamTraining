package by.khomenko.training.finaltask05.entity;

import java.util.Objects;

public class Setting extends Entity {

    private String name;
    private String value;

    public Setting() {
    }

    public Setting(String nameVal, String val) {
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Setting)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Setting setting = (Setting) o;
        return getName().equals(setting.getName())
                && getValue().equals(setting.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getValue());
    }
}
