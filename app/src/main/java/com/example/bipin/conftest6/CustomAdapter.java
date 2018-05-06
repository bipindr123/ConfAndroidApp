package com.example.bipin.conftest6;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<EventsModel> myevents;
    DatabaseReference db;
    FirebaseHelper helper;


    public CustomAdapter(Context c, ArrayList<EventsModel> myevents) {
        this.c = c;
        this.myevents = myevents;
    }

    @Override
    public int getCount() {
        return myevents.size();
    }

    @Override
    public Object getItem(int position) {
        return myevents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.model, parent, false);
        }

        TextView nameTxt = (TextView) convertView.findViewById(R.id.nameTxt);
        TextView dateTxt = (TextView) convertView.findViewById(R.id.dateTxt);
        TextView descTxt = (TextView) convertView.findViewById(R.id.descTxt);

        final EventsModel s = (EventsModel) this.getItem(position);

        String shortDesc = s.getEventDesc();

        if (shortDesc.length() > 20)
            shortDesc = shortDesc.substring(0, 20);

        nameTxt.setText(s.getName());
        dateTxt.setText(s.getEventDate());
        descTxt.setText(shortDesc + "...");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DETAIL
                openDetailActivity(s.getName(), s.getEventDesc(), s.getEventDate(), s.getEventLink());
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                showDialog(s.getName());
                return false;
            }
        });

        return convertView;
    }

    //OPEN DETAIL ACTIVITY
    private void openDetailActivity(String... details) {
        Intent i = new Intent(c, DetailActivity.class);

        i.putExtra("NAME_KEY", details[0]);
        i.putExtra("DESC_KEY", details[1]);
        i.putExtra("DATE_KEY", details[2]);
        i.putExtra("EVENT_LINK", details[3]);
        c.startActivity(i);
    }

    public void showDialog(final String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Confirm Action");
        builder.setMessage("You are about to delete the event record. Do you really want to proceed ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                db = FirebaseDatabase.getInstance().getReference();
                helper = new FirebaseHelper(db);
                helper.remove(name);
                Toast.makeText(c, "deleted record", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(c, "You've changed your mind to delete the event record", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

}














