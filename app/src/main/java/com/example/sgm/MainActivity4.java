package com.example.sgm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;

public class MainActivity4 extends AppCompatActivity {
    ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    TextView tvregresar;
    Button btncancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tvregresar= (TextView) findViewById(R.id.tvregresar);
        btncancelar= (Button) findViewById(R.id.btncancelar);
        progressBar = findViewById(R.id.progressBar);

        tvregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity4.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        final Thread thread = new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;

                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.interrupt();
            }
        });
    }
}