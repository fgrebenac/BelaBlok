package com.fgrebenac.belablok;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addNewGameBtn;
    ListView mainListView;

    Realm realm;

    RealmList<Igra> sveIgre;
    SveIgreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        addNewGameBtn = (FloatingActionButton) findViewById(R.id.addNewGameBtn);
        mainListView = (ListView) findViewById(R.id.mainListView);

        addNewGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewGamePopup.class);
                startActivity(intent);
            }
        });

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Igra igra = (Igra) mainListView.getItemAtPosition(position);
                String uniqueID = igra.getUniqueID();

                Intent intent = new Intent(MainActivity.this, IgraActivity.class);
                intent.putExtra("idIgre", uniqueID);
                startActivity(intent);
            }
        });

        mainListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Igra igra = (Igra) mainListView.getItemAtPosition(position);
                String uniqueID = igra.getUniqueID();

                Intent intent = new Intent(MainActivity.this, DeleteIgraActivity.class);
                intent.putExtra("idIgre", uniqueID);
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        RealmHelper helper = new RealmHelper(realm);

        sveIgre = helper.sveIgre();

        adapter = new SveIgreAdapter(this, sveIgre);
        mainListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}
