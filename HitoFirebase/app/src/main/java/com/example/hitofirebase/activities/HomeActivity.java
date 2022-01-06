package com.example.hitofirebase.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.hitofirebase.R;
import com.example.hitofirebase.models.User;
import com.example.hitofirebase.persistence.ReadAndWriteSnippets;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    private TextView texto;
    private ReadAndWriteSnippets database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = new ReadAndWriteSnippets();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        texto = findViewById(R.id.home_texto);


        /** Leer el username, se hará mediante un listener, de modo que si se actualiza algún dato
         * del user (como que se cambie el nombre de usuario en la db o en la app) se actualizará también
         */

        DatabaseReference users = database.getUsersReference(user.getUid());

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                String mensajeBienvenida = "Bienvenido " + user.getUsername() + " a la aplicación";
                texto.setText(mensajeBienvenida);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Home", "loadUsername:onCancelled", error.toException());

            }
        });





    }
}