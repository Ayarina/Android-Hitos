package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Home extends AppCompatActivity {

    FloatingActionButton boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        boton = findViewById(R.id.home_settings_button);

        Intent intent = getIntent();
        Usuario usuario = (Usuario) intent.getSerializableExtra("LogData");

        //Pasa usuario a MostrarInformacion
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, MostrarInformacion.class);
                intent.putExtra("UserData", usuario);
                startActivity(intent);
            }
        });


    }


}