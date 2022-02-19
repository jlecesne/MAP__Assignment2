package com.example.assignment2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Manager extends AppCompatActivity
        implements View.OnClickListener{

Button btnHistory;
Button btnRestock;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_manager);

        btnHistory = findViewById(R.id.history);
        btnHistory.setOnClickListener(this);
        btnRestock = findViewById(R.id.restock);
        btnRestock.setOnClickListener(this);



    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.history:
                Intent historyIntent = new Intent(Manager.this, History.class);
                startActivity(historyIntent);
                break;

            case R.id.restock:
                Intent restockIntent = new Intent(Manager.this, Restock.class);// messaging object
                startActivity(restockIntent);
                break;

            default:
                break;
        }
    }
}
