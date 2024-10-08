package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.listProducts.ListProducts;
import com.example.ishoppinglist.models.Product;

public class AddProductToSystemActivity extends AppCompatActivity {

    //Declaración de componentes y variables necesarias para el funcionamiento de la activity
    private Button btnCancel, btnInsert;
    private EditText etName, etInformativeNote;
    private Boolean repeatedData;
    private Product productInsert;
    private Switch swtPendingPurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_to_system);

        //Inicializamos los componentes de la interfaz y otros objetos necesarios
        initializeComponents();

        //Si el usuario pulsa el botón btnCancel se le redirigirá a la MainActivity
        btnCancel.setOnClickListener(v -> {
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);
        });

        //Si el usuario pulsa el botón btnInsert, primero se pasará el booleano repeatedData a false, este booleano sirve para saber si a la
        //hora de intentar insertar un nuevo producto contiene datos repetidos, si por ejemplo intento insertar un producto el cual contiene
        //datos repetidos se pasaria el booleano repeatedData a true y no me dejaria insertar el producto, y si posteriormente vuelvo a pulsar
        //el botón btnInsert sin antes pasar el booleano repeatedData a false nunca me dejaría insertar el producto, ya que el booleano repeatedData
        //se quedaría en true
        btnInsert.setOnClickListener(v -> {

            repeatedData =false;

            //Se inicializa el método verifications(), este método realiza algunas verificaciones antes de llevar a cabo la inserción del
            //producto, por ejemplo, comprueba que los editText no estén vacíos y que los datos no estén repetidos, si los datos son
            //correctos se llevará a cabo la inserción del producto con los datos que el usuario haya ingresado
            verifications();

        });
    }

    /**
     * Este método inserta un nuevo producto con los datos que el usuario haya ingresado en los EditText y realiza las verifiaciones
     * necesarias para que no ocurra ningun error
     */
    private void verifications() {

        //Verifica que los editText no esten vacios
        if (etName.getText().length() == 0 | etInformativeNote.getText().length() == 0) {
            Toast.makeText(this, "You need to fill in all the fields", Toast.LENGTH_LONG).show();

        }else{

            //Recorremos la lista de productos
            for (Product product : ListProducts.productArrayList) {
                //Si el producto que estamos recorriendo tiene el mismo nombre o la misma nota informativa que el producto que estamos
                //intentando insertar pasaremos el booleano repeatedData a true, mostraremos un mensaje de error en rojo y nos salimos
                //del bucle usando break
                if (product.getName().toString().equalsIgnoreCase(etName.getText().toString())){
                    repeatedData = true;
                    Toast.makeText(this, "Error, a product with the name " + etName.getText() + " already exists", Toast.LENGTH_LONG).show();
                    break;
                }
            }

            // Si no hay datos repetidos, se inserta el producto con los datos que el usuario ha introducido y posteriormente
            //volvemos al MainActivity
            if (!repeatedData) {
                //Se asignan los datos al producto
                productInsert.setName(etName.getText().toString());
                productInsert.setInformativeNote(etInformativeNote.getText().toString());
                productInsert.setNeedToBuy(swtPendingPurchase.isChecked());
                //Llamamos al método insertProducts el cual sirve para insertar el producto que le pasemos por parametro
                ListProducts.insertProducts(productInsert);

                //Por último mostramos un mensaje por pantalla informando de que el producto se ha insertado correctamente y posteriormente
                //volvemos a la MainActivity
                Toast.makeText(this, "Product successfully inserted", Toast.LENGTH_LONG).show();
                Intent mainActivityIntent = new Intent(this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        }
    }

    /**
     * Este método se encarga de inicializar todos los componentes de la interfaz de usuario y otros
     * objetos necesarios para el funcionamiento de la actividad
     */
    private void initializeComponents(){

        btnCancel = findViewById(R.id.btnCancel);
        btnInsert = findViewById(R.id.btnInsert);
        etName = findViewById(R.id.etName);
        etInformativeNote = findViewById(R.id.etInformativeNote);
        swtPendingPurchase = findViewById(R.id.swtPendingPurchase);
        productInsert = new Product();

    }

}