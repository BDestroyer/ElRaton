package com.inacap.elraton.clase;

import android.graphics.Bitmap;

public class producto
{
    private String titulo, descripcion;
    private int id, precio, cantidad;
    private Bitmap foto;

    public producto() {
    }

    public producto(String titulo, String descripcion, int id, int precio, int cantidad, Bitmap foto) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.id = id;
        this.precio = precio;
        this.cantidad = cantidad;
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}