package com.example.yosua.aplicacionsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private TextView tvRegistrarseGratis;
    private Button btnLogin;
    private final SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "BD1", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRegistrarseGratis = (TextView)findViewById(R.id.tvRegistrateGratis);
        tvRegistrarseGratis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Registro.class);
                startActivity(i);
            }
        });

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Cursor cursor = helper.comprobarLogin(etEmail.getText().toString(), etPassword.getText().toString());

                    if (cursor.getCount() > 0) {
                        Intent i = new Intent(getApplicationContext(), Agenda.class);
                        cursor.moveToFirst();
                        int aux = cursor.getInt(0);
                        Toast.makeText(getApplicationContext(), "ID: " + aux, Toast.LENGTH_SHORT).show();
                        i.putExtra("idUsuario", aux);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    }

                    etEmail.setText("");
                    etEmail.findFocus();
                    etPassword.setText("");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
