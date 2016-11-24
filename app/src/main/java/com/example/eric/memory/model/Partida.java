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
    //Array con todas las cartas
    private Integer[] totalCartes = {
            R.drawable.c0, R.drawable.c1,
            R.drawable.c2, R.drawable.c3,
            R.drawable.c4, R.drawable.c5,
            R.drawable.c6, R.drawable.c7,
            R.drawable.c8, R.drawable.c9,
            R.drawable.c10, R.drawable.c11
    };
    //Array con las cartas escogidas
    private ArrayList<Carta> llistaCartes;
    //Numero de cartas que tendra la partida
    private int numeroCartes;

    /**
     * Constructor vacio para sobrecarga
     */
    public Partida() {
    }

    /**
     * Constructor que le llega total de cartas, lista de las cartas y numero de cartas a jugar
     * @param totalCartes
     * @param llistaCartes
     * @param numeroCartes
     */
    public Partida(Integer[] totalCartes, ArrayList<Carta> llistaCartes, int numeroCartes) {
        this.totalCartes = totalCartes;
        this.llistaCartes = llistaCartes;
        this.numeroCartes = numeroCartes;
    }

    /**
     * Constructor que le llega la lista de cartas y el numero cartas a jugar
     * @param llistaCartes
     * @param numeroCartes
     */
    public Partida(ArrayList<Carta> llistaCartes, int numeroCartes) {
        this.llistaCartes = llistaCartes;
        this.numeroCartes = numeroCartes;
    }

    /**
     * Constructor que le llega el numero de cartas a jugar
     * @param numeroCartes
     */
    public Partida(int numeroCartes) {
        this.numeroCartes = numeroCartes;
        List<Integer> llistaTotal = Arrays.asList(totalCartes);
        llistaCartes = new ArrayList();
        //Bucle for para añadir 2 cartas iguales dentro del array (asi tenemos las parejas)
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

    /**
     * Metodo que crea un arrayList y añade toda carta que tenga su estado como FRONT a esta.
     * @return la lista de cartas como FRONT
     */
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