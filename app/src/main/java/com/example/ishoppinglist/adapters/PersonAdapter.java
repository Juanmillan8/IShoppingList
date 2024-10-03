package com.example.ishoppinglist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ishoppinglist.models.Person;

import org.w3c.dom.Text;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person>{

    private List<Person> persons;

    public PersonAdapter(Context context, int resource, List<Person> persons){
        super(context, resource, persons);
        this.persons=persons;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Person p = this.persons.get(position);

        //Si todavia no se ha creado la vista
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.IdPerson, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvEmail = convertView.findViewById(R.id.tvEmail);
        TextView tvPhone = convertView.findViewById(R.id.tvPhone);

        tvName.setText(p.getName() + " " + p.getSurname());
        tvEmail.setText(p.getEmail());
        tvPhone.setText(p.getPhone());

        return super.getView(position, convertView, parent);

        return convertView;

    }
}
