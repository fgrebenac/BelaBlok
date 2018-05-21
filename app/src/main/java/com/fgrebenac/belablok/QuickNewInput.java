package com.fgrebenac.belablok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import io.realm.Realm;

public class QuickNewInput extends AppCompatActivity {

    EditText ourPoints;
    EditText yourPoints;
    EditText ourCalls;
    EditText yourCalls;
    ToggleButton zvaliSmo;
    ToggleButton zvaliSu;
    Button upisiBodove;
    Button clearAll;
    int nasiBodovi;
    int vasiBodovi;
    int nasaZvanja;
    int vasaZvanja;

    private static final int IGRA = 162;

    BrziZapis zapis;
    BrziZapis updateaniZapis;

    int position = -1;
    int tkoJeZvao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        ourPoints = findViewById(R.id.ourPoints);
        yourPoints = findViewById(R.id.yourPoints);
        ourCalls = findViewById(R.id.ourCalls);
        yourCalls = findViewById(R.id.yourCalls);

        zvaliSmo = findViewById(R.id.zvaliSmo);
        zvaliSu = findViewById(R.id.zvaliSu);
        clearAll = findViewById(R.id.clearAll);
        upisiBodove = findViewById(R.id.upisiBodove);

        updateaniZapis = new BrziZapis();


        position = getIntent().getIntExtra("position", -1);
        updateaniZapis.setMiZvanja(getIntent().getIntExtra("miZvanja", 0));
        updateaniZapis.setViZvanja(getIntent().getIntExtra("viZvanja", 0));
        updateaniZapis.setMiIgra(getIntent().getIntExtra("miIgra", 0));
        updateaniZapis.setViIgra(getIntent().getIntExtra("viIgra", 0));
        updateaniZapis.setTkoJeZvao(getIntent().getIntExtra("tkoJeZvao", 0));

        if(updateaniZapis.getTkoJeZvao() == 1){
            zvaliSmo.setChecked(true);
            zvaliSu.setChecked(false);
            zvaliSmo.setAlpha(1.0f);
            zvaliSu.setAlpha(0.2f);
        } else if(updateaniZapis.getTkoJeZvao() == 2){
            zvaliSu.setChecked(true);
            zvaliSmo.setChecked(false);
            zvaliSu.setAlpha(1.0f);
            zvaliSmo.setAlpha(0.2f);
        }

        ourCalls.setText(String.valueOf(updateaniZapis.getMiZvanja()));
        yourCalls.setText(String.valueOf(updateaniZapis.getViZvanja()));
        ourPoints.setText(String.valueOf(updateaniZapis.getMiIgra()));
        yourPoints.setText(String.valueOf(updateaniZapis.getViIgra()));

        if(position == -1){
            ourPoints.getText().clear();
            ourCalls.getText().clear();
            yourPoints.getText().clear();
            yourCalls.getText().clear();
        }


        final TextWatcher ourPointsWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(ourPoints.hasFocus()) {

                    if (ourPoints.getText().toString().equals(null) || ourPoints.getText().toString().equals("")) {
                        ourPoints.getText().clear();
                        yourPoints.getText().clear();
                    }
                    if (!(ourPoints.getText().toString().equals(null) || ourPoints.getText().toString().equals(""))) {
                        if (Integer.parseInt(ourPoints.getText().toString()) > IGRA) {
                            yourPoints.setText("0");
                        } else {
                            yourPoints.setText(String.valueOf(IGRA - Integer.valueOf(ourPoints.getText().toString())));
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!ourPoints.getText().toString().equals("")){
                    nasiBodovi = Integer.parseInt(ourPoints.getText().toString());
                } else {
                    nasiBodovi = 0;
                }
            }
        };

        TextWatcher yourPointsWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(yourPoints.hasFocus()) {

                    if (yourPoints.getText().toString().equals(null) || yourPoints.getText().toString().equals("")) {
                        ourPoints.getText().clear();
                        yourPoints.getText().clear();
                    }
                    if (!(yourPoints.getText().toString().equals(null) || yourPoints.getText().toString().equals(""))) {
                        if (Integer.parseInt(yourPoints.getText().toString()) > IGRA) {
                            ourPoints.setText("0");
                        } else {
                            ourPoints.setText(String.valueOf(IGRA - Integer.valueOf(yourPoints.getText().toString())));
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!yourPoints.getText().toString().equals("")){
                    vasiBodovi = Integer.parseInt(yourPoints.getText().toString());
                } else {
                    vasiBodovi = 0;
                }

            }
        };

        TextWatcher ourCallsWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(ourCalls.hasFocus()) {

                    if (ourCalls.getText().toString().equals(null) || ourCalls.getText().toString().equals("")) {
                        ourCalls.getText().clear();
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!ourCalls.getText().toString().equals("")){
                    nasaZvanja = Integer.parseInt(ourCalls.getText().toString());
                } else {
                    nasaZvanja = 0;
                }

            }
        };

        TextWatcher yourCallsWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(yourCalls.hasFocus()) {

                    if (yourCalls.getText().toString().equals(null) || yourCalls.getText().toString().equals("")) {
                        yourCalls.getText().clear();
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!yourCalls.getText().toString().equals("")){
                    vasaZvanja = Integer.parseInt(yourCalls.getText().toString());
                } else {
                    vasaZvanja = 0;
                }

            }
        };

        zvaliSmo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(zvaliSmo.isChecked()){
                    zvaliSu.setChecked(false);
                    tkoJeZvao = 1;
                    zvaliSu.setAlpha(0.2f);
                    zvaliSmo.setAlpha(1.0f);
                } else if(!zvaliSmo.isChecked()){
                    zvaliSu.setChecked(true);
                    zvaliSu.setAlpha(1.0f);
                    zvaliSmo.setAlpha(0.2f);
                    tkoJeZvao = 2;
                }
            }
        });

        zvaliSu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(zvaliSu.isChecked()){
                    zvaliSmo.setChecked(false);
                    tkoJeZvao = 2;
                    zvaliSmo.setAlpha(0.2f);
                    zvaliSu.setAlpha(1.0f);
                } else if(!zvaliSu.isChecked()){
                    zvaliSmo.setChecked(true);
                    zvaliSmo.setAlpha(1.0f);
                    zvaliSu.setAlpha(0.2f);
                    tkoJeZvao = 1;
                }
            }
        });

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ourPoints.getText().clear();
                yourPoints.getText().clear();
                ourCalls.getText().clear();
                yourCalls.getText().clear();
            }
        });

        upisiBodove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    nasaZvanja = Integer.parseInt(ourCalls.getText().toString());
                }catch (NumberFormatException e){
                    nasaZvanja = 0;
                }

                try{
                    vasaZvanja = Integer.parseInt(yourCalls.getText().toString());
                }catch (NumberFormatException e){
                    vasaZvanja = 0;
                }
                try{
                    nasiBodovi = Integer.parseInt(ourPoints.getText().toString());
                }catch (NumberFormatException e){
                    nasiBodovi = 0;
                }
                try{
                    vasiBodovi = Integer.parseInt(yourPoints.getText().toString());
                }catch (NumberFormatException e){
                    vasiBodovi = 0;
                }

                if(position != -1){

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("miZvanja", nasaZvanja);
                    resultIntent.putExtra("viZvanja", vasaZvanja);
                    resultIntent.putExtra("miIgra", nasiBodovi);
                    resultIntent.putExtra("viIgra", vasiBodovi);
                    resultIntent.putExtra("position", position);
                    resultIntent.putExtra("tkoJeZvao", tkoJeZvao);
                    setResult(RESULT_OK, resultIntent);

                }else {

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("miZvanja", nasaZvanja);
                    resultIntent.putExtra("viZvanja", vasaZvanja);
                    resultIntent.putExtra("miIgra", nasiBodovi);
                    resultIntent.putExtra("viIgra", vasiBodovi);
                    resultIntent.putExtra("tkoJeZvao", tkoJeZvao);
                    setResult(RESULT_OK, resultIntent);

                }

                finish();
            }
        });

        ourPoints.addTextChangedListener(ourPointsWatcher);
        yourPoints.addTextChangedListener(yourPointsWatcher);
        ourCalls.addTextChangedListener(ourCallsWatcher);
        yourCalls.addTextChangedListener(yourCallsWatcher);


        setFinishOnTouchOutside(true);
    }
}

