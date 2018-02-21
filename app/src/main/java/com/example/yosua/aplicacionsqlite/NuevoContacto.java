package com.example.yosua.aplicacionsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class NuevoContacto extends AppCompatActivity {

    private EditText etNombreCliente, etTelefonoCliente, etEmailCliente;
    private Button btnGuardarContacto;

    private final SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "BD1", null, 1);
    private int idUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        //Obtenemos los id del usuario logueado
        Bundle bundle = getIntent().getExtras();
        idUsuario = bundle.getInt("idUsuario");

        etNombreCliente = (EditText)findViewById(R.id.etNombreCliente);
        etTelefonoCliente = (EditText)findViewById(R.id.etNumeroCliente);
        etEmailCliente = (EditText)findViewById(R.id.etEmailCliente);
        btnGuardarContacto = (Button)findViewById(R.id.btnGuardarContacto);

        btnGuardarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.abrirBD();
                helper.insertarContacto(idUsuario, etNombreCliente.getText().toString(), etTelefonoCliente.getText().toString(), etEmailCliente.getText().toString());
                helper.cerrarBD();

                Toast.makeText(getApplicationContext(), "Contacto añadido", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
