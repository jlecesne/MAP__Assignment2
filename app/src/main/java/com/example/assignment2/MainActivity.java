package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {


Product pants = new Product();
Product shoes = new Product();
Product hats = new Product();

ProductManager productManager;
Product myProduct;
NumberPicker numberPicker;
TextView selectedProduct;
ListView baseAdapterList;

Button btnManager;
Button btnBuy;
TextView total;
TextView quantity;
ArrayList<Product> list;
ProductBaseAdapter adapter;

static final String userQuantity = null;
static final String userProduct = null;
static final String userTotal = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Enable fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        myProduct = ((MyApp)getApplication()).mainProduct;
        productManager = ((MyApp)getApplication()).manager;

        quantity = findViewById(R.id.productQuantity);
        total = findViewById(R.id.total);
        selectedProduct = findViewById(R.id.selectedProduct);


        baseAdapterList = findViewById(R.id.baseAdapterList);
        list = ((MyApp)getApplication()).manager.allProducts;

        adapter = new ProductBaseAdapter(list, this);
        baseAdapterList.setAdapter(adapter);

        //Ensures listview does not repopulate (on create resets on changed orientation
        if(list.size()==0) {
            setupProductList();
        }

        //Allows user to choose product to buy
        baseAdapterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product temp =list.get(position);
                selectedProduct.setText(temp.getProductName());

                updateTotal();
            }

        });

        btnManager = findViewById(R.id.manager);
        btnManager.setOnClickListener(this);
        btnBuy = findViewById(R.id.buy);
        btnBuy.setOnClickListener(this);
        quantity = findViewById(R.id.productQuantity);
        total = findViewById(R.id.total);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);
        numberPicker.setValue(0);


        //Updates total after scrolling number picker
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
                quantity.setText("" + newValue);

                updateTotal();

            }
        });


    }


    //Calculates total based on quantity and product chosen
    public void updateTotal() {
        double tempVal = 0;

        if (!selectedProduct.getText().toString().equals("")) {
            switch (selectedProduct.getText().toString()) {
                case "Pants":
                    tempVal = 20.44;
                    break;

                case "Shoes":
                    tempVal = 10.44;
                    break;

                case "Hats":
                    tempVal = 5.9;
                    break;

                default:
                    tempVal = 0;

            }

            if (tempVal != 0) {
                if(!quantity.getText().toString().equals("")) {
                    Double amount = Double.parseDouble(quantity.getText().toString());
                    Double val = amount * tempVal;
                    total.setText(String.format("%.2f", val));
                }

            }
        }
    }

    //Adds products to allProducts list
    public void setupProductList() {
        pants.setProductName("Pants");
        pants.setPrice(20.44);
        pants.setQuantity(10);

        shoes.setProductName("Shoes");
        shoes.setPrice(10.44);
        shoes.setQuantity(100);

        hats.setProductName("Hats");
        hats.setPrice(5.9);
        hats.setQuantity(30);

        productManager.addProduct(pants);
        productManager.addProduct(shoes);
        productManager.addProduct(hats);

    }

    //Setups click listeners
    @Override
    public void onClick(View view) {
        int id = view.getId();


        //Starts manager activity
        switch (id) {
            case R.id.manager:
                Intent myIntent = new Intent(this, Manager.class);
                startActivity(myIntent);
                break;

                //Decreases quantity in allProducts by amount chosen based on product. Also gives user summary of item purchased
            case R.id.buy:
                if (!total.getText().toString().equals("") && !quantity.getText().toString().equals("")) {
                    if (!selectedProduct.getText().toString().equals("")) {
                        Product tempProduct;
                        Integer amount = Integer.parseInt(quantity.getText().toString());
                        switch (selectedProduct.getText().toString()) {
                            case "Pants":
                                tempProduct = list.get(0);
                                if(tempProduct.quantity>=amount) {
                                    tempProduct.quantity -= Double.parseDouble(quantity.getText().toString());
                                    list.set(0, tempProduct);

                                    popUpDialog(view,  "Your purchase is " + amount + " " + tempProduct.getProductName() + " for " + total.getText().toString());

                                    //Add product to history
                                    Product sav = new Product();
                                    sav.setQuantity(amount);
                                    sav.setProductName(tempProduct.getProductName());
                                    sav.setPrice(Double.parseDouble(total.getText().toString()));
                                    productManager.addPurchase(sav);
                                    productManager.addHistory();
                                }
                                else {
                                    Toast.makeText(this, "Not enough quantity in stock", Toast.LENGTH_SHORT).show();
                                }

                                break;

                            case "Shoes":
                                tempProduct = list.get(1);
                                if(tempProduct.quantity>=amount) {
                                    tempProduct.quantity -= Double.parseDouble(quantity.getText().toString());
                                    list.set(1, tempProduct);

                                    popUpDialog(view,  "Your purchase is " + amount + " " + tempProduct.getProductName() + " for " + total.getText().toString());


                                    //Add product to history
                                    Product sav = new Product();
                                    sav.setQuantity(amount);
                                    sav.setProductName(tempProduct.getProductName());
                                    sav.setPrice(Double.parseDouble(total.getText().toString()));
                                    productManager.addPurchase(sav);
                                    productManager.addHistory();
                                }
                                else {
                                    Toast.makeText(this, "Not enough quantity in stock", Toast.LENGTH_SHORT).show();
                                }

                                break;

                            case "Hats":
                                tempProduct = list.get(2);
                                if(tempProduct.quantity>=amount) {
                                    tempProduct.quantity -= Double.parseDouble(quantity.getText().toString());
                                    list.set(2, tempProduct);

                                    popUpDialog(view,  "Your purchase is " + amount + " " + tempProduct.getProductName() + " for " + total.getText().toString());


                                    //Add product to history
                                    Product sav = new Product();
                                    sav.setQuantity(amount);
                                    sav.setProductName(tempProduct.getProductName());
                                    sav.setPrice(Double.parseDouble(total.getText().toString()));
                                    productManager.addPurchase(sav);
                                    productManager.addHistory();
                                }
                                else {
                                    Toast.makeText(this, "Not enough quantity in stock", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            default:
                                break;

                        }

                    }

                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
        }


    }

    //Summary popup after purchse
    public void popUpDialog(View view, String description) {
        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.purchase_card);

        TextView desc = dialog.findViewById(R.id.purchase_description);
        desc.setText(description);

        dialog.show();
    }


}