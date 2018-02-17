package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yosua on 15/02/2018.
 */

public class SQLite_OpenHelper extends SQLiteOpenHelper{


    public SQLite_OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /****************************************************************************
         *   Creación tabla usuarios                                                *
         *       -ID: identificador del usuario, se incrementa automaticamente      *
         *       -Nombre: nombre del usuario                                        *
         *       -Email: email del usuario con el que hará login en la app          *
         *       -Password: contraseña del usuario con la que hará login en la app  *
         ****************************************************************************/
        String query = "create table usuarios(_ID integer primary key autoincrement, Nombre text, Email text, Password text);";
        db.execSQL(query);

        /****************************************************************************
         *   Creación tabla contactos                                               *
         *       -ID_Contacto: identificador del contacto, se incrementa automaticamente     *
         *       -Nombre: nombre del contacto                                       *
         *       -Telefono: número de teléfono del contacto                         *
         *       -Email: email del contacto                                         *
         *       -ID_Usuario: id del usuario al que pertenece el contacto           *
         ****************************************************************************/
        query = "create table contactos(ID_Contacto integer primary key autoincrement, Nombre text, Telefono text, Email text, ID_Usuario int);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Método para abrir la base de datos
    public void abrirBD() {
        this.getWritableDatabase();
    }

    //Método para cerrar la base de datos
    public void cerrarBD() {
        this.close();
    }

    //Método para registrar usuarios
    public void insertarUsuario(String nombre, String email, String password) {
        ContentValues valores = new ContentValues();
        valores.put("Nombre", nombre);
        valores.put("Email", email);
        valores.put("Password", password);
        this.getWritableDatabase().insert("usuarios", null, valores);
    }

    //Método para hacer login
    public Cursor comprobarLogin(String email, String password) throws SQLException{
        Cursor cursor = this.getReadableDatabase().query("usuarios", new String[]{"_ID", "Nombre", "Email", "Password"}, "Email like '" + email + "' and Password like '" + password + "'", null, null, null, null);
        return cursor;
    }

    //Método para recuperar la lista de contactos del usuario que se pasa como parámetro
    public Cursor recuperarListaContactos (int idUsuario) throws SQLException {
        Cursor cursor = this.getReadableDatabase().query("contactos", new String[]{"ID_Contacto", "Nombre", "Telefono", "Email"}, "ID_Usuario = " + idUsuario, null, null, null, null);
        return cursor;
    }

    public void insertarContacto(int idUsuario, String nombre, String telefono, String email) {
        ContentValues valores = new ContentValues();
        valores.put("Nombre", nombre);
        valores.put("Telefono", telefono);
        valores.put("Email", email);
        valores.put("ID_Usuario", idUsuario);
        this.getWritableDatabase().insert("contactos", null, valores);
    }
}
