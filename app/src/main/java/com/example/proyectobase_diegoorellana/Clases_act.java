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

public class Clases_act extends AppCompatActivity {

    private EditText codigo, clase, intens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);
        codigo = findViewById(R.id.codigo);
        clase = findViewById(R.id.clase);
        intens = findViewById(R.id.intens);
    }

    //Metodo para añadir clases
    public void guardarClase(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"biofit",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String cdg = codigo.getText().toString();
        String cls = clase.getText().toString();
        String its = intens.getText().toString();
        if (!cdg.isEmpty() && !cls.isEmpty() && !its.isEmpty()){
            ContentValues cont = new ContentValues();
            cont.put("codigo",cdg);
            cont.put("clases",cls);
            cont.put("intensidad",its);
            db.insert("Clases",null,cont);
            db.close();
            Clear();
            Toast.makeText(this, "Has guardado una clase", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Los campos están vacíos", Toast.LENGTH_SHORT).show();
        }
    }

    public void mostrarClase(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"biofit",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String cdg = codigo.getText().toString();
        if(!cdg.isEmpty()){
            Cursor file = db.rawQuery("SELECT clases, intensidad FROM Clases WHERE codigo="+cdg,null);
            if(file.moveToFirst()){
                clase.setText(file.getString(0));
                intens.setText(file.getString(1));
            }
            else{
                Toast.makeText(this, "No hay elementos", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "No hay clase asociada", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarClase(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"biofit",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String cdg = codigo.getText().toString();
        if(!cdg.isEmpty()){
            int cantidad = db.delete("Clases","codigo="+cdg,null);
            db.close();
            Clear();
            if(cantidad==1){
                Toast.makeText(this, "Eliminaste la clase asociada a: "+cdg, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "No existe la clase en la base de datos.", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "No hay clase asosiada.", Toast.LENGTH_SHORT).show();
        }
    }

    public void actualizarClase(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"biofit",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String cdg = codigo.getText().toString();
        String cls = clase.getText().toString();
        String its = intens.getText().toString();

        if (!cdg.isEmpty() && !cls.isEmpty() && !its.isEmpty()){
            ContentValues cont = new ContentValues();
            cont.put("clases",cls);
            cont.put("intensidad",its);
            db.update("Clases",cont,"codigo=:"+cdg,null);
            db.close();
            Clear();
            Toast.makeText(this, "Has actualizado la clase", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getBaseContext(), "Hay campos vacíos", Toast.LENGTH_SHORT).show();
        }
    }

    public void Clear(){
        codigo.setText("");
        clase.setText("");
        intens.setText("");
    }
}