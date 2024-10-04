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

    private List<Product> products;

    public ProductAdapter(Context context, List<Product> products){
        super(context, 0, products);
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product p = this.products.get(position);

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvName);

        tvName.setText(p.getName());

        return convertView;

    }
}
