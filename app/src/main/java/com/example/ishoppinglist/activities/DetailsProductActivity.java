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

    //Declaración de variables necesarias para el funcionamiento de la activity
    private ImageButton btnBack;
    private Bundle getProduct;
    private Product product;
    private TextView tvName, tvInformativeNote, tvPurchaseStatus, tvContainsLactose, tvContainsGluten;
    private Button btnEditProduct;
    private Bundle sendProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_product);

        //Inicializamos los componentes de la interfaz y otros objetos necesarios
        initializeComponents();

        //Si el usuario pulsa el botón btnBack se le redirigirá a la anterior activity
        btnBack.setOnClickListener(v -> {
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);
        });

        //Si el usuario pulsa el botón btnEditProduct, se inicializará la activity llamada EditProductActivity, en dicha actividad podremos
        //llevar a cabo la edicion del producto que estamos actualmente visualizando
        btnEditProduct.setOnClickListener(v -> {

            Intent editProductIntent = new Intent(this, EditProductActivity.class);

            //Se pasa el producto que estamos visualizando a la EditProductActivity para que al iniciar dicha actividad podamos obtener
            //los datos del producto y modificarlos
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
        tvContainsGluten = findViewById(R.id.tvContainsGluten);
        tvContainsLactose = findViewById(R.id.tvContainsLactose);
        tvInformativeNote = findViewById(R.id.tvInformativeNote);
        tvPurchaseStatus = findViewById(R.id.tvPurchaseStatus);
        btnEditProduct = findViewById(R.id.btnEditProduct);
        sendProduct = new Bundle();

        //Obtenemos el producto que fue pasado de la anterior activity a esta
        getProduct = getIntent().getExtras();
        product = (Product) getProduct.getSerializable("product");

        tvName.setText(product.getName());
        tvInformativeNote.setText(product.getInformativeNote());

        if(product.getNeedToBuy()){
            tvPurchaseStatus.setText("Pending purchase");
        }else{
            tvPurchaseStatus.setText("Not pending purchase");
        }

        if(product.getContainsGluten()){
            tvContainsGluten.setText("Contains gluten");
        }else{
            tvContainsGluten.setText("Does not contain gluten");
        }

        if(product.getContainsLactose()){
            tvContainsLactose.setText("Contains lactose");
        }else{
            tvContainsLactose.setText("Does not contain lactose");
        }

    }
}