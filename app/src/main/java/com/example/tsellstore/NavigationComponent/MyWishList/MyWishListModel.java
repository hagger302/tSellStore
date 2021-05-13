package com.example.tsellstore.NavigationComponent.MyWishList;

public class MyWishListModel {

    private String productImage;
    private String productTitle;
    private long freeCupon;
    private String rating;
    private long totalRating;
    private String productPrice;
    private String cuttedtPrice;
    private boolean COD;

    public MyWishListModel(String productImage, String productTitle, long freeCupon, String rating, long totalRating, String productPrice, String cuttedtPrice, boolean COD) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.freeCupon = freeCupon;
        this.rating = rating;
        this.totalRating = totalRating;
        this.productPrice = productPrice;
        this.cuttedtPrice = cuttedtPrice;
        this.COD = COD;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public long getFreeCupon() {
        return freeCupon;
    }

    public void setFreeCupon(long freeCupon) {
        this.freeCupon = freeCupon;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public long getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(long totalRating) {
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

    public boolean getCOD() {
        return COD;
    }

    public void setCOD(boolean COD) {
        this.COD = COD;
    }

    @Override
    public String toString() {
        return "MyWishListModel{" +
                "productImage='" + productImage + '\'' +
                ", productTitle='" + productTitle + '\'' +
                ", freeCupon=" + freeCupon +
                ", rating='" + rating + '\'' +
                ", totalRating=" + totalRating +
                ", productPrice='" + productPrice + '\'' +
                ", cuttedtPrice='" + cuttedtPrice + '\'' +
                ", COD=" + COD +
                '}';
    }
}
