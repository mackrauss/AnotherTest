package com.example.anothertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.anothertest.R.id.option_text;

/**
 * Created by armin on 13-06-06.
 */
public class ListModelAdapter extends ArrayAdapter<ListModel> {
    private LayoutInflater mInflater;
    private ArrayList<ListModel> objects;

    public ListModelAdapter(Context context, int layoutResourceId, ArrayList<ListModel> objects) {
        super(context, layoutResourceId, objects);

        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objects = objects;
    }

    /*
      * we are overriding the getView method here - this is what defines how each
      * list item will look.
      */
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = this.mInflater.inflate(R.layout.list_row, null);

        TextView tv = null;
        if (convertView != null) {
            tv = (TextView)convertView.findViewById(option_text);
            tv.setText(objects.get(position).getUserInput());
        }


        return convertView;
    }


}
