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

public class SveIgreAdapter extends ArrayAdapter<Igra> {

    public SveIgreAdapter(Context context, RealmList<Igra> sveIgre){
        super(context, 0, sveIgre);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Igra igra = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.main_listview_row, parent, false);
        }

        Realm realm = Realm.getDefaultInstance();

        RealmHelper helper = new RealmHelper(realm);

        TextView igrac1 = (TextView) convertView.findViewById(R.id.player1);
        TextView igrac2 = (TextView) convertView.findViewById(R.id.player2);
        TextView igrac3 = (TextView) convertView.findViewById(R.id.player3);
        TextView igrac4 = (TextView) convertView.findViewById(R.id.player4);
        TextView miPobjede = (TextView) convertView.findViewById(R.id.miPobjede);
        TextView viPobjede = (TextView) convertView.findViewById(R.id.viPobjede);

        igrac1.setText(igra.getIgrac1());
        igrac2.setText(igra.getIgrac2());
        igrac3.setText(igra.getIgrac3());
        igrac4.setText(igra.getIgrac4());

        miPobjede.setText(String.valueOf(helper.getBrojPobjedaMi(igra)));
        viPobjede.setText(String.valueOf(helper.getBrojPobjedaVi(igra)));

        return convertView;
    }

}
