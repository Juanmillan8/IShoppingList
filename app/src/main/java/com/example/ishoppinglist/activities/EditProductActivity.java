package com.example.ishoppinglist.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.listProducts.ListProducts;
import com.example.ishoppinglist.models.Product;

public class EditProductActivity extends AppCompatActivity {

    private EditText etName, etInformativeNote;
    private Bundle getProduct;
    private Product productEdit;
    private ImageButton btnBack;
    private Button btnEdit;
    private TextView tvMessages;
    private Boolean repeatedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);


        //Inicializamos los componentes de la interfaz y otros objetos necesarios
        initializeComponents();

        //Si el usuario pulsa el boton btnBack se le redirigira a la anterior activity
        btnBack.setOnClickListener(v -> {

            finish();

        });

        btnEdit.setOnClickListener(v -> {
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
                if (product.getName().toString().equalsIgnoreCase(etName.getText().toString()) && product.getId()!=productEdit.getId()){
                    repeatedData = true;
                    tvMessages.setText("Error, ya hay un producto con el nombre " + etName.getText());
                    tvMessages.setTextColor(Color.RED);
                    break;
                }else if (product.getInformativeNote().toString().equalsIgnoreCase(etInformativeNote.getText().toString()) && product.getId()!=productEdit.getId()){
                    repeatedData = true;
                    tvMessages.setText("Error, ya hay un producto con la nota informativa " + etInformativeNote.getText());
                    tvMessages.setTextColor(Color.RED);
                    break;
                }
            }

            if (!repeatedData) {
                productEdit.setName(etName.getText().toString());
                productEdit.setInformativeNote(etInformativeNote.getText().toString());
                tvMessages.setText("");
                ListProducts.editProduct(productEdit);
                Toast.makeText(this, "Producto editado correctamente", Toast.LENGTH_LONG).show();

                Intent mainActivityIntent = new Intent(this, MainActivity.class);
                startActivity(mainActivityIntent);
            }

        }
    }

    /**
     * Este metodo se encarga de inicializar todos los componentes de la interfaz de usuario y otros
     * objetos necesarios para el funcionamiento de la actividad
     */
    private void initializeComponents(){
        btnBack = findViewById(R.id.btnBack);
        btnEdit = findViewById(R.id.btnEdit);
        getProduct = getIntent().getExtras();
        productEdit = (Product) getProduct.getSerializable("product");
        etName = findViewById(R.id.etName);
        etInformativeNote = findViewById(R.id.etInformativeNote);
        tvMessages = findViewById(R.id.tvMessages);


        etName.setText(productEdit.getName());
        etInformativeNote.setText(productEdit.getInformativeNote());

    }

}