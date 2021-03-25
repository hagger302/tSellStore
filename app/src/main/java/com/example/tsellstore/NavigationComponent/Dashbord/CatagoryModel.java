package com.example.tsellstore.NavigationComponent.Dashbord;

public class CatagoryModel {

    /**
     *  we want to load our data thorough our database -> firebase
     *  we load a picture by link for the reason we create link and by the link we load images through firebase
     */

    private String CatagoryLink;
    private String CatagoryName;

    public CatagoryModel(String catagoryLink, String catagoryName) {
        CatagoryLink = catagoryLink;
        CatagoryName = catagoryName;
    }

    public CatagoryModel() {
    }

    public String getCatagoryLink() {
        return CatagoryLink;
    }

    public void setCatagoryLink(String catagoryLink) {
        CatagoryLink = catagoryLink;
    }

    public String getCatagoryName() {
        return CatagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        CatagoryName = catagoryName;
    }

    @Override
    public String toString() {
        return "CatagoryModel{" +
                "CatagoryLink='" + CatagoryLink + '\'' +
                ", CatagoryName='" + CatagoryName + '\'' +
                '}';
    }
}
