package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.adapters.ProductAdapter;
import com.example.ishoppinglist.listProducts.ListProducts;
import com.example.ishoppinglist.models.Product;

public class MainActivity extends AppCompatActivity {

    private ListView lvProducts;
    private ProductAdapter adapter;
    private Button btnAddProductToSystem, btnAddProductsToPendingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ListProducts.productArrayList.isEmpty()){
            ListProducts.enterTestData();
        }


        //Inicializamos los componentes de la interfaz y otros objetos necesarios
        initializeComponents();

        adapter = new ProductAdapter(getApplicationContext(), ListProducts.getProductsThatNeedToBuy());
        lvProducts.setAdapter(adapter);

        //Añado un listener al listView que se ejecutara cada vez que haga click sobre un elemento de dicho listView
        lvProducts.setOnItemClickListener((parent, view, position, id) -> {
            //Obtengo el producto seleccionado del listView
            Product product = (Product) parent.getItemAtPosition(position);

            //Creo un Intent para iniciar la actividad DetailsProductActivity
            Intent intent = new Intent(this, DetailsProductActivity.class);

            //Agregamos el objeto Product al intent para enviarlo a la actividad DetailsProductActivity y poder obtener dicho objeto en dicha actividad
            intent.putExtra("Product", product);

            //Se inicia la actividad DetailsProductActivity
            startActivity(intent);
        });

        btnAddProductToSystem.setOnClickListener(v -> {

            Intent AddProductToSytemIntent = new Intent(this, AddProductToSystemActivity.class);
            startActivity(AddProductToSytemIntent);

        });

        btnAddProductsToPendingList.setOnClickListener(v -> {

            Intent AddProductToPendingListIntent = new Intent(this, AddProductsToPendingListActivity.class);
            startActivity(AddProductToPendingListIntent);

        });

    }

    /**
     * Este metodo se encarga de inicializar todos los componentes de la interfaz de usuario y otros
     * objetos necesarios para el funcionamiento de la actividad
     */
    private void initializeComponents(){

        lvProducts = findViewById(R.id.lvProducts);
        btnAddProductToSystem = findViewById(R.id.btnAddProductToSystem);
        btnAddProductsToPendingList = findViewById(R.id.btnAddProductsToPendingList);


    }

}