package com.fgrebenac.belablok;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;

public class DeleteZapisActivity extends AppCompatActivity {

    Button deleteBtn;
    Button cancelButton;

    Zapis zapis;
    String id;

    Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_activity);

        realm = Realm.getDefaultInstance();

        id = getIntent().getStringExtra("idZapisa");

        zapis = realm.where(Zapis.class).equalTo("uniqueID", id).findFirst();

        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        cancelButton = (Button) findViewById(R.id.cancelBtn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealmHelper helper = new RealmHelper(realm);
                helper.deleteZapis(zapis);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setFinishOnTouchOutside(true);
    }

}
