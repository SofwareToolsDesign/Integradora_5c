package com.stdesign.bitacorasutd.spinners_adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stdesign.bitacorasutd.R;

/**
 * Created by Hurón Padilla on 4/11/2018.
 */

public class CustomAdapter extends ArrayAdapter<String>{
    Context c;
    String [] names;
    int [] images;

    public CustomAdapter(Context context, String [] names, int [] images) {
        super(context, R.layout.spinner_item, names);
        this.c = context;
        this.names = names;
        this.images = images;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_item,null);
        TextView tv1 = row.findViewById(R.id.tvmexicolang);
        ImageView iv1 = row.findViewById(R.id.ivmexicofl);

        tv1.setText(names[position]);
        iv1.setImageResource(images[position]);

        return row;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_item,null);
        TextView tv1 = row.findViewById(R.id.tvmexicolang);
        ImageView iv1 = row.findViewById(R.id.ivmexicofl);

        tv1.setText(names[position]);
        iv1.setImageResource(images[position]);

        return row;
    }
}
