package com.example.sparemate.Others;

public class AddressModel {
    private String name;
    private String fullAddress;
    private String number;
    private boolean selected;

    public AddressModel(String name, String fullAddress, String number, boolean selected) {
        this.name = name;
        this.fullAddress = fullAddress;
        this.number = number;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getNumber() {
        return number;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}

