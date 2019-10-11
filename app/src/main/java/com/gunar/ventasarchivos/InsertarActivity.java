package com.gunar.ventasarchivos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsertarActivity extends AppCompatActivity {
    Productos pt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
    }

    public void insertar(View view) {
        EditText codigo = (EditText) findViewById(R.id.codigo);
        EditText cantidad = (EditText) findViewById(R.id.cantidad);
        EditText costo = (EditText) findViewById(R.id.costo);

        try {


            pt = new Productos(InsertarActivity.this);
            pt.apertura();
            long c = pt.insertarVentas(codigo.getText().toString(), cantidad.getText().toString(), costo.getText().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
