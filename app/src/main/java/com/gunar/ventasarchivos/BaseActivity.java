package com.gunar.ventasarchivos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseActivity extends AppCompatActivity {


    String contenido = "", nombreCompleto = "", carnet = "";
    Productos pt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);


        try {
            pt = new Productos(BaseActivity.this);
            pt.apertura();
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(pt.listarProductos());
        } catch (Exception e) {
            e.printStackTrace();
        }

//
//        if (savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//            if(extras != null) {
//                carnet = extras.getString("carnet");
//                buscar(null);
//
//            }
//        }

    }


    public void buscar(View view) {

        EditText cod = (EditText) findViewById(R.id.carnet);

//        Toast.makeText(this, pt.obtenerCod(cod.getText().toString()), Toast.LENGTH_LONG).show();

        String respuesta = pt.obtenerCod(cod.getText().toString());

        long startTime = System.currentTimeMillis();


        String[] separado = respuesta.split(";");
        String codigo = separado[0];
        String producto = separado[1];
        String univen = separado[2];
        String uniem = separado[3];
        String lin = separado[4];
        String existencia = separado[5];

        EditText descripcion = (EditText) findViewById(R.id.paterno);

        EditText uniVen = (EditText) findViewById(R.id.nombres);
        EditText unixEnvase = (EditText) findViewById(R.id.telefono);
        EditText codLinea = (EditText) findViewById(R.id.aportes);
        EditText codExis = (EditText) findViewById(R.id.totalAportes);


        descripcion.setText(producto);
        uniVen.setText(univen);
        unixEnvase.setText(uniem);
        codLinea.setText(lin);
        codExis.setText(existencia);

        String max = pt.obtenerMax(cod.getText().toString());
        EditText precioVenta = (EditText) findViewById(R.id.materno);
            precioVenta.setText(max + "");

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        EditText tiempo = (EditText) findViewById(R.id.tiempo);
        tiempo.setText(timeElapsed + " millis");

    }
}
