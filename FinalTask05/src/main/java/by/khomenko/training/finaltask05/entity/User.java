package by.khomenko.training.finaltask05.entity;

import java.util.List;

public class User extends Entity{

    private String name;
    //TODO Use URL instead of photo Path.
    private String photoPath = "c/photoPath.by";
    //TODO Find out what level depends on and based on.
    private Integer level;
    private String login;
    private String password;
    private Role role;
    private List<Favorite> favorites;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(final String photoPath) {
        this.photoPath = photoPath;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(final Integer level) {
        this.level = level;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(final List<Favorite> favorites) {
        this.favorites = favorites;
    }
}
