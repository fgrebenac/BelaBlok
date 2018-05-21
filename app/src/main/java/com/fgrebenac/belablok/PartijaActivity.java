package com.fgrebenac.belablok;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmList;

public class PartijaActivity extends AppCompatActivity {

    TextView faliNam;
    TextView faliVam;
    TextView razlika;

    TextView miBodovi;
    TextView viBodovi;

    FloatingActionButton noviZapisBtn;

    ListView zapisiListView;

    Realm realm;

    String id;
    Partija partija;

    RealmList<Zapis> sviZapisi;
    SviZapisiAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partija_activity);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

        realm = Realm.getDefaultInstance();

        faliNam = (TextView) findViewById(R.id.faliNam);
        faliVam = (TextView) findViewById(R.id.faliVam);
        razlika = (TextView) findViewById(R.id.razlika);

        miBodovi = (TextView) findViewById(R.id.miBrojBodovaPartije);
        viBodovi = (TextView) findViewById(R.id.viBrojBodovaPartije);

        noviZapisBtn = (FloatingActionButton) findViewById(R.id.noviZapisBtn);

        zapisiListView = (ListView) findViewById(R.id.zapisiListView);

        id = getIntent().getStringExtra("idPartije");
        partija = realm.where(Partija.class).equalTo("uniqueID", id).findFirst();

        miBodovi.setText(String.valueOf(partija.getMiBodovi()));
        viBodovi.setText(String.valueOf(partija.getViBodovi()));

        noviZapisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PartijaActivity.this, NewInput.class);
                intent.putExtra("idPartije", id);
                startActivity(intent);
            }
        });

        zapisiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Zapis zapis = (Zapis) zapisiListView.getItemAtPosition(position);
                String uniqueId = zapis.getUniqueID();

                Intent intent = new Intent(PartijaActivity.this, NewInput.class);
                intent.putExtra("idZapisa", uniqueId);
                intent.putExtra("idPartije", id);

                startActivity(intent);
            }
        });

        zapisiListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Zapis zapis = (Zapis) zapisiListView.getItemAtPosition(position);
                String uniqueId = zapis.getUniqueID();

                Intent intent = new Intent(PartijaActivity.this, DeleteZapisActivity.class);
                intent.putExtra("idZapisa", uniqueId);
                startActivity(intent);
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(partija.getIgraDo() - partija.getMiBodovi() < 0){
            faliNam.setText("0");
        }else {
            faliNam.setText(String.valueOf(partija.getIgraDo() - partija.getMiBodovi()));
        }

        if(partija.getIgraDo() - partija.getViBodovi() < 0){
            faliVam.setText("0");
        }else {
            faliVam.setText(String.valueOf(partija.getIgraDo() - partija.getViBodovi()));
        }

        razlika.setText(String.valueOf(Math.abs(partija.getMiBodovi() - partija.getViBodovi())));

        RealmHelper helper = new RealmHelper(realm);

        sviZapisi = helper.sviZapisi(partija);

        adapter = new SviZapisiAdapter(this, sviZapisi);
        zapisiListView.setAdapter(adapter);

        miBodovi.setText(String.valueOf(partija.getMiBodovi()));
        viBodovi.setText(String.valueOf(partija.getViBodovi()));

        if(partija.getMiBodovi() >= partija.getIgraDo() && partija.getViBodovi() < partija.getMiBodovi()){
            helper.setGotovaPartija(partija, true);
            noviZapisBtn.hide();
            Toast.makeText(getApplicationContext(), "Mi smo pobijedili.", Toast.LENGTH_SHORT).show();
        }else if (partija.getViBodovi() >= partija.getIgraDo() && partija.getMiBodovi() < partija.getViBodovi()){
            helper.setGotovaPartija(partija, true);
            noviZapisBtn.hide();
            Toast.makeText(getApplicationContext(), "Vi ste pobijedili.", Toast.LENGTH_SHORT).show();
        }else {
            helper.setGotovaPartija(partija, false);
        }

        if(partija.getMiBodovi() < partija.getIgraDo() && partija.getViBodovi() < partija.getIgraDo()){
            noviZapisBtn.show();
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
}
