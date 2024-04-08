package com.example.sgm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    ImageView etprofesores,etdatos,etcroquis,etdescarga,ethorarios;
    TextView tvayuda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        etprofesores=(ImageView) findViewById(R.id.etprofesores);
        etdatos=(ImageView) findViewById(R.id.etdatos);
        etcroquis=(ImageView) findViewById(R.id.etcroquis);
        etdescarga=(ImageView) findViewById(R.id.etdescarga);
        ethorarios=(ImageView) findViewById(R.id.ethorarios);
        tvayuda=(TextView) findViewById(R.id.tvayuda);
        etprofesores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity2.this, MainActivity5.class);
                startActivity(intent);
            }
        });
        etdescarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity2.this, MainActivity4.class);
                startActivity(intent);
            }
        });
        etcroquis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity2.this, MainActivity6.class);
                startActivity(intent);
            }
        });
        ethorarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity2.this, MainActivity7.class);
                startActivity(intent);
            }
        });
        etdatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity2.this, MainActivity8.class);
                startActivity(intent);
            }
        });
        tvayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity2.this, MainActivity9.class);
                startActivity(intent);
            }
        });
    }
}