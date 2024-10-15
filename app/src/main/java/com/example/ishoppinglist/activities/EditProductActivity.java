package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.dataBase.DataBase;
import com.example.ishoppinglist.models.Product;

public class EditProductActivity extends AppCompatActivity {

    //Declaración de variables necesarias para el funcionamiento de la activity
    private EditText etName, etInformativeNote;
    private Bundle getProduct;
    private Product productEdit;
    private Button btnEdit, btnCancel;
    private Boolean repeatedData;
    private Switch swtPendingPurchase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);


        //Inicializamos los componentes de la interfaz y otros objetos necesarios
        initializeComponents();

        //Si el usuario pulsa el botón btnCancel se ejecutará el método finish(), el método finish cierra la actividad actual
        //y regresa a la anterior
        btnCancel.setOnClickListener(v -> {
            finish();
        });

        //Si el usuario pulsa el botón btnEdit, primero se pasará el booleano repeatedData a false, este booleano sirve para saber si a la
        //hora de intentar editar un producto dicho producto está repetido, si por ejemplo intento editar un producto y está repetido
        //se pasaria el booleano repeatedData a true y no me dejaria editar el producto, y si posteriormente vuelvo a pulsar el botón btnEdit
        //sin antes pasar el booleano repeatedData a false nunca me dejaría editar el producto, ya que el booleano repeatedData se quedaría en true
        btnEdit.setOnClickListener(v -> {
            repeatedData=false;

            //Se inicializa el método verifications(), este método realiza algunas verificaciones antes de llevar a cabo la edición del
            //producto, por ejemplo, comprueba que los editText no estén vacíos y que no haya datos repetidos, si los datos son
            //correctos se llevará a cabo la edición del producto con los nuevos datos que el usuario haya ingresado
            verifications();
        });
    }

    /**
     * Este método edita el producto con los datos que el usuario haya insertado en los EditText y realiza las verifiaciones
     * necesarias para que no ocurra ningun error
     */
    private void verifications() {

        //Verifica que los editText no estén vacíos
        if (etName.getText().length() == 0 | etInformativeNote.getText().length() == 0) {
            //Si algún campo está vacío, muestra un mensaje de error en rojo
            Toast.makeText(this, "You need to fill in all the fields", Toast.LENGTH_LONG).show();
        }else{

            //Recorremos la lista de productos
            for (Product product : DataBase.productArrayList) {
                //Si el nombre que he insertado en el editText es igual al nombre del producto que estamos recorriendo pero dicho producto no
                //tiene la misma id del producto que estamos editando (osea, tiene el mismo nombre pero no es este mismo producto)
                //pasaremos el booleano repeatedData a true, mostraremos un mensaje de error por pantalla y nos salimos del bucle usando break
                if (product.getName().toString().equalsIgnoreCase(etName.getText().toString()) && product.getId()!=productEdit.getId()){
                    repeatedData = true;
                    Toast.makeText(this, "Error, a product with the name " + etName.getText() + " already exists", Toast.LENGTH_LONG).show();
                    break;
                }
            }

            // Si no hay datos repetidos, se actualiza el producto con los nuevos datos que el usuario ha introducido y
            //volvemos al MainActivity
            if (!repeatedData) {
                //Se asignan los nuevos valores al producto
                productEdit.setName(etName.getText().toString());
                productEdit.setInformativeNote(etInformativeNote.getText().toString());
                productEdit.setNeedToBuy(swtPendingPurchase.isChecked());
                //Llamamos al método editProduct el cual sirve para editar el producto y le pasamos por parametro el producto con
                //los datos actualizados
                DataBase.editProduct(productEdit);

                //Por último mostramos un mensaje por pantalla informando de que el producto se ha editado correctamente y posteriormente
                //volvemos a la MainActivity
                Toast.makeText(this, "Product successfully edited", Toast.LENGTH_LONG).show();
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
        swtPendingPurchase = findViewById(R.id.swtPendingPurchase);
        btnEdit = findViewById(R.id.btnEdit);
        getProduct = getIntent().getExtras();
        productEdit = (Product) getProduct.getSerializable("product");
        etName = findViewById(R.id.etName);
        etInformativeNote = findViewById(R.id.etInformativeNote);


        etName.setText(productEdit.getName());
        etInformativeNote.setText(productEdit.getInformativeNote());
        swtPendingPurchase.setChecked(productEdit.getNeedToBuy());

    }

}