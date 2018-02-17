package com.example.yosua.aplicacionsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import OpenHelper.SQLite_OpenHelper;

public class Agenda extends AppCompatActivity {

    private ArrayList<Contacto> listaContactos;
    private ListView lvContactos;
    private Button btnNuevoContacto;

    private final SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "BD1", null, 1);
    private int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        //Obtenemos los id del usuario logueado
        Bundle bundle = getIntent().getExtras();
        idUsuario = bundle.getInt("idUsuario");

        //Cargar la lista de contactos
        listaContactos = new ArrayList<Contacto>();
        cargarListaContactos();

        //Botón añadir contacto
        btnNuevoContacto = (Button)findViewById(R.id.btnNuevoContacto);
        btnNuevoContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.abrirBD();
                helper.insertarContacto(idUsuario, "Paco", "999999999", "paco@gmail.com");
                helper.cerrarBD();

                Toast.makeText(getApplicationContext(), "Contacto añadido", Toast.LENGTH_LONG).show();
            }
        });

        //Creamos el adaptador para el list view de contactos
        AdaptadorContacto adaptador = new AdaptadorContacto(this);
        lvContactos = (ListView)findViewById(R.id.lvContactos);
        lvContactos.setAdapter(adaptador);

        //Evento al pulsar sobre un contacto del list view
        lvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Agenda.this, ItemContacto.class);
                intent.putExtra("id_Contacto", listaContactos.get(i).getId_Contacto());
                intent.putExtra("nombre", listaContactos.get(i).getNombre());
                intent.putExtra("telefono", listaContactos.get(i).getTelefono());
                intent.putExtra("email", listaContactos.get(i).getEmail());
                startActivity(intent);
            }
        });


    }

    private void cargarListaContactos() {
        try {
            Cursor cursor = helper.recuperarListaContactos(idUsuario);
            while(cursor.moveToNext()) {
                Contacto contacto = new Contacto(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                listaContactos.add(contacto);
            }
            Toast.makeText(getApplicationContext(), "PASA", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ADAPTADOR
    class AdaptadorContacto extends ArrayAdapter<Contacto> {

        AppCompatActivity appCompatActivity;

        public AdaptadorContacto(AppCompatActivity context) {
            super(context, R.layout.lvw_item_contacto, listaContactos);
            appCompatActivity = context;
        }

        public View getView (int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.lvw_item_contacto, null);

            TextView textView = (TextView) item.findViewById(R.id.textView);
            textView.setText(listaContactos.get(position).getNombre());

            ImageView imageView = (ImageView) item.findViewById(R.id.imageView);
            imageView.setImageResource(R.mipmap.ic_launcher);

            return(item);
        }
    }
}
