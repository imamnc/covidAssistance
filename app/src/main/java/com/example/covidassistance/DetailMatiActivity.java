package com.example.covidassistance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class DetailMatiActivity extends AppCompatActivity {

    private EditText detailNama, detailNip;
    private Spinner detailTipe;
    private TextView detailKey;
    private Button btnEdit, btnDelete;
    private String detailPovinsi, detailKota, detailKecamatan, detailKelurahan, detailTanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mati);

        /* Select Tipe Kasus */
        final Spinner tipe = findViewById(R.id.detailTipe);
        ArrayList opsiTipe = new ArrayList<String>();
        opsiTipe.add("Positif");
        opsiTipe.add("Sembuh");
        opsiTipe.add("Meninggal");
        ArrayAdapter<String> adapterTipe = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,opsiTipe);
        tipe.setAdapter(adapterTipe);

        /* Get Resource */
        detailNama = findViewById(R.id.detailNama);
        detailNip = findViewById(R.id.detailNIP);
        detailKey = findViewById(R.id.detailKey);
        detailTipe = findViewById(R.id.detailTipe);
        btnEdit = findViewById(R.id.btnSimpan);
        btnDelete = findViewById(R.id.btnDelete);

        /* Fill Resource */
        detailKey.setText(getIntent().getStringExtra("key"));
        detailNip.setText(getIntent().getStringExtra("nip"));
        detailNama.setText(getIntent().getStringExtra("nama"));
        detailTipe.setSelection(getIndexSpinner(detailTipe, getIntent().getStringExtra("tipe")));
        detailPovinsi = getIntent().getStringExtra("provinsi");
        detailKota = getIntent().getStringExtra("kota");
        detailKecamatan = getIntent().getStringExtra("kecamatan");
        detailKelurahan = getIntent().getStringExtra("kelurahan");
        detailTanggal = getIntent().getStringExtra("tanggal");

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
            Kasus kasus = new Kasus();
            kasus.setNip(detailNip.getText().toString());
            kasus.setNama(detailNama.getText().toString());
            kasus.setTipe(detailTipe.getSelectedItem().toString());
            kasus.setProvinsi(detailPovinsi);
            kasus.setKota(detailKota);
            kasus.setKecamatan(detailKecamatan);
            kasus.setKelurahan(detailKelurahan);
            kasus.setTanggal(detailTanggal);

            new HelperDatabaseSembuh().updateSembuh(detailKey.getText().toString(), kasus, new HelperDatabaseSembuh.DataStatus() {
                @Override
                public void DataIsLoaded(List<Kasus> sembuh, List<String> keys) {

                }

                @Override
                public void DataIsInserted() {

                }

                @Override
                public void DataIsUpdated() {
                    Snackbar.make(v, "Data berhasil diubah !", Snackbar.LENGTH_LONG).show();
                    finish();
                    return;
                }

                @Override
                public void DataIsDeleted() {

                }
            });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
            new HelperDatabaseSembuh().deleteSembuh(detailKey.getText().toString(), new HelperDatabaseSembuh.DataStatus() {
                @Override
                public void DataIsLoaded(List<Kasus> sembuh, List<String> keys) {

                }

                @Override
                public void DataIsInserted() {

                }

                @Override
                public void DataIsUpdated() {

                }

                @Override
                public void DataIsDeleted() {
                    Snackbar.make(v, "Data berhasil dihapus !", Snackbar.LENGTH_LONG).show();
                    finish();
                    return;
                }
            });
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

    private int getIndexSpinner(Spinner spinner, String value){
        int index = 0;
        for(int i = 0; i<spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).equals(value)){
                index = i;
                break;
            }
        }
        return index;
    }

}
