package com.fgrebenac.belablok;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import io.realm.Realm;
import io.realm.RealmList;

public class AddNewPartija extends AppCompatActivity {

    Spinner bodoviSpinner;
    Button startBtn;

    Realm realm;

    RealmList<Zapis> zapisi;
    Partija partija;
    Igra igra;
    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_partija);

        realm = Realm.getDefaultInstance();

        startBtn = (Button) findViewById(R.id.startBtn);

        id = getIntent().getStringExtra("idIgre");

        igra = realm.where(Igra.class).equalTo("uniqueID", id).findFirst();


        bodoviSpinner = (Spinner) findViewById(R.id.bodoviSpinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                                     R.array.bodovi_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bodoviSpinner.setAdapter(spinnerAdapter);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RealmHelper helper = new RealmHelper(realm);

                zapisi = new RealmList<>();
                partija = new Partija(zapisi, Integer.parseInt(bodoviSpinner.getSelectedItem().toString()));

                helper.novaPartija(igra, partija);

                Intent intent = new Intent(AddNewPartija.this, PartijaActivity.class);
                intent.putExtra("idPartije", partija.getUniqueID());
                startActivity(intent);

                finish();
            }
        });

        setFinishOnTouchOutside(true);
    }
}
