package com.example.ishoppinglist.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ishoppinglist.R;
import com.example.ishoppinglist.models.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product>{

    //Lista que contendrá los productos a mostrar en el ListView
    private List<Product> products;

    //Constructor del adapter
    public ProductAdapter(Context context, List<Product> products){
        super(context, 0, products);
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Obtenemos e inicializamos el objeto Product en la posición actual
        Product p = this.products.get(position);

        //Reutiliza una vista existente o crea una nueva si es necesario
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        //Accedemos al TextView que se usa para mostrar el nombre de cada producto y le
        //insertamos el nombre del producto actual
        TextView tvName = convertView.findViewById(R.id.tvName);
        tvName.setText(p.getName());

        //Devolvemos la vista modificada para que se muestre en el ListView
        return convertView;

    }
}
