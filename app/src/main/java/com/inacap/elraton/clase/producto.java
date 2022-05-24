package com.inacap.elraton.clase;

public class producto
{
    private String descripcion ,precio, cantidad, color;

    public producto() {
    }

    public producto(String descripcion, String precio, String cantidad, String color) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
