package com.example.mov001_proyectofinal_ronald_nacho.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mov001_proyectofinal_ronald_nacho.Models.Propiedad;
import com.example.mov001_proyectofinal_ronald_nacho.R;

import java.util.List;

public class AdapterResumen extends ArrayAdapter<Propiedad> {

    int mLayoutId;
    public AdapterResumen(Context context, int layoutId, List<Propiedad> items){
        super(context, layoutId, items);
        mLayoutId = layoutId;
    }



    @Override
    public View getView(int position, View view, ViewGroup parent){
        Propiedad propiedad = getItem(position);

        String idRegistroAdapter=propiedad.getIdRegistro();
        String tituloAdapter =propiedad.getTitulo();
        String provinciaAdapter =propiedad.getProvincia();
        String categoriaAdapter =propiedad.getCategoria();
        float precioAdapter = (float)propiedad.getPrecio();



        if (view==null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(mLayoutId, parent, false);
        }

        TextView idRegistroView = (TextView) view.findViewById(R.id.txtRegistro);
        TextView tituloView = (TextView) view.findViewById(R.id.txtTitulo);
        TextView provinciaView = (TextView) view.findViewById(R.id.txtProvincia);
        TextView categoriaView = (TextView) view.findViewById(R.id.txtCategoria);
        TextView precioView = (TextView) view.findViewById(R.id.txtPrecio);


        idRegistroView.setText("Id.Anuncio: "+ idRegistroAdapter);
        tituloView.setText("Título: "+ tituloAdapter);
        provinciaView.setText("Provincia: "+ provinciaAdapter);
        categoriaView.setText("Categoría: "+ categoriaAdapter);
        precioView.setText("Precio (colones): "+ Float.toString(precioAdapter));


        return view;
    }

}
