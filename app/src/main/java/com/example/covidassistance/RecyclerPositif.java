package com.example.covidassistance;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class RecyclerPositif {

    private Context mContext;
    private KasusAdapter mKasusAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Kasus> kasus, List<String> keys){
        mContext = context;
        mKasusAdapter = new KasusAdapter(kasus, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mKasusAdapter);
    }

    class PositifItemView extends RecyclerView.ViewHolder{
        private TextView mNama, mTipe, mAlamat, mTanggal, mNip;
        private String key;
        private ConstraintLayout itemPositif;

        public PositifItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.item_positif, parent, false));
            mNama = (TextView) itemView.findViewById(R.id.tvNama);
            mAlamat = (TextView) itemView.findViewById(R.id.tvAlamat);
            mTanggal = (TextView) itemView.findViewById(R.id.tvTanggal);
            mNip = (TextView) itemView.findViewById(R.id.tvNip);
            itemPositif = (ConstraintLayout) itemView.findViewById(R.id.frame_positif);
        }

        public void bind(Kasus kasus, String key){
            mNama.setText(kasus.getNama());
            mAlamat.setText(kasus.getKelurahan() + ", " + kasus.getKecamatan() + ", " + kasus.getKota());
            mTanggal.setText(kasus.getTanggal());
            mNip.setText(kasus.getNip());
            this.key = key;
        }
    }

    class KasusAdapter extends RecyclerView.Adapter<PositifItemView>{
        private List<Kasus> mKasusList;
        private List<String> mKeys;

        public KasusAdapter(List<Kasus> mKasusList, List<String> mKeys) {
            this.mKasusList = mKasusList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public PositifItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PositifItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull final PositifItemView holder, final int position) {
            holder.bind(mKasusList.get(position), mKeys.get(position));
            holder.itemPositif.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailSembuh = new Intent(mContext, DetailSembuhActivity.class);
                    detailSembuh.putExtra("key", mKeys.get(position));
                    detailSembuh.putExtra("nip", mKasusList.get(position).getNip());
                    detailSembuh.putExtra("nama", mKasusList.get(position).getNama());
                    detailSembuh.putExtra("tipe", "Positif");
                    detailSembuh.putExtra("provinsi", mKasusList.get(position).getProvinsi());
                    detailSembuh.putExtra("kota", mKasusList.get(position).getKota());
                    detailSembuh.putExtra("kecamatan", mKasusList.get(position).getKecamatan());
                    detailSembuh.putExtra("kelurahan", mKasusList.get(position).getKelurahan());
                    detailSembuh.putExtra("tanggal", mKasusList.get(position).getTanggal());
                    mContext.startActivity(detailSembuh);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mKasusList.size();
        }
    }

}
