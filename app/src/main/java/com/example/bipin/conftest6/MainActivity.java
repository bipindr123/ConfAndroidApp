package com.example.bipin.conftest6;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;




import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DatabaseReference db;
    FirebaseHelper helper;
    CustomAdapter adapter;
    GridView gv;
    EditText nameEditTxt,descEditTxt,dateEditTxt,linkEditTxt;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gv= (GridView) findViewById(R.id.gv);

        //INITIALIZE FIREBASE DB
        db= FirebaseDatabase.getInstance().getReference();
        helper=new FirebaseHelper(db);

        //ADAPTER
        adapter=new CustomAdapter(this,helper.retrieve());
        gv.setAdapter(adapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();
            }
        });
    }

    //DISPLAY INPUT DIALOG
    private void displayInputDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Save To Firebase");
        d.setContentView(R.layout.input_dialog);

        nameEditTxt= (EditText) d.findViewById(R.id.editEvenName);
        descEditTxt  = (EditText) d.findViewById(R.id.editEventDesc);
        dateEditTxt = (EditText) d.findViewById(R.id.editEventDate);
        linkEditTxt =(EditText) d.findViewById(R.id.editEventDate);

        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);

        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //GET DATA
                String name=nameEditTxt.getText().toString();
                String desc=descEditTxt.getText().toString();
                String date=dateEditTxt.getText().toString();
                String link = linkEditTxt.getText().toString();

                //SET DATA
                EventsModel s=new EventsModel();
                s.setName(name);
                s.setEventLink(link);
                s.setDescription(desc);
                s.setEventDate(date);


                //SIMPLE VALIDATION
                if(name != null && name.length()>0)
                {
                    //THEN SAVE
                    if(helper.save(s))
                    {
                        //IF SAVED CLEAR EDITXT
                        nameEditTxt.setText("");
                        dateEditTxt.setText("");
                        descEditTxt.setText("");
                        linkEditTxt.setText("");


                        adapter=new CustomAdapter(MainActivity.this,helper.retrieve());
                        gv.setAdapter(adapter);

                        Toast.makeText(MainActivity.this,"Saved Succesfully",Toast.LENGTH_LONG).show();


                    }
                }else
                {
                    Toast.makeText(MainActivity.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        d.show();
    }



}