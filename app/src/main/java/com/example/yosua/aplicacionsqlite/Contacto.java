package com.example.yosua.aplicacionsqlite;

import android.widget.TextView;

/**
 * Created by Yosua on 16/02/2018.
 */

public class Contacto {

    private int id_Contacto;
    private String nombre, telefono, email;

    public Contacto (int id_Contacto, String nombre, String telefono, String email) {
        this.id_Contacto = id_Contacto;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public int getId_Contacto() { return id_Contacto; }

    public String getNombre() { return nombre; }

    public String getTelefono() { return telefono; }

    public String getEmail() { return email; }
}
