package com.example.mov001_proyectofinal_ronald_nacho.Models;


import java.io.Serializable;



public class Propiedad implements Serializable {

    private String idRegistro;
    private String titulo;
    private String categoria;
    private String provincia;
    private String garage;
    private int numHabitaciones;
    private float precio;
    private String ubicGeo;
    private String propietario;



    public Propiedad(String idRegistro,String titulo, String categoria,String provincia, String garage,int numHabitaciones, float precio, String ubicGeo, String propietario) {

        this.idRegistro=idRegistro;
        this.titulo = titulo;
        this.categoria = categoria;
        this.provincia = provincia;
        this.numHabitaciones = numHabitaciones;
        this.garage = garage;
        this.precio = precio;
        this.ubicGeo = ubicGeo;
        this.propietario = propietario;
    }



    public Propiedad(){

     }


    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getUbicGeo() {
        return ubicGeo;
    }

    public void setUbicGeo(String ubicGeo) {
        this.ubicGeo = ubicGeo;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;

    }

}
