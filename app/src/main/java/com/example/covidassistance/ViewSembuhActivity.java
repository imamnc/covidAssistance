package com.example.covidassistance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class ViewSembuhActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sembuh);
        mRecyclerView = findViewById(R.id.sembuh_list);
        new HelperDatabaseSembuh().readSembuh(new HelperDatabaseSembuh.DataStatus() {
            @Override
            public void DataIsLoaded(List<Kasus> sembuh, List<String> keys) {
                findViewById(R.id.progresSembuh).setVisibility(View.GONE);
                new RecyclerSembuh().setConfig(mRecyclerView, ViewSembuhActivity.this, sembuh, keys);
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
