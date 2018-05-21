package com.fgrebenac.belablok;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class QuickPartijaPoints extends AppCompatActivity {

    Spinner brojBodova;
    Button startQuickGame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_game_size);

        brojBodova = (Spinner) findViewById(R.id.quickPartijaPoints);
        startQuickGame = (Button) findViewById(R.id.startQuickPartijaBtn);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.bodovi_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brojBodova.setAdapter(spinnerAdapter);

        startQuickGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuickPartijaPoints.this, QuickGameActivity.class);
                intent.putExtra("brojBodova", Integer.parseInt(brojBodova.getSelectedItem().toString()));
                startActivity(intent);
            }
        });

        setFinishOnTouchOutside(true);
    }
}
