package com.fgrebenac.belablok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NewGamePopup extends AppCompatActivity {

    private Button saveGame;
    private Button quickGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game_popup);

        saveGame = (Button) findViewById(R.id.saveGameBtn);
        quickGame = (Button) findViewById(R.id.quickGameBtn);

        saveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewGamePopup.this, SaveGameStartInput.class);
                startActivity(intent);
                finish();
            }
        });

        quickGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewGamePopup.this, QuickPartijaPoints.class);
                startActivity(intent);
                finish();
            }
        });

        setFinishOnTouchOutside(true);
    }
}
