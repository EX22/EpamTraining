package by.khomenko.training.finaltask05.entity;

public enum Role {
    ADMINISTRATOR("admin"),
    USER("user");

    private String name;

    Role(final String nameVal) {
        this.name = nameVal;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByIdentity(final Integer identity) {
        return Role.values()[identity];
    }
}
