package com.fgrebenac.belablok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

public class QuickGameAdapter extends ArrayAdapter<BrziZapis> {

    ArrayList<BrziZapis> sviZapisi;

    public QuickGameAdapter(Context context, ArrayList<BrziZapis> sviZapisi){
        super(context, 0, sviZapisi);
        this.sviZapisi = sviZapisi;
    }

    public int getCount(){
        return sviZapisi.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BrziZapis zapis = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.zapisi_listview_row, parent, false);
        }

        TextView miZapis = (TextView) convertView.findViewById(R.id.miZapis);
        TextView viZapis = (TextView) convertView.findViewById(R.id.viZapis);

        miZapis.setText(String.valueOf(zapis.getMiBodovi()));
        viZapis.setText(String.valueOf(zapis.getViBodovi()));

        return convertView;
    }
}

