package com.example.sgm;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity9 extends AppCompatActivity {
    TextView negro,blanco,negro2,blanco2,azul,purpura,rojo,verde,gris,amarillo,naranja,rosa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        negro =(TextView) findViewById(R.id.negro);
        blanco =(TextView) findViewById(R.id.blanco);
        negro2 =(TextView) findViewById(R.id.negro2);
        blanco2 =(TextView) findViewById(R.id.blanco2);
        azul =(TextView) findViewById(R.id.azul);
        rojo =(TextView) findViewById(R.id.rojo);
        purpura =(TextView) findViewById(R.id.purpura);
        verde =(TextView) findViewById(R.id.verde);
        gris =(TextView) findViewById(R.id.gris);
        amarillo =(TextView) findViewById(R.id.amarillo);
        naranja =(TextView) findViewById(R.id.naranja);
        rosa =(TextView) findViewById(R.id.rosa);

        negro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int negro = Color.parseColor("#FF000000");
                getResources().getColor(R.color.azul);
                getResources().getColor(R.color.purple);
            }
        });
    }
}