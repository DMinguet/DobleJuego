package com.example.gamesfragment;

import java.util.ArrayList;
import java.util.Random;

public class JuegoAhorcado {
    private final ArrayList<String> palabras;
    private final int NUM_MAX_INTENTOS = 6;
    private String palabraAleatoria;
    private int intentosRestantes;
    private ArrayList<Character> letrasGastadas;

    public JuegoAhorcado(ArrayList<String> palabras) {
        this.palabras = palabras;
        this.intentosRestantes = NUM_MAX_INTENTOS;
        this.letrasGastadas = new ArrayList<>();
    }

    //Devuelve  las posiciones donde puede coincidir la letra, en el caso de que no esté, devolverá null, en el caso de que la letra ya se haya indicado anteriormente, devolverá
    //un -1 en la primera posición del array
    public ArrayList<Integer> comprobarLetra(char letra) {
        ArrayList<Integer> posiciones = new ArrayList<>();

        if (comprobarLetraYaIntroducida(letra)) {
            posiciones.add(-1);
            return posiciones;
        } else {
            letrasGastadas.add(letra);

            for (int i = 0; i < palabraAleatoria.length(); i++) {
                if (palabraAleatoria.charAt(i) == letra) {
                    posiciones.add(i);
                }
            }

            if (posiciones.isEmpty()) {
                intentosRestantes--;
                return null;
            } else {
                return posiciones;
            }
        }
    }

    public boolean comprobarLetraYaIntroducida(char letra) {
        for(int i = 0; i < letrasGastadas.size(); i++) {
            if (letrasGastadas.get(i) == letra) {
                return true;
            }
        }

        return false;
    }

    public void inicializar() {
        Random rd = new Random();
        palabraAleatoria = palabras.get(rd.nextInt(palabras.size() + 1));
        intentosRestantes = NUM_MAX_INTENTOS;
        letrasGastadas.clear();
    }

    public String getPalabraAleatoria() {
        return palabraAleatoria;
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    public ArrayList<Character> getLetrasGastadas() {
        return letrasGastadas;
    }
}
