package com.example.covidassistance;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddKasus extends AppCompatActivity {

    private DatabaseReference database;
    private String input_nip, input_nama, input_tipe, input_provinsi, input_kota, input_kecamatan, input_kelurahan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kasus);

        /* DB INSIALISASI */
        database = FirebaseDatabase.getInstance().getReference();

        /* Select Provinsi */
        final Spinner provinsi = findViewById(R.id.provinsi);
        ArrayList opsiProvinsi = new ArrayList<String>();
        opsiProvinsi.add("DKI Jakarta");
        opsiProvinsi.add("Jawa Barat");
        opsiProvinsi.add("Jawa Tengah");
        opsiProvinsi.add("Jawa Timur");
        ArrayAdapter<String> adapterProvinsi = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,opsiProvinsi);
        provinsi.setAdapter(adapterProvinsi);

        /* Select Kota */
        final Spinner kota = findViewById(R.id.kota);
        ArrayList opsiKota = new ArrayList<String>();
        opsiKota.add("Jakarta Pusat");
        opsiKota.add("Jakarta Selatan");
        opsiKota.add("Jakarta Barat");
        opsiKota.add("Jakarta Timur");
        opsiKota.add("Jakarta Utara");
        opsiKota.add("Bandung");
        opsiKota.add("Bekasi");
        opsiKota.add("Bogor");
        opsiKota.add("Semarang");
        opsiKota.add("Solo");
        opsiKota.add("Surabaya");
        opsiKota.add("Gresik");
        opsiKota.add("Sidoarjo");
        ArrayAdapter<String> adapterKota = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,opsiKota);
        kota.setAdapter(adapterKota);

        /* Select Kecamatan */
        final Spinner kecamatan = findViewById(R.id.kecamatan);
        ArrayList opsiKecamatan = new ArrayList<String>();
        opsiKecamatan.add("Kemayoran");
        opsiKecamatan.add("Kelapa Gading");
        opsiKecamatan.add("Pulo Gadung");
        opsiKecamatan.add("Kebayoran Lama");
        opsiKecamatan.add("Kebon Jeruk");
        opsiKecamatan.add("Rungkut");
        opsiKecamatan.add("Keputih");
        opsiKecamatan.add("Benowo");
        opsiKecamatan.add("Wiyung");
        opsiKecamatan.add("Wonokromo");
        opsiKecamatan.add("Rungkut");
        opsiKecamatan.add("Keputih");
        opsiKecamatan.add("Buah Batu");
        opsiKecamatan.add("Sukasari");
        opsiKecamatan.add("Kiaracondong");
        opsiKecamatan.add("Bogor Barat");
        opsiKecamatan.add("Bogor Selatan");
        opsiKecamatan.add("Bogor Tengah");
        opsiKecamatan.add("Menganti");
        opsiKecamatan.add("Cerme");
        opsiKecamatan.add("Manyar");
        opsiKecamatan.add("Driyorejo");
        ArrayAdapter<String> adapterKecamatan = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,opsiKecamatan);
        kecamatan.setAdapter(adapterKecamatan);

        /* Select Kelurahan */
        final Spinner kelurahan = findViewById(R.id.kelurahan);
        ArrayList opsiKelurahan = new ArrayList<String>();
        opsiKelurahan.add("Domas");
        opsiKelurahan.add("Boteng");
        opsiKelurahan.add("Putat Lor");
        opsiKelurahan.add("Bantar Jati");
        opsiKelurahan.add("Cibadak");
        opsiKelurahan.add("Batu Tulis");
        opsiKelurahan.add("Sidosermo");
        opsiKelurahan.add("Medokan");
        opsiKelurahan.add("Panjang Jiwo");
        ArrayAdapter<String> adapterKelurahan = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,opsiKelurahan);
        kelurahan.setAdapter(adapterKelurahan);

        /* Select Tipe Kasus */
        final Spinner tipe = findViewById(R.id.opsiTipeKasus);
        ArrayList opsiTipe = new ArrayList<String>();
        opsiTipe.add("Positif");
        opsiTipe.add("Sembuh");
        opsiTipe.add("Meninggal");
        ArrayAdapter<String> adapterTipe = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,opsiTipe);
        tipe.setAdapter(adapterTipe);

        /* Back Button Action Bar */
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final EditText fieldNip = findViewById(R.id.inputNIP);
        final EditText fieldNama = findViewById(R.id.inputNama);

        /* Submit Kasus */
        Button submitKasus = findViewById(R.id.submitKasus);
        submitKasus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Get Data */
                input_nip = fieldNip.getText().toString();
                input_nama = fieldNama.getText().toString();
                input_tipe = tipe.getSelectedItem().toString();
                input_provinsi = provinsi.getSelectedItem().toString();
                input_kota = kota.getSelectedItem().toString();
                input_kecamatan = kecamatan.getSelectedItem().toString();
                input_kelurahan = kelurahan.getSelectedItem().toString();
                Date new_date = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String input_tanggal = df.format(new_date);
                /* Insert Data */
                if(input_nip.length() != 0 & input_nama.length() !=0 & input_tipe.length() != 0 & input_provinsi.length() != 0
                        & input_kota.length() != 0 & input_kecamatan.length() != 0 & input_kelurahan.length() != 0 & input_tanggal.length() != 0) {
                            /* Insert Data */
                            Data_kasus data = new Data_kasus(input_nip, input_nama, input_tipe, input_provinsi, input_kota, input_kecamatan, input_kelurahan, input_tanggal);
                            database.child("Kasus").push().setValue(data).addOnSuccessListener(AddKasus.this, new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(AddKasus.this, "Berhasil menambahkan !", Toast.LENGTH_SHORT).show();
                                    fieldNip.setText("");
                                    fieldNama.setText("");
                                }
                            });
                }else{
                    Toast.makeText(AddKasus.this, "Pastikan Semua Field Terisi !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
