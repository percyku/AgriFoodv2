package com.agrifood.percyku.agrifood;

import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by percyku on 16/11/1.
 */
public class PostInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {


//    Fragment fragment;


    Context context;
    String name;

    int arg;




    public PostInfoWindowAdapter(Context context,String name,int arg){
        this.context=context;
        this.name=name;
        this.arg=arg;

    }



    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.info_window_layout, null);

        TextView tvName= (TextView) v.findViewById(R.id.tv_name);
        tvName.setText(name);
        Button bt=(Button)v.findViewById(R.id.button);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });



        return v;
    }
}
