package com.example.tsellstore.NavigationComponent.MyWishList;

public class MyWishListModel {

    private int productImage;
    private String productTitle;
    private int freeCupon;
    private String rating;
    private int totalRating;
    private String productPrice;
    private String cuttedtPrice;
    private String paymentMethod;

    public MyWishListModel(int productImage, String productTitle, int freeCupon, String rating, int totalRating, String productPrice, String cuttedtPrice, String paymentMethod) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.freeCupon = freeCupon;
        this.rating = rating;
        this.totalRating = totalRating;
        this.productPrice = productPrice;
        this.cuttedtPrice = cuttedtPrice;
        this.paymentMethod = paymentMethod;
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

    public int getFreeCupon() {
        return freeCupon;
    }

    public void setFreeCupon(int freeCupon) {
        this.freeCupon = freeCupon;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCuttedtPrice() {
        return cuttedtPrice;
    }

    public void setCuttedtPrice(String cuttedtPrice) {
        this.cuttedtPrice = cuttedtPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "MyWishListModel{" +
                "productImage=" + productImage +
                ", productTitle='" + productTitle + '\'' +
                ", freeCupon=" + freeCupon +
                ", rating='" + rating + '\'' +
                ", totalRating=" + totalRating +
                ", productPrice='" + productPrice + '\'' +
                ", cuttedtPrice='" + cuttedtPrice + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
