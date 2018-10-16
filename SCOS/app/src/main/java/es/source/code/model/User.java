package es.source.code.model;

import java.io.Serializable;

/**
 * Created by Wanghongbo on 2018/10/10.
 */

public class User implements Serializable {
    private String userName;
    private String password;
    private Boolean isOldUser;

    public User(String name, String pwd, Boolean bool) {
        this.userName = name;
        this.password = pwd;
        this.isOldUser = bool;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsOldUser() {
        return isOldUser;
    }

    public void setOldUser(Boolean oldUser) {
        this.isOldUser = oldUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
