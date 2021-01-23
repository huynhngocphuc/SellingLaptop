package com.sellinglaptop.model;

import java.util.Date;
import java.util.List;

public class CartModel {
    private int id;
    private int userID;
    private int customerID;
    private double totalPrice;
    private int status;
    private int optionPay;

    public int getOptionPay() {
        return optionPay;
    }

    public void setOptionPay(int optionPay) {
        this.optionPay = optionPay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private Date buyDate;
    private List<CartItemModel> itemModelList;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public List<CartItemModel> getItemModelList() {
        return itemModelList;
    }

    public void setItemModelList(List<CartItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }
}
