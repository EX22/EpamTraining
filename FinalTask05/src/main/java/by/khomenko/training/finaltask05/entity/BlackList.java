package by.khomenko.training.finaltask05.entity;

import java.util.Objects;

public class BlackList extends Entity {

    //TODO Remove login.
    private Integer userId;
    private String userLogin;

    public BlackList(Integer userId) {
        this.userId = userId;
    }

    public BlackList(Integer userId, String userLogin) {
        this.userId = userId;
        this.userLogin = userLogin;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlackList)) return false;
        if (!super.equals(o)) return false;
        BlackList blackList = (BlackList) o;
        return getUserId().equals(blackList.getUserId()) &&
                getUserLogin().equals(blackList.getUserLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUserId(), getUserLogin());
    }
}
