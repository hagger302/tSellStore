package com.example.tsellstore;

public class MyRewardModel {

    private String coupon_title_reward;
    private String coupon_validitydate_reward;
    private String coupon_body_reward;

    public MyRewardModel(String coupon_title_reward, String coupon_validitydate_reward, String coupon_body_reward) {
        this.coupon_title_reward = coupon_title_reward;
        this.coupon_validitydate_reward = coupon_validitydate_reward;
        this.coupon_body_reward = coupon_body_reward;
    }

    public String getCoupon_title_reward() {
        return coupon_title_reward;
    }

    public void setCoupon_title_reward(String coupon_title_reward) {
        this.coupon_title_reward = coupon_title_reward;
    }

    public String getCoupon_validitydate_reward() {
        return coupon_validitydate_reward;
    }

    public void setCoupon_validitydate_reward(String coupon_validitydate_reward) {
        this.coupon_validitydate_reward = coupon_validitydate_reward;
    }

    public String getCoupon_body_reward() {
        return coupon_body_reward;
    }

    public void setCoupon_body_reward(String coupon_body_reward) {
        this.coupon_body_reward = coupon_body_reward;
    }

    @Override
    public String toString() {
        return "MyRewardModel{" +
                "coupon_title_reward='" + coupon_title_reward + '\'' +
                ", coupon_validitydate_reward='" + coupon_validitydate_reward + '\'' +
                ", coupon_body_reward='" + coupon_body_reward + '\'' +
                '}';
    }
}
