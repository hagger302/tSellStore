package com.example.tsellstore.NavigationComponent.Dashbord.ViewPager;

public class SliderModel {

    private String banner;
    private String backgroundColor;

    public SliderModel(String banner, String backgroundColor) {
        this.banner = banner;
        this.backgroundColor = backgroundColor;
    }

    public SliderModel() {
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public String toString() {
        return "SliderModel{" +
                "banner=" + banner +
                ", backgroundColor='" + backgroundColor + '\'' +
                '}';
    }
}
