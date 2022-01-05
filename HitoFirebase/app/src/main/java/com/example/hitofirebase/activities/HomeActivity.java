package com.example.hitofirebase.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hitofirebase.R;
import com.example.hitofirebase.persistence.ReadAndWriteSnippets;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private TextView texto;
    private ReadAndWriteSnippets database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = new ReadAndWriteSnippets();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        texto = findViewById(R.id.home_texto);
        texto.setText("Bienvenido " + user.getUid()+ " a la aplicación");

    }
}