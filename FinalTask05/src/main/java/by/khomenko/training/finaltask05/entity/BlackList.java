package by.khomenko.training.finaltask05.entity;

public class BlackList extends Entity {

    private String userLogin;

    public BlackList(final String userLoginVal) {
        this.userLogin = userLoginVal;
    }

    public String getUser_login() {
        return userLogin;
    }

    public void setUser_login(final String userLoginVal) {
        this.userLogin = userLoginVal;
    }
}
