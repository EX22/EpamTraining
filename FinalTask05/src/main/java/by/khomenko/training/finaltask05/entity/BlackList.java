package by.khomenko.training.finaltask05.entity;

public class BlackList extends Entity {

    private String userLogin;

    public BlackList(final String userLoginVal) {
        this.userLogin = userLoginVal;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(final String userLoginVal) {
        this.userLogin = userLoginVal;
    }
}
