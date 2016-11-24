package com.example.eric.memory.controllers;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;


import com.example.eric.memory.R;
import com.example.eric.memory.model.Partida;

public class Joc extends AppCompatActivity {

    private GridView gv;
    private Partida partida;
    private ImageAdapter adapter;
    private android.os.CountDownTimer CountDownTimer; //Para contar hacia atras
    private int tempsRestant;
    private final int segons = 1000; //Variable final que determina un segundo en millis
    private int tempsSegons = 60; //Tiempo que tendra la partida
    private final GeneralListener listener = new GeneralListener(this);
    private boolean timeOff=false; //Para determinaar cuando acaba el tiempo


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
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc);
        int cartes = getIntent().getIntExtra("dificultad", -33);
        //Intent intent = getIntent();
        gv = (GridView) findViewById(R.id.gridViewMemory);

        //TODO este 12 hay que calcularlo de alguna manera
        this.partida = new Partida(cartes);
        adapter = new ImageAdapter(this, partida);

        gv.setAdapter(adapter);
        gv.setOnItemClickListener(listener);
        contador(); //Llama al metodo contador para tener la cuenta atras
    }

    /**
     * Por cada jugada realizada actualiza el tablero.
     */
    public void refrescarTablero() {
        gv.setAdapter(adapter);
        gv.refreshDrawableState();
    }

    /**
     * Metodo que terminada la partida, en cas de guanyar termina o en cas de acabar tot el temps
     * En ambos casos treura un alertDialog preguntant si volen tornar a jugar o no.
     */
    public void acabarPartida(boolean timeOff) {

        if (timeOff) {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("Se acabo el tiempo!");
            ad.setMessage("¿ Quieres volver a probar ?");
            ad.setCancelable(false);
            ad.setPositiveButton("Volver al menu", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    aceptar();
                }
            });
            ad.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    cancelar();
                }
            });
            ad.show();
        }
    }

    /**
     * En caso de aceptar se hace un intent, y se lanza la actividad MainActivity donde esta el menu.
     * Aparte se finaliza esta activity.
     */
    public void aceptar() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * En caso de no querer volver a jugar se finaliza la activity y se sale de la app.
     */
    public void cancelar() {
        finish();
    }

    /**
     * Contado que hace la cuenta atras para hacer el reto de girar las cartas.
     */
    public void contador() {
        timeOff = false;
        new CountDownTimer(tempsSegons * 1000, segons) {

            public void onTick(long millisUntilFinished) {

                ((TextView) findViewById(R.id.contador)).setText("Tiempo Restante: " + (millisUntilFinished / segons));
            }

            public void onFinish() {
                 timeOff = true;
                ((TextView) findViewById(R.id.contador)).setText("SE ACABO!");
                acabarPartida(timeOff);
            }
        }.start();
    }
}