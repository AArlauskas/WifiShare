package com.example.wifishare.ClassesLogic;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    List<String> friendList = new ArrayList<>();
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addFriend(String username)
    {
        friendList.add(username);
    }

    public List<String> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<String> friendList) {
        this.friendList = friendList;
    }
}
