package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button boton;
    private EditText editText;
    String Token;
    private TinyDB tinyDB; //Creamos objeto TinyDB

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tinyDB = new TinyDB(this); //Inicializamos la base de datos

        String token = "uwu";

        //Datos Usuario
        EditText nombreUsuario = findViewById(R.id.main_nombre);
        EditText apellidoUsuario = findViewById(R.id.main_apellidos);
        EditText numeroUsuario = findViewById(R.id.main_numero);
        EditText codigoUsuario = findViewById(R.id.main_codigo);

        //Datos del usuario anterior
        TextView lastName = findViewById(R.id.main_lastName);
        TextView lastSurname = findViewById(R.id.main_lastSurname);
        TextView lastNumber = findViewById(R.id.main_lastNumber);

        //Intentar actualizar los ultimos datos por si está vacío y no hay nada guardado.
        try{
            Usuario lastUser = tinyDB.getObject("LastUser", Usuario.class); //Sacamos el objeto almacenado con la key "LastUser"
            lastName.setText(lastUser.getNombre());
            lastSurname.setText(lastUser.getApellidos());
            lastNumber.setText(String.valueOf(lastUser.getNombre()));
        }
        catch(Exception e){
            e.printStackTrace();
        }

        boton = findViewById(R.id.main_boton);

        //Va a Home, que contiene la información del usuario si pasa bien el "token".
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(codigoUsuario.getText().toString().equals(token)){
                    Usuario usuario = new Usuario(nombreUsuario.getText().toString(), apellidoUsuario.getText().toString(), Integer.parseInt(numeroUsuario.getText().toString()));
                    tinyDB.putObject("LastUser", usuario);

                    Intent intent = new Intent(MainActivity.this, Home.class); //interaction between MainActivity and MostrarInformacion
                    intent.putExtra("LogData", usuario);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this, "Prueba poner uwu", Toast.LENGTH_LONG).show();
            }
        });
    }
}

/*
* Saco de la BD la información del último usuario?
* */