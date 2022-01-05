package com.example.hitofirebase.persistence;

import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.hitofirebase.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReadAndWriteSnippets {

    private static final String TAG = "ReadAndWriteSnippets";

    private DatabaseReference mDatabase;

    public ReadAndWriteSnippets() {
        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance("https://hito-firebase-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        // [END initialize_database_ref]
    }

    /**
     * Escribe un nuevo usuario según su userId
     * **/
    public void writeNewUser(String userId, String name, String email) {
        User user = new User(userId, name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }

    public void writeNewUserWithTaskListeners(String userId, String name, String email) {
        User user = new User(userId, name, email);

        // [START rtdb_write_new_user_task]
        mDatabase.child("users").child(userId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Log.d(TAG, "Se ha añadido el usuario: " + userId);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Log.e(TAG, "No se ha podido añadir a: " + userId);
                    }
                });
        // [END rtdb_write_new_user_task]
    }

}
