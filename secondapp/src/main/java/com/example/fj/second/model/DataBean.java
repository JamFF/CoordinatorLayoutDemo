package com.example.fj.second.model;

public class DataBean {

    private int icon;
    private String name;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "icon=" + icon +
                ", name='" + name + '\'' +
                '}';
    }
}
