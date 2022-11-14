package com.example.gamesfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentAhorcado extends Fragment {
    private final String LETRAS = "qwertyuiopasdfghjklñzxcvbnm";
    private ArrayList<String> palabras;
    private String palabraAleatoria;
    private ArrayList<Character> textoMostrado;
    private boolean juegoTerminado = false;
    private JuegoAhorcado juegoAhorcado;
    private ImageView ivImagenRepresentacion;
    private TextView tvIntentosRestantes;
    private TextView tvPalabra;
    private TextView tvLetrasGastadas;
    private EditText etLetraComprobar;
    private Button btnJugar;
    private TextView tvAdvertencias;
    private Button btnJugarDeNuevo;

    public interface IOnAttachListener {
        ArrayList<String> getPalabras();
    }

    public FragmentAhorcado() {
        super(R.layout.fragment_ahorcado);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        IOnAttachListener listener = (IOnAttachListener) context;
        palabras = listener.getPalabras();
        textoMostrado = new ArrayList<>();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        juegoAhorcado = new JuegoAhorcado(palabras);
        juegoAhorcado.inicializar();

        ivImagenRepresentacion = view.findViewById(R.id.ivImagen);
        tvIntentosRestantes = view.findViewById(R.id.tvIntentosRestantes);
        tvPalabra = view.findViewById(R.id.tvPalabra);
        tvLetrasGastadas = view.findViewById(R.id.tvLetrasGastadas);
        etLetraComprobar = view.findViewById(R.id.etLetraComprobar);
        btnJugar = view.findViewById(R.id.btnJugar);
        tvAdvertencias = view.findViewById(R.id.tvAdvertencias);
        btnJugarDeNuevo = view.findViewById(R.id.btnJugarDeNuevo);

        palabraAleatoria = juegoAhorcado.getPalabraAleatoria();

        inicializar();

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (juegoAhorcado.getIntentosRestantes() == 0) {
                    tvAdvertencias.setText("Intentos agotados");
                    btnJugarDeNuevo.setVisibility(View.VISIBLE);
                } else if (juegoTerminado == true) {
                    tvAdvertencias.setText("Has acertado la palabra, enhorabuena!!");
                    btnJugarDeNuevo.setVisibility(View.VISIBLE);
                } else {
                    jugar();
                }
            }
        });

        btnJugarDeNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                juegoTerminado = false;
                juegoAhorcado.inicializar();
                juegoNuevo();
                btnJugarDeNuevo.setVisibility(View.GONE);
            }
        });
    }

    public void jugar() {
        tvAdvertencias.setText("");
        String textoIntroducido = etLetraComprobar.getText().toString();

        textoIntroducido.toLowerCase();
        char comprobarLetra = textoIntroducido.charAt(0);

        if (comprobarLetra(comprobarLetra)) {
            if (juegoAhorcado.comprobarLetra(comprobarLetra) == null) {
                tvLetrasGastadas.setText(juegoAhorcado.getLetrasGastadas().toString());
                tvIntentosRestantes.setText(String.valueOf(juegoAhorcado.getIntentosRestantes()));
                switch (juegoAhorcado.getIntentosRestantes()) {
                    case 0:
                        ivImagenRepresentacion.setImageResource(R.drawable.hangman_0);
                        break;
                    case 1:
                        ivImagenRepresentacion.setImageResource(R.drawable.hangman_1);
                        break;
                    case 2:
                        ivImagenRepresentacion.setImageResource(R.drawable.hangman_2);
                        break;
                    case 3:
                        ivImagenRepresentacion.setImageResource(R.drawable.hangman_3);
                        break;
                    case 4:
                        ivImagenRepresentacion.setImageResource(R.drawable.hangman_4);
                        break;
                    case 5:
                        ivImagenRepresentacion.setImageResource(R.drawable.hangman_5);
                        break;
                    case 6:
                        ivImagenRepresentacion.setImageResource(R.drawable.hangman_6);
                        break;
                }
                if (juegoAhorcado.getIntentosRestantes() == 0) {
                    tvAdvertencias.setText("Intentos agotados");
                    btnJugarDeNuevo.setVisibility(View.VISIBLE);
                }
            } else {
                tvLetrasGastadas.setText(juegoAhorcado.getLetrasGastadas().toString());
                for (int i = 0; i < juegoAhorcado.comprobarLetra(comprobarLetra).size(); i++) {
                    textoMostrado.set(juegoAhorcado.comprobarLetra(comprobarLetra).get(i), comprobarLetra);
                }

                StringBuilder mostrarResultado = new StringBuilder();
                mostrarResultado.append(textoMostrado);
                tvPalabra.setText(mostrarResultado);

                for (int i = 0; i < mostrarResultado.length(); i++) {
                    if (mostrarResultado.charAt(i) == '+') {
                        break;
                    } else if (i == mostrarResultado.length() - 1 && mostrarResultado.charAt(i) != '_') {
                        juegoTerminado = true;
                        tvAdvertencias.setText("Has acertado la palabra, enhorabuena!!");
                        btnJugarDeNuevo.setVisibility(View.VISIBLE);
                    }
                }
            }
            juegoAhorcado.comprobarLetra(comprobarLetra);
        }
    }

    public boolean comprobarLetra(char comprobarLetra) {
        for (int i = 0; i < LETRAS.length(); i++) {
            if (comprobarLetra == LETRAS.charAt(i)) {
                return true;
            }
        }

        return false;
    }

    public void inicializar() {
        StringBuilder totalGuionesPalabra = new StringBuilder();
        tvIntentosRestantes.setText("6");

        for (int i = 0; i < palabraAleatoria.length(); i++) {
            if(i == palabraAleatoria.length() - 1) {
                totalGuionesPalabra.append("_");
            } else {
                totalGuionesPalabra.append("_  ");
            }
        }

        for (int i = 0; i < totalGuionesPalabra.length(); i++) {
            textoMostrado.add(totalGuionesPalabra.charAt(i));
        }

        tvPalabra.setText(totalGuionesPalabra);
    }

    public void juegoNuevo() {
        palabraAleatoria = juegoAhorcado.getPalabraAleatoria();
        textoMostrado.clear();
        juegoTerminado = false;
        ivImagenRepresentacion.setImageResource(R.drawable.hangman_0);
        tvIntentosRestantes.setText("6");
        tvLetrasGastadas.setText("");
        tvAdvertencias.setText("");
        inicializar();
    }
}
