package com.gunar.ventasarchivos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MostrarVentasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_ventas);

        try {
            Productos pt = new Productos(MostrarVentasActivity.this);
            pt.apertura();
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(pt.listarVentasGanancias());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
