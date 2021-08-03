/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 15/07/2021    1.0            Vanhv   
 */
package entity;

/**
 *
 * @author vanhv
 */
public class Cart {

    private String pId;
    private String date;
    private String pName;
    private String cQuantity;
    private double price;
    private double total;

    public Cart() {
    }

    public Cart(String pId, String date, String pName, String cQuantity, double price, double total) {
        this.pId = pId;
        this.date = date;
        this.pName = pName;
        this.cQuantity = cQuantity;
        this.price = price;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getcQuantity() {
        return cQuantity;
    }

    public void setcQuantity(String cQuantity) {
        this.cQuantity = cQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return pId +",date="+ date + ", pName=" + pName + ", cQuantity=" + cQuantity + ", price=" + price + ", total=" + total;
    }

}
