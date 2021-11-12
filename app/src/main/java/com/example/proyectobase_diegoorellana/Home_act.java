package com.example.proyectobase_diegoorellana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import Objetos.Insumos;

public class Home_act extends AppCompatActivity {
    private VideoView video;

    private Insumos in = new Insumos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        video = findViewById(R.id.vw);

        String ruta = "android.resource://"+getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(ruta); //parseo la ruta
        video.setVideoURI(uri);//al videoview le paso mi video
        video.start();

        //Controles para el video
        //MediaController media = new MediaController(this);
        //video.setMediaController(media);


    }

    public void Clases(View view){
        Intent i = new Intent(this, Clases_act.class);
        startActivity(i);

    }

    public void Insumos(View v){
        Intent i =  new Intent(this, Insumos_act.class);
        Bundle bun = new Bundle(); //necesario para enviar los arreglos
        bun.putStringArray("Insumos",in.getInsumos());
        i.putExtras(bun); //envio el bundle desde el intent
        startActivity(i);
    }
}