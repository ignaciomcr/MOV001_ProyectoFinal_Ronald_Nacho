package com.example.mov001_proyectofinal_ronald_nacho.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.example.mov001_proyectofinal_ronald_nacho.Adapter.AdapterResumen;
import com.example.mov001_proyectofinal_ronald_nacho.Models.Propiedad;
import com.example.mov001_proyectofinal_ronald_nacho.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class fragment3 extends Fragment {

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String respFiltro="";
    public String respColumna="";
    public ArrayAdapter<CharSequence> adapCategoria;
    public ArrayAdapter<CharSequence> adapProvincia;
   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_fragment3, container, false);
        SetupUI(view);

        //ConsultaPropiedades(view);

        return  view;

    }

    private void SetupUI(final View view){

        final RadioButton rbTodos=(RadioButton)view.findViewById(R.id.rbTodos);
        final RadioButton rbCat=(RadioButton)view.findViewById(R.id.rbPorCat);
        final RadioButton rbProv=(RadioButton)view.findViewById(R.id.rbPorProv);

        final Spinner spinnerFiltros=(Spinner)view.findViewById(R.id.filtros_spinner);
        spinnerFiltros.setAdapter(null);
        spinnerFiltros.setVisibility(view.INVISIBLE);

        //rbTodos.setChecked(true);

        adapCategoria=ArrayAdapter.createFromResource(getContext(),R.array.categorias_spinner,R.layout.support_simple_spinner_dropdown_item);
        adapCategoria.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        adapProvincia=ArrayAdapter.createFromResource(getContext(),R.array.provincias_spinner,R.layout.support_simple_spinner_dropdown_item);
        adapProvincia.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);



        ConsultaResumen(view);

        rbTodos.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 spinnerFiltros.setAdapter(null);
                 spinnerFiltros.setVisibility(view.INVISIBLE);

                 if (rbTodos.isChecked()){
                     rbCat.setChecked(false);
                     rbProv.setChecked(false);
                     //respFiltro="";

                     ConsultaResumen(view);
                 }

             }
         }
        );


        rbCat.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               spinnerFiltros.setAdapter(null);
               spinnerFiltros.setAdapter(adapCategoria);

               if (rbCat.isChecked()){
                   respColumna="categoria";
                   rbTodos.setChecked(false);
                   rbProv.setChecked(false);
                   spinnerFiltros.setVisibility(view.VISIBLE);

               }

           }
          }
        );

        rbProv.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 spinnerFiltros.setAdapter(null);
                 spinnerFiltros.setAdapter(adapProvincia);

                 if (rbProv.isChecked()){
                     respColumna="provincia";
                     rbTodos.setChecked(false);
                     rbCat.setChecked(false);
                     spinnerFiltros.setVisibility(view.VISIBLE);

                 }

             }
         }
        );


        final Button mboton=(Button)view.findViewById(R.id.btOculto);

        mboton.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               ConsultaFiltro(view,respFiltro.trim(),respColumna);
           }
        }
        );


        spinnerFiltros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view,
                                       int position, long id) {
                respFiltro = spinnerFiltros.getSelectedItem().toString();

                mboton.performClick();
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(getContext(),"NADA",Toast.LENGTH_SHORT).show();
            }

        });


    }


    private void Mensaje(String va,View view){


    }

    private void ConsultaResumen(final View view)
    {

        final ArrayList<Propiedad> mInformacion = new ArrayList<Propiedad>();

        //final AdapterLista adapter = new AdapterLista(getContext(), R.layout.list,mInformacion);
        final AdapterResumen adapterResumen = new AdapterResumen(getContext(), R.layout.listresumen,mInformacion);

        final ListView listViewPropiedades = (ListView)view.findViewById(R.id.listviewPropiedades);
        adapterResumen.clear();


        db.collection("Propiedades")//.whereEqualTo(FieldPath.documentId(), "RfJ4ztiWGkMnmbAHdM6n")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String idRegistroBD=document.getId().toString();
                                String tituloBD = document.get("titulo").toString();
                                String categoriaBD = document.get("categoria").toString();
                                String provinciaBD = document.get("provincia").toString();
                                String garageBD = document.get("garage").toString();
                                String numHabitacionesBD = document.get("numHabitaciones").toString();
                                String precioBD = document.get("precio").toString();
                                String ubicBD = document.get("ubicGeo").toString();
                                String propietarioBD = document.get("propietario").toString();


                                Propiedad objPropiedad=new Propiedad(idRegistroBD,tituloBD,categoriaBD,provinciaBD,garageBD,
                                        Integer.parseInt(numHabitacionesBD),Float.parseFloat(precioBD),
                                        ubicBD,propietarioBD);

                                //Propiedad objResumen=new Propiedad(idRegistroBD,tituloBD,Float.parseFloat(precioBD),fotoBD);


                                //Toast.makeText(getContext(),document.getId().toString(),Toast.LENGTH_SHORT).show();

                                //adapter.add(objPropiedad);
                                adapterResumen.add(objPropiedad);

                            }

                            listViewPropiedades.setAdapter(adapterResumen);
                            //Toast.makeText(getContext(),adapter.getItem(0).getPropietario().toString(),Toast.LENGTH_SHORT).show();



                        } else {
                            Toast.makeText(getContext(),"No se encontraron propiedades",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        listViewPropiedades.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                //Toast.makeText(getContext(),Integer.toString(position),Toast.LENGTH_SHORT).show();

                //Toast.makeText(getContext(),adapterResumen.getItem(position).getIdRegistro(),Toast.LENGTH_SHORT).show();

                fragment3Directions.AccionTofragment4 action= fragment3Directions.AccionTofragment4();
                action.setArgDocumentoID(adapterResumen.getItem(position).getIdRegistro().toString());//set the message
                Navigation.findNavController(view).navigate(action);


                //ConsultaPropiedades(view,adapterResumen.getItem(position).getIdRegistro());

            }
        });

    }


    private void ConsultaFiltro(final View view,String filtro,String columna)
    {



        final ArrayList<Propiedad> mInformacion = new ArrayList<Propiedad>();

        //final AdapterLista adapter = new AdapterLista(getContext(), R.layout.list,mInformacion);
        final AdapterResumen adapterResumen = new AdapterResumen(getContext(), R.layout.listresumen,mInformacion);

        final ListView listViewPropiedades = (ListView)view.findViewById(R.id.listviewPropiedades);
        adapterResumen.clear();


        db.collection("Propiedades").whereEqualTo(columna, filtro.trim())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String idRegistroBD=document.getId().toString();
                                String tituloBD = document.get("titulo").toString();
                                String categoriaBD = document.get("categoria").toString();
                                String provinciaBD = document.get("provincia").toString();
                                String garageBD = document.get("garage").toString();
                                String numHabitacionesBD = document.get("numHabitaciones").toString();
                                String precioBD = document.get("precio").toString();
                                String ubicBD = document.get("ubicGeo").toString();
                                String propietarioBD = document.get("propietario").toString();



                                Propiedad objPropiedad=new Propiedad(idRegistroBD,tituloBD,categoriaBD,provinciaBD,garageBD,
                                        Integer.parseInt(numHabitacionesBD),Float.parseFloat(precioBD),
                                        ubicBD,propietarioBD);

                                //Propiedad objResumen=new Propiedad(idRegistroBD,tituloBD,Float.parseFloat(precioBD),fotoBD);


                                //Toast.makeText(getContext(),document.getId().toString(),Toast.LENGTH_SHORT).show();

                                //adapter.add(objPropiedad);
                                adapterResumen.add(objPropiedad);

                            }

                            listViewPropiedades.setAdapter(adapterResumen);
                            //Toast.makeText(getContext(),adapter.getItem(0).getPropietario().toString(),Toast.LENGTH_SHORT).show();



                        } else {
                            Toast.makeText(getContext(),"No se encontraron propiedades",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        listViewPropiedades.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                //Toast.makeText(getContext(),Integer.toString(position),Toast.LENGTH_SHORT).show();

                //Toast.makeText(getContext(),adapterResumen.getItem(position).getIdRegistro(),Toast.LENGTH_SHORT).show();

                fragment3Directions.AccionTofragment4 action= fragment3Directions.AccionTofragment4();
                action.setArgDocumentoID(adapterResumen.getItem(position).getIdRegistro().toString());//set the message
                Navigation.findNavController(view).navigate(action);


                //ConsultaPropiedades(view,adapterResumen.getItem(position).getIdRegistro());

            }
        });


    }


}
