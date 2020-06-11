package com.example.covidassistance;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HelperDatabasePositif {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private Query query;
    private List<Kasus> positif = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(List<Kasus> positif, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public HelperDatabasePositif() {
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("Kasus");
        query = FirebaseDatabase.getInstance().getReference("Kasus").orderByChild("tipe").equalTo("Positif");
    }

    public void readPositif(final DataStatus dataStatus){
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                positif.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Kasus kasus = keyNode.getValue(Kasus.class);
                    positif.add(kasus);
                }
                dataStatus.DataIsLoaded(positif, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updatePositif(String key, Kasus kasus, final HelperDatabasePositif.DataStatus dataStatus){
        mReference.child(key).setValue(kasus).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deletePositif(String key, final HelperDatabasePositif.DataStatus dataStatus){
        mReference.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }

}
