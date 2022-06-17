package com.inacap.elraton.clase;

public class carrito
{
    int id, cantidad;

    public carrito() {
    }

    public carrito(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
