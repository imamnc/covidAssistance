package com.example.covidassistance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class ViewMatiActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mati);
        mRecyclerView = findViewById(R.id.mati_list);
        new HelperDatabaseMati().readMati(new HelperDatabaseMati.DataStatus() {
            @Override
            public void DataIsLoaded(List<Kasus> mati, List<String> keys) {
                findViewById(R.id.progresMati).setVisibility(View.GONE);
                new RecyclerMati().setConfig(mRecyclerView, ViewMatiActivity.this, mati, keys);
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
