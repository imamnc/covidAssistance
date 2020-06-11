package com.example.covidassistance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, AddKasus.class);
                startActivity(i);
            }
        });

        Button signout = findViewById(R.id.logout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent toLogin = new Intent(Home.this, MainActivity.class);
                startActivity(toLogin);
            }
        });

        ImageButton positif = findViewById(R.id.btnPositif);
        positif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPositif = new Intent(Home.this, ViewPositifActivity.class);
                startActivity(toPositif);
            }
        });


        ImageButton sembuh = findViewById(R.id.btnSembuh);
        sembuh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSembuh = new Intent(Home.this, ViewSembuhActivity.class);
                startActivity(toSembuh);
            }
        });

        ImageButton mati = findViewById(R.id.btnMati);
        mati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMati = new Intent(Home.this, ViewMatiActivity.class);
                startActivity(toMati);
            }
        });

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            String nama = user.getDisplayName();
            TextView greet = findViewById(R.id.greeting);
            greet.setText(nama);
            Toast.makeText(Home.this, nama, Toast.LENGTH_SHORT).show();
        }

    }

}
