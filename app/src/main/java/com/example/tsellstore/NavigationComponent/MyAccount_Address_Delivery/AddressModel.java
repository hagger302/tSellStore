package com.example.tsellstore.NavigationComponent.MyAccount_Address_Delivery;

public class AddressModel {

    private String full_name;
    private String address;
    private String pincode;
    private Boolean selected;


    public AddressModel(String full_name, String address, String pincode, Boolean selected) {
        this.full_name = full_name;
        this.address = address;
        this.pincode = pincode;
        this.selected = selected;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }


    @Override
    public String toString() {
        return "AddressModel{" +
                "full_name='" + full_name + '\'' +
                ", address='" + address + '\'' +
                ", pincode='" + pincode + '\'' +
                ", selected=" + selected +
                '}';
    }
}
