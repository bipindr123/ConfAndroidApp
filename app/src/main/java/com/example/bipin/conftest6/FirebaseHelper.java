package com.example.bipin.conftest6;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved = null;
    ArrayList<EventsModel> spacecrafts = new ArrayList<>();

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    //WRITE IF NOT NULL
    public Boolean save(EventsModel spacecraft) {
        if (spacecraft == null) {
            saved = false;
        } else {
            try {
                db.child("Events").push().setValue(spacecraft);
                saved = true;

            } catch (DatabaseException e) {
                e.printStackTrace();
                saved = false;
            }
        }

        return saved;
    }

    //IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchData(DataSnapshot dataSnapshot) {
        spacecrafts.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            EventsModel spacecraft = ds.getValue(EventsModel.class);
            spacecrafts.add(spacecraft);
            Log.d("bruh", "fetchData: "+ spacecraft.eventName);
        }

    }

    //READ BY HOOKING ONTO DATABASE OPERATION CALLBACKS
    public ArrayList<EventsModel> retrieve() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return spacecrafts;
    }
}



















