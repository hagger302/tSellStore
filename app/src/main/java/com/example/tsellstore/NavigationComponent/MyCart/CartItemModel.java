package com.example.tsellstore.NavigationComponent.MyCart;

public class CartItemModel {
    public static  final int CART_ITEM = 0;
    public static  final int TOTAL_AMMOUNT = 1;
    private int type;

    ////////////////----------->>>>>>>>>cart item
    private int productImage;
    private String productTitle;
    private int freeCoupons;
    private String productPrice;
    private String cuttedPrice;
    private int productQuantity;
    private int offerApplied;
    private int couponApplied;


    public CartItemModel(int type, int productImage, String productTitle, int freeCoupons, String productPrice, String cuttedPrice, int productQuantity, int offerApplied, int couponApplied) {
        this.type = type;
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.freeCoupons = freeCoupons;
        this.productPrice = productPrice;
        this.cuttedPrice = cuttedPrice;
        this.productQuantity = productQuantity;
        this.offerApplied = offerApplied;
        this.couponApplied = couponApplied;
    }

    public CartItemModel() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getFreeCoupons() {
        return freeCoupons;
    }

    public void setFreeCoupons(int freeCoupons) {
        this.freeCoupons = freeCoupons;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCuttedPrice() {
        return cuttedPrice;
    }

    public void setCuttedPrice(String cuttedPrice) {
        this.cuttedPrice = cuttedPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getOfferApplied() {
        return offerApplied;
    }

    public void setOfferApplied(int offerApplied) {
        this.offerApplied = offerApplied;
    }

    public int getCouponApplied() {
        return couponApplied;
    }

    public void setCouponApplied(int couponApplied) {
        this.couponApplied = couponApplied;
    }

    @Override
    public String toString() {
        return "CartItemModel{" +
                "type=" + type +
                ", productImage=" + productImage +
                ", productTitle='" + productTitle + '\'' +
                ", freeCoupons=" + freeCoupons +
                ", productPrice='" + productPrice + '\'' +
                ", cuttedPrice='" + cuttedPrice + '\'' +
                ", productQuantity=" + productQuantity +
                ", offerApplied=" + offerApplied +
                ", couponApplied=" + couponApplied +
                '}';
    }


    //////////////////////////--------->>>>>>>>>>> total ammount
    private String totalItems;
    private String totalItemAmount;
    private String deliveryPrice;
    private String savedAmount;
    private String totalAmount;

    public CartItemModel(int type, String totalItems, String totalItemAmount, String deliveryPrice, String savedAmount,
                         String totalAmount) {
        this.type = type;
        this.totalItems = totalItems;
        this.totalItemAmount = totalItemAmount;
        this.deliveryPrice = deliveryPrice;
        this.savedAmount = savedAmount;
        this.totalAmount = totalAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getTotalItemAmount() {
        return totalItemAmount;
    }

    public void setTotalItemAmount(String totalItemAmount) {
        this.totalItemAmount = totalItemAmount;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(String savedAmount) {
        this.savedAmount = savedAmount;
    }
}
