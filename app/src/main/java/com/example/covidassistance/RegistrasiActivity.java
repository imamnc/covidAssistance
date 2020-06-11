package com.example.covidassistance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegistrasiActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    static EditText in_nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        /* Back Button Action Bar */
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /* Proses Registrasi */
        mAuth = FirebaseAuth.getInstance();

        final EditText in_email = findViewById(R.id.email);
        final EditText in_password = findViewById(R.id.password);
        in_nama = findViewById(R.id.nama);

        Button register = findViewById(R.id.registrasi);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = in_email.getText().toString();
                String password = in_password.getText().toString();

                if(email.length() != 0 & password.length() != 0){
                    mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegistrasiActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(RegistrasiActivity.this, "Registrasi Berhasil",
                                        Toast.LENGTH_SHORT).show();

                                // Update Display Name
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(in_nama.getText().toString())
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RegistrasiActivity.this, "Display Name Updated",
                                                            Toast.LENGTH_SHORT).show();
                                                    // Go to login Activity
                                                    finish();
                                                }
                                            }
                                        });
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(RegistrasiActivity.this, "Registrasi Gagal",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegistrasiActivity.this, "Pastikan Semua Field Terisi !",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // Change Activity to Home
        if(currentUser != null){
            Intent toHome = new Intent(RegistrasiActivity.this, Home.class);
            startActivity(toHome);
        }
    }
}
