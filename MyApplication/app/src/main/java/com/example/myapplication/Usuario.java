package com.example.myapplication;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre, apellidos;
    private String numero;

    //Constructor
    public Usuario(String nombre, String apellidos, String numero) {
        this.nombre = (!nombre.trim().isEmpty()) ? nombre : "";
        this.apellidos = (!apellidos.trim().isEmpty()) ? apellidos : "";
        this.numero = (!nombre.trim().isEmpty()) ? numero : "";
    }

    //Getters & Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
