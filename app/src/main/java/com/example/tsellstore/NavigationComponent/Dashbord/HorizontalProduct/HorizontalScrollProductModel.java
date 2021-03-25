package com.example.tsellstore.NavigationComponent.Dashbord.HorizontalProduct;

public class HorizontalScrollProductModel {

    private int productImage;
    private String productTitle;
    private String productDescription;
    private String productPrice;


    public HorizontalScrollProductModel(int productImage, String productTitle, String productDescription, String productPrice) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public HorizontalScrollProductModel() {
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "HorizontalScrollProductModel{" +
                "productImage=" + productImage +
                ", productTitle='" + productTitle + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice='" + productPrice + '\'' +
                '}';
    }
}