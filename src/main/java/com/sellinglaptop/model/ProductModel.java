package com.sellinglaptop.model;

public class ProductModel {
    private int id;
    private String productName;
    private String image;
    private double price;
    private int quantity;
    private String describePro;
    private int detailCateId;
    private int ids[]; //Chứ danh sách id để xoá

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescribePro() {
        return describePro;
    }

    public void setDescribePro(String describePro) {
        this.describePro = describePro;
    }

    public int getDetailCateId() {
        return detailCateId;
    }

    public void setDetailCateId(int detailCateId) {
        this.detailCateId = detailCateId;
    }
}
