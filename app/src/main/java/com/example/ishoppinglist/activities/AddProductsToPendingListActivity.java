package com.example.ishoppinglist.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.listProducts.ListProducts;
import com.example.ishoppinglist.models.Product;

public class AddProductsToPendingListActivity extends AppCompatActivity {

    private Spinner spinnerProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products_to_pending_list);

        initializeComponents();

    }

    private void initializeComponents(){

        spinnerProducts = findViewById(R.id.spinnerProducts);

        ArrayAdapter<Product> arrayAdapterProducts = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, ListProducts.getProductsThatDontNeedToBuy());
        spinnerProducts.setAdapter(arrayAdapterProducts);



    }


}