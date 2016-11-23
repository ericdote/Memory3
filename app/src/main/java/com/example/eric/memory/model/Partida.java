package com.example.eric.memory.model;

import com.example.eric.memory.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Eric on 23/11/2016.
 */

public class Partida {

    private Integer[] totalCartes = {
            R.drawable.c0, R.drawable.c1,
            R.drawable.c2, R.drawable.c3,
            R.drawable.c4, R.drawable.c5,
            R.drawable.c6, R.drawable.c7,
            R.drawable.c8, R.drawable.c9,
            R.drawable.c10, R.drawable.c11
    };
    private ArrayList<Carta> llistaCartes;
    private int numeroCartes;

    public Partida() {
    }

    public Partida(Integer[] totalCartes, ArrayList<Carta> llistaCartes, int numeroCartes) {
        this.totalCartes = totalCartes;
        this.llistaCartes = llistaCartes;
        this.numeroCartes = numeroCartes;
    }

    public Partida(ArrayList<Carta> llistaCartes, int numeroCartes) {
        this.llistaCartes = llistaCartes;
        this.numeroCartes = numeroCartes;
    }

    public Partida(int numeroCartes) {
        this.numeroCartes = numeroCartes;
        List<Integer> llistaTotal = Arrays.asList(totalCartes);
        llistaCartes = new ArrayList();

        for (int i = 0; i < numeroCartes; i++) {
            llistaCartes.add(new Carta(llistaTotal.get(i / 2)));
        }

        Collections.shuffle(llistaCartes);

    }


    public ArrayList<Carta> getLlistaCartes() {
        return llistaCartes;
    }

    public int getNumeroCartes() {
        return numeroCartes;
    }

    public ArrayList<Carta> mostrarCartasFront() {
        ArrayList<Carta> listaCartasFront = new ArrayList();
        for (Carta carta : getLlistaCartes()) {
            if (carta.getEstat() == Carta.Estat.FRONT) {
                listaCartasFront.add(carta);
            }
        }
        return listaCartasFront;
    }

}