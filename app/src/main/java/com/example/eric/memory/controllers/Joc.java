package com.example.eric.memory.controllers;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eric.memory.R;
import com.example.eric.memory.model.Partida;

public class Joc extends AppCompatActivity {

    private GridView gv;
    private Partida partida;
    private ImageAdapter adapter;
    private android.os.CountDownTimer CountDownTimer;
    private final int segons = 1000;
    private int tempsSegons = 60;


    public GridView getGv() {
        return gv;
    }

    public void setGv(GridView gv) {
        this.gv = gv;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc);
        //Intent intent = getIntent();
        gv = (GridView) findViewById(R.id.gridViewMemory);

        //TODO este 12 hay que calcularlo de alguna manera
        this.partida = new Partida(12);
        adapter = new ImageAdapter(this, partida);
        GeneralListener listener = new GeneralListener(this);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(listener);
        if(listener.comprobarFin()){
            acabarPartida();
        }
        contador();




        /*gv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(TaulerActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    public void refrescarTablero() {
        gv.setAdapter(adapter);
        gv.refreshDrawableState();
    }


    public void acabarPartida(){
        Toast.makeText(this, "Muere", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void contador(){
        new CountDownTimer(tempsSegons*1000, segons) {

            public void onTick(long millisUntilFinished) {

                ((TextView) findViewById(R.id.contador)).setText("seconds remaining: " + millisUntilFinished / segons);
            }

            public void onFinish() {
                ((TextView) findViewById(R.id.contador)).setText("done!");
            }
        }.start();
    }
}