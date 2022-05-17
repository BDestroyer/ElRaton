package com.inacap.elraton.clase;

public class usuario
{
    private String email;
    private String nombre;
    private String apellido;
    private String contrasenna;

    public usuario() {
    }

    public usuario(String email, String nombre, String apellido, String contrasenna) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenna = contrasenna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }
}
