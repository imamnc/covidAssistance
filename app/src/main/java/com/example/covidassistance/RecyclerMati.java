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

import java.util.List;

public class RecyclerMati {

    private Context mContext;
    private RecyclerMati.KasusAdapter mKasusAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Kasus> kasus, List<String> keys){
        mContext = context;
        mKasusAdapter = new RecyclerMati.KasusAdapter(kasus, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mKasusAdapter);
    }

    class MatiItemView extends RecyclerView.ViewHolder{
        private TextView mNama, mTipe, mAlamat, mTanggal, mNip;
        private String key;
        private ConstraintLayout itemMati;

        public MatiItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.item_mati, parent, false));
            mNama = (TextView) itemView.findViewById(R.id.tvNama);
            mAlamat = (TextView) itemView.findViewById(R.id.tvAlamat);
            mTanggal = (TextView) itemView.findViewById(R.id.tvTanggal);
            mNip = (TextView) itemView.findViewById(R.id.tvNip);
            itemMati = (ConstraintLayout) itemView.findViewById(R.id.frame_mati);
        }

        public void bind(Kasus kasus, String key){
            mNama.setText(kasus.getNama());
            mAlamat.setText(kasus.getKelurahan() + ", " + kasus.getKecamatan() + ", " + kasus.getKota());
            mTanggal.setText(kasus.getTanggal());
            mNip.setText(kasus.getNip());
            this.key = key;
        }
    }

    class KasusAdapter extends RecyclerView.Adapter<RecyclerMati.MatiItemView>{
        private List<Kasus> mKasusList;
        private List<String> mKeys;

        public KasusAdapter(List<Kasus> mKasusList, List<String> mKeys) {
            this.mKasusList = mKasusList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public RecyclerMati.MatiItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerMati.MatiItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerMati.MatiItemView holder, final int position) {
            holder.bind(mKasusList.get(position), mKeys.get(position));
            holder.itemMati.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailSembuh = new Intent(mContext, DetailSembuhActivity.class);
                    detailSembuh.putExtra("key", mKeys.get(position));
                    detailSembuh.putExtra("nip", mKasusList.get(position).getNip());
                    detailSembuh.putExtra("nama", mKasusList.get(position).getNama());
                    detailSembuh.putExtra("tipe", "Meninggal");
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
