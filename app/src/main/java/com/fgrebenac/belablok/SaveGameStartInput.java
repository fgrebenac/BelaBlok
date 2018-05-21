package com.fgrebenac.belablok;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

public class SaveGameStartInput extends AppCompatActivity {

    private EditText igrac1name;
    private EditText igrac2name;
    private EditText igrac3name;
    private EditText igrac4name;


    private Button startGameBtn;

    private Igra igra;
    private RealmList<Partija> partije;

    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_game_start_input);

        realm = Realm.getDefaultInstance();

        igrac1name = (EditText) findViewById(R.id.igrac1name);
        igrac2name = (EditText) findViewById(R.id.igrac2name);
        igrac3name = (EditText) findViewById(R.id.igrac3name);
        igrac4name = (EditText) findViewById(R.id.igrac4name);

        startGameBtn = (Button) findViewById(R.id.startGameBtn);


        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RealmHelper helper = new RealmHelper(realm);

                partije = new RealmList<>();
                igra = new Igra(partije, igrac1name.getText().toString(), igrac2name.getText().toString(),
                        igrac3name.getText().toString(), igrac4name.getText().toString());

                helper.novaIgra(igra);

                Intent intent = new Intent(SaveGameStartInput.this, IgraActivity.class);
                intent.putExtra("idIgre", igra.getUniqueID());
                startActivity(intent);
                finish();
            }
        });


        setFinishOnTouchOutside(true);
    }
}
