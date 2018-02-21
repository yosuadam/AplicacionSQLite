package com.example.yosua.aplicacionsqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import OpenHelper.SQLite_OpenHelper;

public class ItemContacto extends AppCompatActivity {

    private int id_Contacto;
    private String nombre, telefono, email;

    private final SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "BD1", null, 1);

    private EditText etNombre, etTelefono, etEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        //Obtenemos los datos del contacto del bundle
        Bundle bundle = getIntent().getExtras();
        id_Contacto = bundle.getInt("id_Contacto");
        nombre = bundle.getString("nombre");
        telefono = bundle.getString("telefono");
        email = bundle.getString("email");

        etNombre = (EditText) findViewById(R.id.etNombreContactoVista);
        etNombre.setText(nombre);

        etTelefono = (EditText) findViewById(R.id.etTelefonoClienteVista);
        etTelefono.setText(telefono);

        etEmail = (EditText)findViewById(R.id.etEmailClienteVista);
        etEmail.setText(email);
    }

    public int getId_Contacto() { return id_Contacto; }

    public String getNombre() { return nombre; }

    public String getTelefono() { return telefono; }

    public String getEmail() { return email; }

}
