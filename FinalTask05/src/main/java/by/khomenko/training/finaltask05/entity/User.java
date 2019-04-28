package by.khomenko.training.finaltask05.entity;

import java.util.List;
import java.util.Objects;

public class User extends Entity {

    private String name;
    //TODO Use URL instead of photo Path.
    private String photoPath = "c/photoPath.by";

    /**
     * User's level depends on quantity of images they recognized.
     * For demonstration purposes the one recognized image gains level up
     * to one grade.
     */
    private Integer level;
    private String login;
    private String password;
    private Role role;
    private List<Favorite> favorites;

    public String getName() {
        return name;
    }

    public void setName(final String nameVal) {
        this.name = nameVal;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(final String photoPathVal) {
        this.photoPath = photoPathVal;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(final Integer levelVal) {
        this.level = levelVal;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String loginVal) {
        this.login = loginVal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String passwordVal) {
        this.password = passwordVal;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role roleVal) {
        this.role = roleVal;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(final List<Favorite> favorites) {
        this.favorites = favorites;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        User user = (User) o;
        return getName().equals(user.getName())
                && getPhotoPath().equals(user.getPhotoPath())
                && getLevel().equals(user.getLevel())
                && getLogin().equals(user.getLogin())
                && getPassword().equals(user.getPassword())
                && getRole() == user.getRole()
                && getFavorites().equals(user.getFavorites());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getPhotoPath(),
                getLevel(), getLogin(), getPassword(), getRole(),
                getFavorites());
    }
}
