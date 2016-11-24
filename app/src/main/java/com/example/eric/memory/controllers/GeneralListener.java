package com.example.eric.memory.controllers;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.eric.memory.model.Carta;
import com.example.eric.memory.model.Partida;

import java.util.ArrayList;

/**
 * Created by Eric on 23/11/2016.
 */

public class GeneralListener implements AdapterView.OnItemClickListener, Runnable {

    private Joc tauler;
    private Carta carta;
    private boolean listenerActive = true;
    private ArrayList<Carta> listaCartasFront;
    private boolean comprovar;

    public GeneralListener(Joc tauler) {
        this.tauler = tauler;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Partida partida = tauler.getPartida();

        if (listenerActive) {
            carta = tauler.getPartida().getLlistaCartes().get(position);
            carta.girar();
            tauler.refrescarTablero();
            listaCartasFront = partida.mostrarCartasFront();
            //Mira ambas cartas y en caso de no ser iguales hace un delay de 2 segundos para poder ver la segunda
            if (listaCartasFront.size() == 2 && listaCartasFront.get(0).getFrontImage() != listaCartasFront.get(1).getFrontImage()) {
                this.listenerActive = false;
                Handler delay = new Handler();
                delay.postDelayed(this, 2000);
            //Si ambas cartas son iguales las pone como FIXED
            } else if (listaCartasFront.size() == 2) {
                listaCartasFront.get(0).setEstat(Carta.Estat.FIXED);
                listaCartasFront.get(1).setEstat(Carta.Estat.FIXED);
                if(comprobarFin()){
                    tauler.acabarPartida(comprovar);
                }
            }
        }
    }

    @Override
    public void run() {
        listaCartasFront.get(0).girar();
        listaCartasFront.get(1).girar();
        tauler.refrescarTablero();
        listenerActive = true;
    }


   public boolean comprobarFin() {
        comprovar = true;
        for (Carta carta : listaCartasFront) {
            if (carta.getEstat() != Carta.Estat.FIXED) {
                Toast.makeText(tauler, "MM", Toast.LENGTH_SHORT).show();
                comprovar = false;
                break;
            } else {
                Toast.makeText(tauler, "BB", Toast.LENGTH_SHORT).show();
                comprovar = true;
            }
        }
        return comprovar;
    }

}
