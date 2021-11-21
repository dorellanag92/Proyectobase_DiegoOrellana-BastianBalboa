package com.example.proyectobase_diegoorellana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectobase_diegoorellana.database.AdminSQLiteOpenHelper;

public class Registro_act extends AppCompatActivity {

    private EditText idu, usr, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        idu = findViewById(R.id.regid);
        usr = findViewById(R.id.reguser);
        pass = findViewById(R.id.regpass);

    }

    public void registrarUsuario(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"biofit",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String idreg = idu.getText().toString();
        String usrreg = usr.getText().toString();
        String passreg = pass.getText().toString();
        if (!idreg.isEmpty() && !usrreg.isEmpty() && !passreg.isEmpty()){
            ContentValues cont = new ContentValues();
            cont.put("id",idreg);
            cont.put("usuario",usrreg);
            cont.put("contrasena",passreg);
            db.insert("Usuarios",null,cont);
            db.close();
            Limpiar();
            Toast.makeText(this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Los campos están vacíos", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarUsuario(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"biofit",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String idreg = idu.getText().toString();
        if(!idreg.isEmpty()){
            int cantidad = db.delete("Usuarios","id="+idreg,null);
            db.close();
            Limpiar();
            if(cantidad==1){
                Toast.makeText(this, "Eliminaste al usuario asociado a: "+idreg, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "No existe el usuario en la base de datos.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "No hay usuario asosiado.", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizarUsuario(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"biofit",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String idreg = idu.getText().toString();
        String usrreg = usr.getText().toString();
        String passreg = pass.getText().toString();

        if (!idreg.isEmpty() && !usrreg.isEmpty() && !passreg.isEmpty()){
            ContentValues cont = new ContentValues();
            cont.put("usuario",usrreg);
            cont.put("contrasena",passreg);
            db.update("Usuarios",cont,"id="+idreg,null);
            db.close();
            Limpiar();
            Toast.makeText(this, "Has actualizado al usuario", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getBaseContext(), "No deben haber campos vacíos", Toast.LENGTH_SHORT).show();
        }
    }

    public void mostrarClase(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"biofit",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String idreg = idu.getText().toString();
        if(!idreg.isEmpty()){
            Cursor file = db.rawQuery("SELECT usuario, contrasena FROM Usuarios WHERE id="+idreg,null);
            if(file.moveToFirst()){
                usr.setText(file.getString(0));
                pass.setText(file.getString(1));
            }
            else{
                Toast.makeText(this, "No hay elementos", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "No hay clase asociada", Toast.LENGTH_SHORT).show();
        }
    }

    public void Limpiar(){
        idu.setText("");
        usr.setText("");
        pass.setText("");
    }
}