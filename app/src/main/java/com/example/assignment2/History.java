package com.example.assignment2;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class History extends AppCompatActivity
        implements View.OnClickListener{

ProductBaseAdapter adapter;
ArrayList<Product> list;
ListView baseAdapterList;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_history);


        baseAdapterList = findViewById(R.id.historyList);
        list = ((MyApp)getApplication()).manager.purchaseHistory;
        adapter = new ProductBaseAdapter(list, this);
        baseAdapterList.setAdapter(adapter);

        baseAdapterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product temp =list.get(position);
                Intent myIntent = new Intent (History.this, PurchaseDetails.class);
                myIntent.putExtra("Name", temp.getProductName());
                myIntent.putExtra("Price", temp.getProductPrice());
                myIntent.putExtra("Quantity", temp.getProductquantity());
                myIntent.putExtra("ID", temp.getId());
                startActivity(myIntent);

            }

        });

    }

    @Override
    public void onClick(View view) {

    }
}
