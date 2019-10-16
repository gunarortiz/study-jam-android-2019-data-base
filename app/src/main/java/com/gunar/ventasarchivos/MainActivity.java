package com.gunar.ventasarchivos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {
    Productos pt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

    }



    public void migrar(View view){
        try {
            pt = new Productos(MainActivity.this);
            pt.apertura();

            pt.borrarProductos();
            pt.borrarPrecios();
            pt.borrarVentas();
            Toast.makeText(this, "se borraron los datos", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


        migrarPrecios();

        migrarProductos();

    }

    public void migrarProductos() {
        String estado = Environment.getExternalStorageState();


        if (!estado.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "No hay SD Card", Toast.LENGTH_LONG).show();
            finish();
        }

        try {
            File dir = Environment.getExternalStorageDirectory();
            File pts = new File(dir.getAbsolutePath()+ File.separator + "productos.csv");

            BufferedReader lee = new BufferedReader(new FileReader(pts));
            String res = "", linea;
            linea=lee.readLine();
            while ((linea=lee.readLine())!=null){
                String nuevo = linea + ";";
                String[] separado = nuevo.split(";");
                String codigo = separado[0];
                String producto = separado[1];
                String univen = separado[2];
                String uniem = separado[3];
                String lin = separado[4];
                String existencia = separado[5];

                double max = Double.parseDouble(pt.obtenerMax(codigo)) - 0.5d;

                long c = pt.insertarProductos(codigo, producto, univen, uniem, lin, existencia, max+"");
            }


        }
        catch (Exception e){
        }
    }

    public void migrarPrecios() {
        String estado = Environment.getExternalStorageState();


        if (!estado.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "No hay SD Card", Toast.LENGTH_LONG).show();
            finish();
        }

        try {
            File dir = Environment.getExternalStorageDirectory();
            File pts = new File(dir.getAbsolutePath()+ File.separator + "precios.csv");

            BufferedReader lee = new BufferedReader(new FileReader(pts));
            String res = "", linea;
            while ((linea=lee.readLine())!=null){
                String nuevo = linea + ";";
                String[] separado = nuevo.split(";");

                String cod = separado[0];
                String cat = separado[1];
                String fe = separado[2];

                String[] fechaSeparado = (fe+'/').split("/");
                String cantidad = separado[3];

                long c = pt.insertarPrecios(cod, cat, fechaSeparado[2]+"-"+fechaSeparado[1]+"-"+fechaSeparado[0], cantidad);

            }


        }
        catch (Exception e){
        }
    }

    public void base(View view) {
        Intent intent = new Intent(this, BaseActivity.class);
        startActivity(intent);
    }

    public void ventas(View view) {
        Intent intent = new Intent(this, InsertarActivity.class);
        startActivity(intent);
    }

    public void resumen(View view) {
        Intent intent = new Intent(this, MostrarVentasActivity.class);
        startActivity(intent);
    }

    public void resumenLinea(View view) {
        Intent intent = new Intent(this, MostrarPreciosActivity.class);
        startActivity(intent);
    }



    public void productos(View view) {
        Intent intent = new Intent(this, MostrarProductosActivity.class);
        startActivity(intent);
    }

    public void precios(View view) {
        Intent intent = new Intent(this, MostrarPreciosActivity.class);
        startActivity(intent);
    }


    public void finalizar(View view){
        finish();
    }
}
