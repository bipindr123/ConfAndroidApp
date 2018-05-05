package com.example.bipin.conftest6;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {


    TextView nameTxt, descTxt, dateTxt, linkTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);

        nameTxt = (TextView) findViewById(R.id.nameDetailTxt);
        descTxt = (TextView) findViewById(R.id.descDetailTxt);
        dateTxt = (TextView) findViewById(R.id.dateDetailTxt);

        //get intent
        Intent i = this.getIntent();


        //RECEIVE DATA
        String name = i.getExtras().getString("NAME_KEY");
        String desc = i.getExtras().getString("DESC_KEY");
        String propellant = i.getExtras().getString("DATE_KEY");
        final String evenLink = i.getExtras().getString("EVENT_LINK");

        //BIND DATA
        nameTxt.setText(name);
        descTxt.setText(desc);
        dateTxt.setText(propellant);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl(evenLink);
            }
        });
    }

    public void openUrl(String link) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);

    }


}
