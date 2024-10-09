package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.DataBase.DataBase;
import com.example.ishoppinglist.models.Product;

public class AddProductsToPendingListActivity extends AppCompatActivity {

    //Declaración de componentes y variables necesarias para el funcionamiento de la activity
    private Spinner spinnerProducts;
    private Button btnCancel, btnInsert;
    private Product p;
    private TextView tvMessage, tvTitleSelectProduct;
    private String productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products_to_pending_list);

        //Inicializamos los componentes de la interfaz y otros objetos necesarios
        initializeComponents();

        //Si el usuario pulsa el botón btnCancel se le redirigirá a la MainActivity
        btnCancel.setOnClickListener(v -> {
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);
        });

        //Si el usuario pulsa el botón btnInsert se inicializara el metodo insertProductToPendingList()
        btnInsert.setOnClickListener(v -> {
            insertProductToPendingList();
        });

    }

    /**
     * Aquí se obtiene el producto seleccionado en el Spinner, posteriormente se marca como pendiente de compra,
     * se actualiza en la lista usando el metodo editProduct, se muestra un mensaje de éxito y por ultimo volvemos
     * a la MainActivity
     */
    private void insertProductToPendingList(){

        //Obtenemos el nombre del producto que hemos seleccionado en el spinner
        productName = spinnerProducts.getSelectedItem().toString();

        //Recorremos la lista de productos, para cada producto, verificamos si el nombre coincide con el nombre del
        //producto seleccionado en el spinner, si coincide almacenamos dicho producto en una variable y salimos del bucle
        for (Product product : DataBase.productArrayList) {
            if (product.getName().toString().equalsIgnoreCase(productName)){
                p = product;
                break;
        }
        }

        //Pasamos el atributo needToBuy del producto seleccionado a true indicando que ahora dicho producto esta como pendiente de compra
        p.setNeedToBuy(true);
        //Editamos el producto almacenando el cambio en el atributo needToBuy
        DataBase.editProduct(p);

        //Por último mostramos un mensaje por pantalla informando de que el producto ha pasado a estar como pendiente de compra correctamente
        //y posteriormente volvemos a la MainActivity
        Toast.makeText(this, "Product successfully added to the pending list", Toast.LENGTH_LONG).show();
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);

    }

    /**
     * Aqui inicializamos los componentes de la interfaz de usuario y establecemos la visibilidad de los elementos dependiendo de si tenemos productos
     * que no esten como pendientes de compra o no, también configuramos el adaptador del Spinner con productos que no necesitan ser comprados
     */
    private void initializeComponents(){

        //Inicializamos los componentes de la interfaz de usuario
        spinnerProducts = findViewById(R.id.spinnerProducts);
        btnCancel = findViewById(R.id.btnCancel);
        btnInsert = findViewById(R.id.btnInsert);
        tvMessage = findViewById(R.id.tvMessage);
        tvTitleSelectProduct = findViewById(R.id.tvTitleSelectProduct);

        //Creamos un adaptador para el Spinner el cual contiene los productos que no necesitan ser comprados (obtenemos dichos productos usando el metodo
        //getProductsThatDontNeedToBuy() de la clase ListProducts), posteriormente le asignamos dicho adaptador al Spinner
        ArrayAdapter<String> arrayAdapterProducts = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, DataBase.getProductsThatDontNeedToBuy());
        spinnerProducts.setAdapter(arrayAdapterProducts);

        //Si no hay productos disponibles para mostrar en el Spinner, mostraremos el botón btnCancel y el textView tvMessage el cual informa de que no hay
        //productos disponibles para pasar a la lista de pendientes
        if(arrayAdapterProducts.isEmpty()){
            btnCancel.setVisibility(View.VISIBLE);
            tvMessage.setVisibility(View.VISIBLE);
        }else{
            //Si hay productos disponibles, se muestran los componentes necesarios para la selección e inserción de los productos a la lista de pendientes
            btnInsert.setVisibility(View.VISIBLE);
            spinnerProducts.setVisibility(View.VISIBLE);
            tvTitleSelectProduct.setVisibility(View.VISIBLE);
        }
    }


}