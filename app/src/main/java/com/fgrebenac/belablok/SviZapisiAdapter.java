package com.fgrebenac.belablok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmList;

public class SviZapisiAdapter extends ArrayAdapter<Zapis> {

    public SviZapisiAdapter(Context context, RealmList<Zapis> sviZapisi){
        super(context, 0, sviZapisi);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Zapis zapis = getItem(position);

        Realm realm = Realm.getDefaultInstance();

        RealmHelper helper = new RealmHelper(realm);

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
