package com.example.hitofirebase.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hitofirebase.R;
import com.example.hitofirebase.models.User;
import com.example.hitofirebase.persistence.ReadAndWriteSnippets;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public static User currentUser;
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    private ReadAndWriteSnippets database;

    private Button botonLogin, botonRegister;
    private EditText correo, contraseña;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Inicializamos la referencia
        database = new ReadAndWriteSnippets();

        correo = findViewById(R.id.correo_login);
        contraseña = findViewById(R.id.contraseña_login);
        botonLogin = findViewById(R.id.boton_login);
        botonRegister = findViewById(R.id.boton_gotoregister);

        botonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(correo.getText().toString(), contraseña.getText().toString());
            }
        });

    }

   /* @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            Toast.makeText(MainActivity.this, "Ha iniciado sesión satisfactoriamente", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    } Hasta que no haya cerrar sesion... */

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            currentUser = new User(user.getUid(), database.getUsername(user.getUid()), email);

                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            Toast.makeText(MainActivity.this, "Bienvenido" + currentUser.getUsername() +
                                    "/" + currentUser.getCorreo(), Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}