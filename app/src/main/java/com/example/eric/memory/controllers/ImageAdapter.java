package com.example.eric.memory.controllers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.eric.memory.model.Partida;

/**
 * Created by Eric on 23/11/2016.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private Partida partida;

    public ImageAdapter(Context c, Partida p) {

        mContext = c;
        partida = p;

    }


    public int getCount() {
        return partida.getNumeroCartes();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(280,320));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setImageResource(partida.getLlistaCartes().get(position).getActive());
        } else {
            imageView = (ImageView) convertView;
        }

        return imageView;
    }

















}