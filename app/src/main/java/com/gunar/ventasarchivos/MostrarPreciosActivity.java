package com.gunar.ventasarchivos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MostrarPreciosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_precios);

        try {
            Productos pt = new Productos(MostrarPreciosActivity.this);
            pt.apertura();
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(pt.listarVentas());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
