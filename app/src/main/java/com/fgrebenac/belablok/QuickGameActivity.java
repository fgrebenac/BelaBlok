package com.fgrebenac.belablok;

import android.app.Activity;
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
import android.widget.Toast;

import java.util.ArrayList;

public class QuickGameActivity extends AppCompatActivity {

    TextView brzaPartijaMi;
    TextView brzaPartijaVi;
    TextView faliNam;
    TextView faliVam;
    TextView razlika;

    TextView brojPobjedaMiTxt;
    TextView brojPobjedaViTxt;

    ListView brziZapisiListView;

    FloatingActionButton addBrziZapis;
    Button startNewQuickGame;

    QuickGameAdapter adapter;

    ArrayList<BrziZapis> zapisi;

    int ukupnaIgra;

    int brojPobjedaMi = 0;
    int brojPobjedaVi = 0;

    int tkoJePobijedio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_game_activity);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

        faliNam = (TextView) findViewById(R.id.faliNam);
        faliVam = (TextView) findViewById(R.id.faliVam);
        razlika = (TextView) findViewById(R.id.razlika);

        brojPobjedaMiTxt = (TextView) findViewById(R.id.brojPobjedaMi);
        brojPobjedaViTxt = (TextView) findViewById(R.id.brojPobjedaVi);

        brzaPartijaMi = (TextView) findViewById(R.id.miBrojBodovaPartije);
        brzaPartijaVi = (TextView) findViewById(R.id.viBrojBodovaPartije);

        brziZapisiListView = (ListView) findViewById(R.id.brziZapisiListView);

        addBrziZapis = (FloatingActionButton) findViewById(R.id.addBrziZapisBtn);
        startNewQuickGame = (Button) findViewById(R.id.startNewQuickGame);
        startNewQuickGame.setVisibility(View.GONE);

        ukupnaIgra = getIntent().getIntExtra("brojBodova", 1001);

        zapisi = new ArrayList<>();

        adapter = new QuickGameAdapter(this, zapisi);
        brziZapisiListView.setAdapter(adapter);

        addBrziZapis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuickGameActivity.this, QuickNewInput.class);
                startActivityForResult(intent, 123);
            }
        });

        brziZapisiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                BrziZapis zapis = (BrziZapis) brziZapisiListView.getItemAtPosition(position);

                Intent intent = new Intent(QuickGameActivity.this, QuickNewInput.class);
                intent.putExtra("miZvanja", zapis.getMiZvanja());
                intent.putExtra("viZvanja", zapis.getViZvanja());
                intent.putExtra("miIgra", zapis.getMiIgra());
                intent.putExtra("viIgra", zapis.getViIgra());
                intent.putExtra("position", position);
                intent.putExtra("tkoJeZvao", zapis.getTkoJeZvao());

                startActivityForResult(intent, 123);
            }
        });

        brziZapisiListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(QuickGameActivity.this, DeleteQuickZapisActivity.class);
                intent.putExtra("position", position);
                startActivityForResult(intent, 234);

                return true;
            }
        });

        startNewQuickGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(QuickGameActivity.this, StartNewQuickGame.class);
                startActivityForResult(intent, 345);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123) {

             if (resultCode == Activity.RESULT_OK) {

                BrziZapis zapis = new BrziZapis();

                zapis.setMiZvanja(data.getIntExtra("miZvanja", 0));
                zapis.setViZvanja(data.getIntExtra("viZvanja", 0));
                zapis.setMiIgra(data.getIntExtra("miIgra", 0));
                zapis.setViIgra(data.getIntExtra("viIgra", 0));
                zapis.setTkoJeZvao(data.getIntExtra("tkoJeZvao", 0));

                int position = data.getIntExtra("position", -1);

                if(position != -1){
                    zapisi.get(position).setMiZvanja(zapis.getMiZvanja());
                    zapisi.get(position).setViZvanja(zapis.getViZvanja());
                    zapisi.get(position).setMiIgra(zapis.getMiIgra());
                    zapisi.get(position).setViIgra(zapis.getViIgra());
                    zapisi.get(position).setTkoJeZvao(zapis.getTkoJeZvao());
                }else {
                    zapisi.add(zapis);
                }

                adapter.notifyDataSetChanged();

            }

        }

        if(requestCode == 234){

            if(resultCode == Activity.RESULT_OK){
                int position = data.getIntExtra("position", -1);
                boolean isTrue = data.getBooleanExtra("isTrue", false);

                if(isTrue) {
                    zapisi.remove(position);
                }
                adapter.notifyDataSetChanged();
            }

        }

        if(requestCode == 345){

            if(resultCode == Activity.RESULT_OK){

                if(tkoJePobijedio == 1){
                    brojPobjedaMi++;
                    brojPobjedaMiTxt.setText(String.valueOf(brojPobjedaMi));
                } else {
                    brojPobjedaVi++;
                    brojPobjedaViTxt.setText(String.valueOf(brojPobjedaVi));
                }


                ukupnaIgra = data.getIntExtra("brojBodova", 1001);
                zapisi.clear();
                adapter.notifyDataSetChanged();
                startNewQuickGame.setVisibility(View.GONE);
                addBrziZapis.show();
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(ukupnaIgra - sumOurPoints(zapisi) < 0){
            faliNam.setText("0");
        }else {
            faliNam.setText(String.valueOf(ukupnaIgra - sumOurPoints(zapisi)));
        }

        if(ukupnaIgra - sumYourPoints(zapisi) < 0){
            faliVam.setText("0");
        }else {
            faliVam.setText(String.valueOf(ukupnaIgra - sumYourPoints(zapisi)));
        }

        razlika.setText(String.valueOf(Math.abs(sumOurPoints(zapisi) - sumYourPoints(zapisi))));

        brzaPartijaMi.setText(String.valueOf(sumOurPoints(zapisi)));
        brzaPartijaVi.setText(String.valueOf(sumYourPoints(zapisi)));

        if(sumOurPoints(zapisi) >= ukupnaIgra && sumYourPoints(zapisi) < ukupnaIgra){
            addBrziZapis.hide();
            Toast.makeText(getApplicationContext(), "Mi smo pobijedili.", Toast.LENGTH_SHORT).show();
            startNewQuickGame.setVisibility(View.VISIBLE);
            tkoJePobijedio = 1;
        } else if(sumYourPoints(zapisi) >= ukupnaIgra && sumOurPoints(zapisi) < ukupnaIgra){
            addBrziZapis.hide();
            Toast.makeText(getApplicationContext(), "Vi ste pobijedili.", Toast.LENGTH_SHORT).show();
            startNewQuickGame.setVisibility(View.VISIBLE);
            tkoJePobijedio = 2;
        }

        if(sumOurPoints(zapisi) < ukupnaIgra && sumYourPoints(zapisi) < ukupnaIgra){
            addBrziZapis.show();
            startNewQuickGame.setVisibility(View.GONE);
        }
    }

    public int sumOurPoints(ArrayList<BrziZapis> zapisi){
        int suma = 0;
        try {
            for (BrziZapis z : zapisi) {
                suma += z.getMiBodovi();
            }
        }catch (NullPointerException e){
            return 0;
        }
        return suma;
    }

    public int sumYourPoints(ArrayList<BrziZapis> zapisi){
        int suma = 0;
        try {
            for (BrziZapis z : zapisi) {
                suma += z.getViBodovi();
            }
        }catch (NullPointerException e){
            return 0;
        }
        return suma;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
}
