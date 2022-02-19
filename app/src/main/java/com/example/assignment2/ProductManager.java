package com.example.assignment2;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ProductManager {
    ArrayList<Product> allProducts = new ArrayList(0);
    ArrayList<Product> purchaseHistory = new ArrayList(0);
    ArrayList<String> purchaseDetails = new ArrayList(0);
    int id=0;

    public void addProduct(Product p) {allProducts.add(p);}
    public void addPurchase(Product p) {
        p.id=id;
        purchaseHistory.add(p);
        id++;
    }
    public void addHistory() {
        Date date = new Date();
        String str = date.toString();
        purchaseDetails.add(str);
    }
}
