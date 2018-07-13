package com.example.bipin.conftest6;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.materialize.color.Material;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    DatabaseReference db;
    FirebaseHelper helper;
    CustomAdapter adapter;
    GridView gv;
    EditText nameEditTxt, descEditTxt, dateEditTxt, linkEditTxt;
    String TAG = "broo";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Main Events");
        setSupportActionBar(toolbar);

        gv = (GridView) findViewById(R.id.gv);

        //INITIALIZE FIREBASE DB
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();
            }
        });

        //wait dialog
          final ProgressDialog mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.show();


        //Basically Passing the function as a parameter so its called inside
        helper.retrieve(new FirebaseCallBack() {
            @Override
            public void onCallBack(ArrayList<EventsModel> list) {
                adapter = new CustomAdapter(MainActivity2.this, list);
                mProgressDialog.dismiss();
                gv.setAdapter(adapter);
            }
        });


    }

    interface FirebaseCallBack {
        void onCallBack(ArrayList<EventsModel> list);
    }


    //DISPLAY INPUT DIALOG
    private void displayInputDialog() {
        Dialog d = new Dialog(this);
        d.setTitle("Save To Firebase");
        d.setContentView(R.layout.input_dialog);

        nameEditTxt = (EditText) d.findViewById(R.id.editEvenName);
        descEditTxt = (EditText) d.findViewById(R.id.editEventDesc);
        dateEditTxt = (EditText) d.findViewById(R.id.editEventDate);
        linkEditTxt = (EditText) d.findViewById(R.id.editEventLink);

        Button saveBtn = (Button) d.findViewById(R.id.saveBtn);


        Log.d(TAG, "displayInputDialog: 2d run");

        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: 3drun");

                //GET DATA
                String name = nameEditTxt.getText().toString();
                String desc = descEditTxt.getText().toString();
                String date = dateEditTxt.getText().toString();
                String link = linkEditTxt.getText().toString();

                //SET DATA
                EventsModel s = new EventsModel();
                s.setName(name);
                s.setEventLink(link);
                s.setDescription(desc);
                s.setEventDate(date);


                //SIMPLE VALIDATION
                if (name != null && name.length() > 0) {
                    Log.d(TAG, "onClick: 4run");
                    //THEN SAVE
                    if (helper.save(s)) {
                        //IF SAVED CLEAR EDITXT
                        nameEditTxt.setText("");
                        dateEditTxt.setText("");
                        descEditTxt.setText("");
                        linkEditTxt.setText("");


                        Toast.makeText(MainActivity2.this, "Saved Succesfully", Toast.LENGTH_LONG).show();


                    }
                } else {
                    Toast.makeText(MainActivity2.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Log.d(TAG, "displayInputDialog: 5run");
        d.show();
    }


}
