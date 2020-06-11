package com.example.covidassistance;

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

public class HelperDatabaseSembuh {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private Query query;
    private List<Kasus> sembuh = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(List<Kasus> sembuh, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public HelperDatabaseSembuh() {
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("Kasus");
        query = FirebaseDatabase.getInstance().getReference("Kasus").orderByChild("tipe").equalTo("Sembuh");
    }

    public void readSembuh(final HelperDatabaseSembuh.DataStatus dataStatus){
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sembuh.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Kasus kasus = keyNode.getValue(Kasus.class);
                    sembuh.add(kasus);
                }
                dataStatus.DataIsLoaded(sembuh, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateSembuh(String key, Kasus kasus, final DataStatus dataStatus){
        mReference.child(key).setValue(kasus).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deleteSembuh(String key, final DataStatus dataStatus){
        mReference.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }

}
