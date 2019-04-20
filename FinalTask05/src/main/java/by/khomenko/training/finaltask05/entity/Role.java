package by.khomenko.training.finaltask05.entity;

public enum Role {
    ADMINISTRATOR("admin"),
    USER("user");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
    }
}
