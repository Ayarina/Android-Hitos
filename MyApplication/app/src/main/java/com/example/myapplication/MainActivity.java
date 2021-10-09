package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button boton;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Datos Usuario
        EditText nombreUsuario = findViewById(R.id.main_nombre);
        EditText apellidoUsuario = findViewById(R.id.main_apellidos);
        EditText numeroUsuario = findViewById(R.id.main_numero);

        boton = findViewById(R.id.main_boton);


        //Start MostrarInformacion
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Usuario usuario = new Usuario(nombreUsuario.getText().toString(), apellidoUsuario.getText().toString(), Integer.parseInt(numeroUsuario.getText().toString()));

                Intent intent = new Intent(MainActivity.this, MostrarInformacion.class); //interaction between MainActivity and MostrarInformacion
                intent.putExtra("UserData", usuario);
                startActivity(intent);
            }
        });
    }
}