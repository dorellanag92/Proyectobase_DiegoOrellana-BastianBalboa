package com.example.proyectobase_diegoorellana;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import Objetos.Insumos;

public class Insumos_act extends AppCompatActivity {

    private Spinner insumos;
    private TextView result;
    private RatingBar calificar;
    private Insumos in = new Insumos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insumos);

        insumos = findViewById(R.id.spnInsumos);
        result = findViewById(R.id.result);
        calificar = findViewById(R.id.rt);

        //recibo los extras
        Bundle bun = getIntent().getExtras();
        String[] listado = bun.getStringArray("Insumos"); //guardo la lista desde su referencia

        //relleno el spinner
        ArrayAdapter adaptInsumos = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listado);
        insumos.setAdapter(adaptInsumos);
    }

    public void Calcular(View view){
        String opcion = insumos.getSelectedItem().toString(); //Se obtiene la seleccion
        int resultado = 0;
        for(int i = 0; i < opcion.length(); i++){
                if(opcion.equals(in.getInsumos()[i])){
                    resultado = in.anadirAdicional(in.getPrecios()[i],8000);
                    calificar.setRating(in.getRatings()[i]);
                    break;
                }
        }
        result.setText("El precio de "+ opcion+" más el envío, es: $"+resultado);
    }
}