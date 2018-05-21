package com.fgrebenac.belablok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.realm.RealmList;

public class SvePartijeAdapter extends ArrayAdapter<Partija> {

    public SvePartijeAdapter(Context context, RealmList<Partija> svePartije){
        super(context, 0, svePartije);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Partija partija = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.partije_listview_row, parent, false);
        }

        TextView miBodoviPartije = (TextView) convertView.findViewById(R.id.miBodoviPartije);
        TextView viBodoviPartije = (TextView) convertView.findViewById(R.id.viBodoviPartije);

        miBodoviPartije.setText(String.valueOf(partija.getMiBodovi()));
        viBodoviPartije.setText(String.valueOf(partija.getViBodovi()));

        return convertView;
    }
}
