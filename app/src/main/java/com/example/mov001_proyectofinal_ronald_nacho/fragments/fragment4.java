package com.example.mov001_proyectofinal_ronald_nacho.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mov001_proyectofinal_ronald_nacho.Adapter.AdapterLista;
import com.example.mov001_proyectofinal_ronald_nacho.Models.Propiedad;
import com.example.mov001_proyectofinal_ronald_nacho.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class fragment4 extends Fragment

    implements OnMapReadyCallback

{

    GoogleMap mGoogleMap;
    MapView mMapView;
    View view;

    private String DocumentoIDFragement3;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();

    public double lon ;
    public double lat;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
        mMapView=(MapView)view.findViewById(R.id.map);

        if (mMapView!=null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



         view=inflater.inflate(R.layout.fragment_fragment4, container, false);


        SetupUI(view);




        return  view;

    }



    public void SetupUI(View view  )
    {



        fragment4Args args= fragment4Args.fromBundle(getArguments());

        String message = args.getArgDocumentoID();

        DocumentoIDFragement3=message;

        ConsultaPropiedades(view,DocumentoIDFragement3);

    }


    private void ConsultaPropiedades(View view,String idDocumento)
    {
        final ArrayList<Propiedad> mInformacion = new ArrayList<Propiedad>();

        final AdapterLista adapter = new AdapterLista(getContext(), R.layout.list,mInformacion);

        final ListView listviewDetalle = (ListView)view.findViewById(R.id.listviewDetalle);
        adapter.clear();


        db.collection("Propiedades").whereEqualTo(FieldPath.documentId(), idDocumento)
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


                                String[] parts = ubicBD.split("/");
                                String Latitud = parts[0]; // 123
                                String Longitud = parts[1]; // 654321

                                lat=Double.parseDouble(Latitud);
                                lon=Double.parseDouble(Longitud);

                                LatLng location = new LatLng(lat, lon);

                                MarkerOptions markerOptions = new MarkerOptions().position(location).title(location.toString());
                                mGoogleMap.addMarker(markerOptions);

                                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(location));




                                Propiedad objPropiedad=new Propiedad(idRegistroBD,tituloBD,categoriaBD,provinciaBD,garageBD,
                                        Integer.parseInt(numHabitacionesBD),Float.parseFloat(precioBD),
                                        ubicBD,propietarioBD);



                                adapter.add(objPropiedad);
                            }
                            listviewDetalle.setAdapter(adapter);

                        } else {
                            Toast.makeText(getContext(),"No se encontraron propiedades",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {



        MapsInitializer.initialize(getContext());
        mGoogleMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        /*googleMap.addMarker(new MarkerOptions().position(new LatLng(40.689247,-74.044502)).title("libertad").snippet("espero ir"));
        CameraPosition Liberty= CameraPosition.builder().target(new LatLng(40.689247,-74.044502)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));*/



    LatLng location = new LatLng(lat, lon);

        MarkerOptions markerOptions = new MarkerOptions().position(location).title(location.toString());
        mGoogleMap.addMarker(markerOptions);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(location));

    }
}


