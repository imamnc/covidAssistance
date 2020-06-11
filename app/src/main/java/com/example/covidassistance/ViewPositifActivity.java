package com.example.covidassistance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ViewPositifActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_positif);
        mRecyclerView = findViewById(R.id.positif_list);
        new HelperDatabasePositif().readPositif(new HelperDatabasePositif.DataStatus() {
            @Override
            public void DataIsLoaded(List<Kasus> positif, List<String> keys) {
                findViewById(R.id.progresPositif).setVisibility(View.GONE);
                new RecyclerPositif().setConfig(mRecyclerView, ViewPositifActivity.this, positif, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

        /* Back Button Action Bar */
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
