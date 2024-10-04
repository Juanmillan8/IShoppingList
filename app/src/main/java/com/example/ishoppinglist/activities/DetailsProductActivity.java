package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.models.Product;

public class DetailsProductActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private Bundle getProduct;
    private Product product;
    private TextView tvName;
    private TextView tvInformativeNote;
    private TextView tvPurchaseStatus;
    private Button btnEditProduct;
    private Bundle sendProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);


        initializeComponents();

        //Si el usuario pulsa el boton btnBack se le redirigira a la anterior activity
        btnBack.setOnClickListener(v -> {

            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);

        });

        btnEditProduct.setOnClickListener(v -> {

            Intent editProductIntent = new Intent(this, EditProductActivity.class);

            sendProduct.putSerializable("product", product);
            editProductIntent.putExtras(sendProduct);

            startActivity(editProductIntent);

        });

    }


    /**
     * Este método se encarga de inicializar todos los componentes de la interfaz de usuario y otros
     * objetos necesarios para el funcionamiento de la actividad
     */
    private void initializeComponents(){
        btnBack = findViewById(R.id.btnBack);
        tvName = findViewById(R.id.tvName);
        tvInformativeNote = findViewById(R.id.tvInformativeNote);
        tvPurchaseStatus = findViewById(R.id.tvPurchaseStatus);
        btnEditProduct = findViewById(R.id.btnEditProduct);
        sendProduct = new Bundle();

        getProduct = getIntent().getExtras();
        product = (Product) getProduct.getSerializable("Product");

        tvName.setText(product.getName());
        tvInformativeNote.setText(product.getInformativeNote());

        if(product.getNeedToBuy()){
            tvPurchaseStatus.setText("Se necesita comprar");
        }else{
            tvPurchaseStatus.setText("No se necesita comprar");
        }

    }
}