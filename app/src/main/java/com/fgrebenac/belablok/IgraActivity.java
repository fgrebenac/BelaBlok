package com.fgrebenac.belablok;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmList;

public class IgraActivity extends AppCompatActivity {

    TextView playerName1;
    TextView playerName2;
    TextView playerName3;
    TextView playerName4;

    TextView rezMi;
    TextView rezVi;

    FloatingActionButton novaPartija;

    ListView partijeListView;

    String id;

    Igra igra;

    Realm realm;

    RealmList<Partija> svePartije;
    SvePartijeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.igra_activity);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

        realm = Realm.getDefaultInstance();

        playerName1 = (TextView) findViewById(R.id.player1);
        playerName2 = (TextView) findViewById(R.id.player2);
        playerName3 = (TextView) findViewById(R.id.player3);
        playerName4 = (TextView) findViewById(R.id.player4);

        rezMi = (TextView) findViewById(R.id.rezMi);
        rezVi = (TextView) findViewById(R.id.rezVi);

        novaPartija = (FloatingActionButton) findViewById(R.id.novaPartijaBtn);

        partijeListView = (ListView) findViewById(R.id.partijeListView);

        id = getIntent().getStringExtra("idIgre");

        igra = realm.where(Igra.class).equalTo("uniqueID", id).findFirst();


        playerName1.setText(igra.getIgrac1());
        playerName2.setText(igra.getIgrac2());
        playerName3.setText(igra.getIgrac3());
        playerName4.setText(igra.getIgrac4());

        rezMi.setText(String.valueOf(igra.getMiBrojPobjeda()));
        rezVi.setText(String.valueOf(igra.getViBrojPobjeda()));

        novaPartija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IgraActivity.this, AddNewPartija.class);
                intent.putExtra("idIgre", id);
                startActivity(intent);
            }
        });

        partijeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Partija partija = (Partija) partijeListView.getItemAtPosition(position);
                String uniqueID = partija.getUniqueID();

                Intent intent = new Intent(IgraActivity.this, PartijaActivity.class);
                intent.putExtra("idPartije", uniqueID);
                startActivity(intent);
            }
        });

        partijeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Partija partija = (Partija) partijeListView.getItemAtPosition(position);
                String uniqueId = partija.getUniqueID();

                Intent intent = new Intent(IgraActivity.this, DeletePartijaActivity.class);
                intent.putExtra("idPartije", uniqueId);
                startActivity(intent);
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        RealmHelper helper = new RealmHelper(realm);

        svePartije = helper.svePartije(igra);

        adapter = new SvePartijeAdapter(this, svePartije);
        partijeListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        rezMi.setText(String.valueOf(helper.getBrojPobjedaMi(igra)));
        rezVi.setText(String.valueOf(helper.getBrojPobjedaVi(igra)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
}
