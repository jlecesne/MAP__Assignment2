package com.example.assignment2;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    String productName;
    int quantity;
    double price;
    int id;

    public Product(String name, int amt, double cost) {
        this.productName=name;
        this.quantity=amt;
        this.price=cost;
    }

    public Product() {
        this.productName="";
    }

    protected Product(Parcel in) {
        productName = in.readString();
        quantity = in.readInt();
        price = in.readDouble();
        id = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getProductName() {
        return this.productName;
    }

    public int getProductquantity() {
        return this.quantity;
    }

    public double getProductPrice() {
        return this.price;
    }

    public int getId() {
        return id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(price);
        parcel.writeInt(id);
        parcel.writeInt(quantity);
        parcel.writeString(productName);
    }
}
