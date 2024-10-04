package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.listProducts.ListProducts;
import com.example.ishoppinglist.models.Product;

public class AddProductToSystemActivity extends AppCompatActivity {

    private Button btnCancel, btnInsert;
    private EditText etName, etInformativeNote;
    private TextView tvMessages;
    private Boolean repeatedData;
    private Product productInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_to_system);


        initializeComponents();

        btnCancel.setOnClickListener(v -> {

            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);

        });

        btnInsert.setOnClickListener(v -> {

            repeatedData =false;
            verifications();

        });
    }

    private void verifications() {

        //Verifica que los editText no esten vacios
        if (etName.getText().length() == 0 | etInformativeNote.getText().length() == 0) {
            tvMessages.setText("Necesitas rellenar todos los campos");
            tvMessages.setTextColor(Color.RED);
        }else{

            for (Product product : ListProducts.productArrayList) {
                if (product.getName().toString().equalsIgnoreCase(etName.getText().toString())){
                    repeatedData = true;
                    tvMessages.setText("Error, ya hay un producto con el nombre " + etName.getText());
                    tvMessages.setTextColor(Color.RED);
                    break;
                }else if (product.getInformativeNote().toString().equalsIgnoreCase(etInformativeNote.getText().toString())){
                    repeatedData = true;
                    tvMessages.setText("Error, ya hay un producto con la nota informativa " + etInformativeNote.getText());
                    tvMessages.setTextColor(Color.RED);
                    break;
                }
            }

            if (!repeatedData) {
                productInsert.setName(etName.getText().toString());
                productInsert.setInformativeNote(etInformativeNote.getText().toString());
                tvMessages.setText("");
                ListProducts.insertProducts(productInsert);
                Toast.makeText(this, "Producto insertado correctamente", Toast.LENGTH_LONG).show();

                Intent mainActivityIntent = new Intent(this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        }
    }

    private void initializeComponents(){

        btnCancel = findViewById(R.id.btnCancel);
        btnInsert = findViewById(R.id.btnInsert);
        etName = findViewById(R.id.etName);
        etInformativeNote = findViewById(R.id.etInformativeNote);
        tvMessages = findViewById(R.id.tvMessages);
        productInsert = new Product();

    }

}