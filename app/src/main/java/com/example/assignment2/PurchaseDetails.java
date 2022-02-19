package com.example.assignment2;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PurchaseDetails extends AppCompatActivity {

TextView product;
TextView price;
TextView date;
ProductManager productManager;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_details);


        productManager = ((MyApp)getApplication()).manager;

        String pName = getIntent().getExtras().getString("Name");
        int pQuantity = getIntent().getExtras().getInt("Quantity");
        double pPrice = getIntent().getExtras().getDouble("Price");
        int pID = getIntent().getExtras().getInt("ID");


        product = findViewById(R.id.productName);
        price = findViewById(R.id.productPrice);
        date = findViewById(R.id.purchaseDate);

        product.setText("Product: " + pName);
        price.setText("Price: " + pPrice);
        date.setText("Purchase Date: " + productManager.purchaseDetails.get(pID));





    }
}
