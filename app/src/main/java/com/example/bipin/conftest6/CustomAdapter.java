package com.example.bipin.conftest6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<EventsModel> myevents;

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
}














