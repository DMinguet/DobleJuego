package com.example.gamesfragment;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ParserPalabras {
    private ArrayList<String> palabras;
    private final InputStream palabrasArchivo;

    public ParserPalabras(Context context) {
        this.palabrasArchivo = context.getResources().openRawResource(R.raw.words_es);
    }

    public boolean parse() {
        boolean parsed = false;
        palabras = new ArrayList<>();
        BufferedReader bufInput = new BufferedReader(new InputStreamReader(palabrasArchivo));

        String palabra;
        try {
            while ((palabra = bufInput.readLine()) != null) {
                palabras.add(palabra);
            }

            parsed = true;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return parsed;
    }

    public ArrayList<String> getPalabras() {
        return this.palabras;
    }
}
