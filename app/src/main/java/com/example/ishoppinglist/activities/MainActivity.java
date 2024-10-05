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

    //Declaración de variables necesarias para el funcionamiento de la activity
    private ListView lvProducts;
    private ProductAdapter adapter;
    private Button btnAddProductToSystem, btnAddProductsToPendingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Si la lista de productos esta vacia se inicializará el método enterTestData el cual sirve para llenar la lista con datos
        //de prueba
        if(ListProducts.productArrayList.isEmpty()){
            ListProducts.enterTestData();
        }

        //Inicializamos los componentes de la interfaz y otros objetos necesarios
        initializeComponents();

        //Se configura el adaptador con los productos que necesitan ser comprados, aquí en lugar de mandarle
        //directamente un arrayList al adapter usamos el método getProductsThatNeedToBuy() el cual nos devuelve un
        //arrayList con estos productos que necesitan ser comprados
        adapter = new ProductAdapter(getApplicationContext(), ListProducts.getProductsThatNeedToBuy());

        //Le asignamos el adaptador al listView
        lvProducts.setAdapter(adapter);

        //Añado un listener al listView que se ejecutará cada vez que haga click sobre un elemento de dicho listView
        lvProducts.setOnItemClickListener((parent, view, position, id) -> {
            //Obtengo el producto seleccionado del listView
            Product product = (Product) parent.getItemAtPosition(position);

            //Creo un Intent para iniciar la actividad DetailsProductActivity
            Intent intent = new Intent(this, DetailsProductActivity.class);

            //Agregamos el objeto Product al intent para enviarlo a la actividad DetailsProductActivity y poder obtener
            //dicho objeto en dicha actividad
            intent.putExtra("product", product);

            //Se inicia la actividad DetailsProductActivity
            startActivity(intent);
        });

        //Si el usuario pulsa el botón btnAddProductToSystem se inicializará la activity AddProductToSystemActivity en la cual podrá
        //añadir más productos al sistema
        btnAddProductToSystem.setOnClickListener(v -> {

            Intent AddProductToSytemIntent = new Intent(this, AddProductToSystemActivity.class);
            startActivity(AddProductToSytemIntent);

        });

        //Si el usuario pulsa el botón btnAddProductsToPendingList se inicializará la actividad AddProductsToPendingListActivity, en
        //dicha activity se le mostrará al usuario un spinner con productos no pendientes de compra para que pueda seleccionar cuál de ellos
        //pasar a pendiente de compra
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