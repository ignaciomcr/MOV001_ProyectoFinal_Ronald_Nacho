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

public class AdapterLista extends ArrayAdapter<Propiedad> {

    int mLayoutId;
    public AdapterLista(Context context, int layoutId, List<Propiedad> items){
        super(context, layoutId, items);
        mLayoutId = layoutId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        Propiedad propiedad = getItem(position);

        String tituloAdapter =propiedad.getTitulo();
        String categoriaAdapter = propiedad.getCategoria();
        String provinciaAdapter = propiedad.getProvincia();
        String garageAdapter = propiedad.getGarage();
        int numHabitacionesAdapter = ((Integer)propiedad.getNumHabitaciones());
        float precioAdapter = (float)propiedad.getPrecio();
        String ubicacionAdapter = propiedad.getUbicGeo();
        String propietarioAdapter = propiedad.getPropietario();


        if (view==null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(mLayoutId, parent, false);
        }

        TextView tituloView = (TextView) view.findViewById(R.id.txtTitulo);
        TextView categoriaView = (TextView) view.findViewById(R.id.txtCategoria);
        TextView provinciaView = (TextView) view.findViewById(R.id.txtProvincia);
        TextView garageView = (TextView) view.findViewById(R.id.txtGarage);
        TextView numHabitacionesView = (TextView) view.findViewById(R.id.txtNumHabitaciones);
        TextView precioView = (TextView) view.findViewById(R.id.txtPrecio);
        TextView ubicacionView = (TextView) view.findViewById(R.id.txtUbicGeo);
        TextView propietarioView = (TextView) view.findViewById(R.id.txtPropieatario);

        tituloView.setText("Título: "+ tituloAdapter);
        categoriaView.setText("Categoría: "+categoriaAdapter);
        provinciaView.setText("Provincia: "+provinciaAdapter);
        garageView.setText("Garage: "+garageAdapter);
        numHabitacionesView.setText("Habitaciones: "+Integer.toString(numHabitacionesAdapter));
        precioView.setText("Precio (colones): "+Float.toString(precioAdapter));
        ubicacionView.setText("Ubic.Geo: "+ubicacionAdapter);
        propietarioView.setText("Propietario: "+ propietarioAdapter);

        return view;
    }

}
