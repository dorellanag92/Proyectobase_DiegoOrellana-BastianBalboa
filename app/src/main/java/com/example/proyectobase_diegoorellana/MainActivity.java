package com.example.proyectobase_diegoorellana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectobase_diegoorellana.database.AdminSQLiteOpenHelper;

import Objetos.Administrador;
import Objetos.Insumos;

public class MainActivity extends AppCompatActivity {

    private EditText user, pass;
    private TextView msj;
    private Button btn;
    private ProgressBar pb;
    private Administrador adm = new Administrador();
    private Insumos in = new Insumos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.reguser);
        pass = findViewById(R.id.regpass);
        msj = findViewById(R.id.msj);
        btn = findViewById(R.id.btn);
        pb = findViewById(R.id.pb);

        msj.setVisibility(View.INVISIBLE);
        pb.setVisibility(View.INVISIBLE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task().execute();
            }
        });
    }

    //Tarea asincrona
    class Task extends AsyncTask<String, Void, String> {

        //Define la configuracion inicial de mi tarea
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            msj.setVisibility(View.INVISIBLE);
            pb.setVisibility(View.VISIBLE);
        }

        //Realiza el proceso en segundo plano para poder correr la tarea pesada
        @Override
        protected String doInBackground(String... strings) {
            try {
                for (int i = 0; i <= 2; i++) {
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            return null;
        }

        //Finaliza la tarea
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "biofit", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            pb.setVisibility(View.VISIBLE);

            String usr = user.getText().toString();
            String pas = pass.getText().toString();

            if (!usr.isEmpty() && !pas.isEmpty()) {
                Cursor fila = db.rawQuery("SELECT usuario, contrasena FROM Usuarios where usuario= '" + usr + "' and contrasena= '" + pas + "'", null);
                if (fila.moveToFirst()) {
                    Intent i = new Intent(getBaseContext(), Home_act.class);
                    //preparo los extras
                    Bundle bun = new Bundle(); //necesario para enviar los arreglos
                    bun.putStringArray("Insumos",in.getInsumos());
                    i.putExtras(bun); //envio el bundle desde el intent
                    startActivity(i);
                } else {
                    Toast.makeText(getBaseContext(), "Usuario inexistente", Toast.LENGTH_SHORT).show();
                }
                db.close();
                pb.setVisibility(View.INVISIBLE);
            }
            else {
                Toast.makeText(getBaseContext(), "No debes dejar campos vacíos", Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.INVISIBLE);
            }
        }


          /*String userObj = adm.getUser().trim();
            String passObj = adm.getPass().trim();

            switch(usuario){

                case "Diego":
                    if(usuario.equals(userObj) && contrasena.equals(passObj)){
                        //iniciar sesion
                        msj.setVisibility(View.INVISIBLE);
                        Intent i =  new Intent(getBaseContext(), Home_act.class);

                        //preparo los extras
                        Bundle bun = new Bundle(); //necesario para enviar los arreglos
                        bun.putStringArray("Insumos",in.getInsumos());
                        i.putExtras(bun); //envio el bundle desde el intent

                        startActivity(i);
                    }
                    else{
                        msj.setVisibility(View.VISIBLE);
                        pb.setVisibility(View.INVISIBLE);
                        msj.setText("Campos incorrectos. Intente nuevamente.");

                    }
                    break;

                case "":
                    if(usuario.equals("") || contrasena.equals("")){
                        //campos vacios
                        msj.setVisibility(View.VISIBLE);
                        pb.setVisibility(View.INVISIBLE);
                        msj.setText("Campos vacíos. Intente nuevamente.");
                    }
                    break;

                default:
                    if(!usuario.equals(userObj) || !contrasena.equals(passObj)){
                        //Campos incorrectos
                        msj.setVisibility(View.VISIBLE);
                        pb.setVisibility(View.INVISIBLE);
                        msj.setText("Campos incorrectos. Intente nuevamente.");
                    }
                    break;
            }*/
    }


    public void Info(View view){
        Intent i = new Intent(this, Info_act.class);
        startActivity(i);
    }

    public void Registrar(View view){
        Intent i = new Intent(this, Registro_act.class);
        startActivity(i);
    }

    public void Cerrar(View view){
        finish();
        System.exit(0);
    }

    public void Facebook(View view){
        Intent i = new Intent(Intent.ACTION_VIEW); //abre sitio web
        i.setData(Uri.parse("https://www.facebook.com/")); //recibe direccion web
        startActivity(i); //Se inicia el activity
    }

    public void Youtube(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.youtube.com/"));
        startActivity(i);
    }

    public void Twitter(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.twitter.com/"));
        startActivity(i);
    }
}