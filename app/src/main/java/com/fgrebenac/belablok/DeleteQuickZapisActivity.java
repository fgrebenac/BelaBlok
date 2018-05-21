package com.fgrebenac.belablok;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DeleteQuickZapisActivity extends AppCompatActivity {

    Button deleteQuickZapisBtn;
    Button cancelQuickZapisBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_activity);

        deleteQuickZapisBtn = (Button) findViewById(R.id.deleteBtn);
        cancelQuickZapisBtn = (Button) findViewById(R.id.cancelBtn);

        final int position = getIntent().getIntExtra("position", -1);

        deleteQuickZapisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("isTrue", true);
                intent.putExtra("position", position);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        cancelQuickZapisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setFinishOnTouchOutside(true);
    }
}
