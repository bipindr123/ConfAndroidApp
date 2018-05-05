package com.example.bipin.conftest6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<EventsModel> spacecrafts;

    public CustomAdapter(Context c, ArrayList<EventsModel> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int position) {
        return spacecrafts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        }

        TextView nameTxt= (TextView) convertView.findViewById(R.id.nameTxt);
        TextView propTxt= (TextView) convertView.findViewById(R.id.propellantTxt);
        TextView descTxt= (TextView) convertView.findViewById(R.id.descTxt);

        final EventsModel s= (EventsModel) this.getItem(position);

        nameTxt.setText(s.getName());
        propTxt.setText(s.getEventLink());
        descTxt.setText(s.getEventDesc());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DETAIL
                openDetailActivity(s.getName(),s.getEventDesc(),s.getEventLink());
            }
        });

        return convertView;
    }

    //OPEN DETAIL ACTIVITY
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c,DetailActivity.class);

        i.putExtra("NAME_KEY",details[0]);
        i.putExtra("DESC_KEY",details[1]);
        i.putExtra("PROP_KEY",details[2]);

        c.startActivity(i);
    }
}














