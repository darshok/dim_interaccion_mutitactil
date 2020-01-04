package upv.dim.interacccionmultitactil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import upv.dim.interacccionmultitactil.Ejercicio1.Ejercicio1;
import upv.dim.interacccionmultitactil.Ejercicio2.Ejercicio2;
import upv.dim.interacccionmultitactil.Ejercicio3.Ejercicio3;
import upv.dim.interacccionmultitactil.Ejercicio4.Ejercicio4;
import upv.dim.interacccionmultitactil.Ejercicio5.Ejercicio5;
import upv.dim.interacccionmultitactil.Ejercicio6.Ejercicio6;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        AppCompatButton activity1Bt = findViewById(R.id.buttonExercise1);

        activity1Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Ejercicio1.class));
            }
        });

        AppCompatButton activity2Bt = findViewById(R.id.buttonExercise2);

        activity2Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Ejercicio2.class));
            }
        });

        AppCompatButton activity3Bt = findViewById(R.id.buttonExercise3);

        activity3Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Ejercicio3.class));
            }
        });

        AppCompatButton activity4Bt = findViewById(R.id.buttonExercise4);

        activity4Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Ejercicio4.class));
            }
        });

        AppCompatButton activity5Bt = findViewById(R.id.buttonExercise5);

        activity5Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Ejercicio5.class));
            }
        });

        AppCompatButton activity6Bt = findViewById(R.id.buttonExercise6);

        activity6Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Ejercicio6.class));
            }
        });

    }
}
