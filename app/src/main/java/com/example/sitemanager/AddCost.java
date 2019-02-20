package com.example.sitemanager;

public class AddCost {
    String RawMat;
    String RawCost;
    String Quantity;
    String Total;
    String Url;
public AddCost(){

}
    public AddCost(String rawMat, String rawCost, String quantity, String total,String url) {
        RawMat = rawMat;
        RawCost = rawCost;
        Quantity = quantity;
        Total = total;
        Url=url;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getRawMat() {
        return RawMat;
    }

    public void setRawMat(String rawMat) {
        RawMat = rawMat;
    }

    public String getRawCost() {
        return RawCost;
    }

    public void setRawCost(String rawCost) {
        RawCost = rawCost;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
