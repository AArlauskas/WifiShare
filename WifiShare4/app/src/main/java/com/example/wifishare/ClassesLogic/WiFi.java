package com.example.wifishare.ClassesLogic;

public class WiFi {
    private String name;
    private String password;
    private String description;
    private boolean sharable;
    private String creator;

    public WiFi(String name, String password, String description, boolean sharable, String creator)
    {
        this.name = name;
        this.password = password;
        this.description = description;
        this.sharable = sharable;
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSharable() {
        return sharable;
    }

    public void setSharable(boolean sharable) {
        this.sharable = sharable;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
