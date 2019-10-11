package com.gunar.ventasarchivos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MostrarProductosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        try {
            Productos pt = new Productos(MostrarProductosActivity.this);
            pt.apertura();
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(pt.listarProductos());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
