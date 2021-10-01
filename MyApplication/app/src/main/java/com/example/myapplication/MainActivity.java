package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView texto;
    private Button boton;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = findViewById(R.id.texto_hito);
        boton = findViewById(R.id.boton_hito);
        editText = findViewById(R.id.intent_text);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texto.setText("Success");

                Intent intent = new Intent(MainActivity.this, MostrarInformacion.class);
                intent.putExtra("intent_text", editText.getText().toString());
                startActivity(intent);
            }
        });
    }
}