package com.example.assignment2;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ProductBaseAdapter extends BaseAdapter {
ArrayList<Product> listOfProducts;
Context context;
    public ProductBaseAdapter(ArrayList<Product> listOfProducts, Context context) {
        this.listOfProducts = listOfProducts;
        this.context = context;

    }

    @Override
    public int getCount() {
        return listOfProducts.size();
    }

    @Override
    public Object getItem(int i) { return listOfProducts.get(i);}

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.product_row_layout, null);

        TextView nameText = view.findViewById(R.id.textView_productName);
        TextView quantityText = view.findViewById(R.id.textView_productQuantity);
        TextView priceText = view.findViewById(R.id.textView_productPrice);

        nameText.setText((String.valueOf(listOfProducts.get(i).productName)));
        quantityText.setText((String.valueOf(listOfProducts.get(i).quantity)));
        priceText.setText((String.valueOf(listOfProducts.get(i).price)));



        return view;
    }

}
