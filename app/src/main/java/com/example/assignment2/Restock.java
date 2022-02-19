package com.example.assignment2;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Restock extends AppCompatActivity
        implements View.OnClickListener{


ProductBaseAdapter adapter;
ArrayList<Product> list;
ListView baseAdapterList;

EditText newQuantity;
Button btnOk;
Button btnCancel;
TextView restockProduct;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_restock);

        baseAdapterList = findViewById(R.id.restockList);
        list = ((MyApp)getApplication()).manager.allProducts;
        adapter = new ProductBaseAdapter(list, this);
        baseAdapterList.setAdapter(adapter);
        restockProduct = findViewById(R.id.restockProduct);


        //Allows user to choose product to update
        baseAdapterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product temp =list.get(position);
                restockProduct.setText(temp.getProductName());
            }

        });


        newQuantity = findViewById(R.id.newQuantity);
        btnOk = findViewById(R.id.ok);
        btnOk.setOnClickListener(this);
        btnCancel = findViewById(R.id.cancel);
        btnCancel.setOnClickListener(this);






    }



    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id) {
            //Updates quantity if all fields are filled and valid
            case R.id.ok:
                if(!newQuantity.getText().toString().equals("") && !restockProduct.getText().toString().equals("")) {
                    String amount = newQuantity.getText().toString();

                    //Validate that text for added quantity ia a number
                    if(amount.equals("")) {
                        Toast.makeText(this, "All fields are REQUIRED", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        try {
                            int i = Integer.parseInt(amount);
                        } catch (NumberFormatException nfe) {
                            Toast.makeText(this, "Enter a Valid number", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }

                    updateQuantity(view);
                }
                else {
                    Toast.makeText(this, "All fields are REQUIRED", Toast.LENGTH_SHORT).show();
                }


                break;

                //returns to manager page
            case R.id.cancel:
                finish();
                break;
        }
    }

    //Updates quantity of allProducts using product name as ID
    public void updateQuantity(View view) {
        int amount = Integer.parseInt(newQuantity.getText().toString());
        String prod = restockProduct.getText().toString();
        Product tempProduct;

        switch (prod) {
            case "Pants":
                tempProduct = list.get(0);
                tempProduct.quantity+=amount;
                list.set(0, tempProduct);
                break;

            case "Shoes":
                tempProduct = list.get(1);
                tempProduct.quantity+=amount;
                list.set(1, tempProduct);

                break;

            case "Hats":
                tempProduct = list.get(2);
                tempProduct.quantity+=amount;
                list.set(2, tempProduct);
                break;


            default:
                break;
        }

        //Refreshes BaseAdapter
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Quantity Updated!", Toast.LENGTH_SHORT).show();
    }
}
