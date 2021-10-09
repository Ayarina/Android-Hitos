package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MostrarInformacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_informacion);

        //Datos del usuario
        TextView nombreUsuario = findViewById(R.id.info_nombre),
        apellidoUsuario = findViewById(R.id.info_apellidos),
        numeroUsuario = findViewById(R.id.info_numero);

        //Usuario actualizado
        Intent intent = getIntent();
        Usuario usuario = (Usuario) intent.getSerializableExtra("UserData");
        nombreUsuario.setText(usuario.getNombre());
        apellidoUsuario.setText(usuario.getApellidos());
        numeroUsuario.setText(String.valueOf(usuario.getNumero()));
    }
}