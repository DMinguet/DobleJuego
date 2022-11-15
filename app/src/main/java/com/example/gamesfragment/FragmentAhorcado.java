package com.example.gamesfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentAhorcado extends Fragment {
    private final String LETRAS = "qwertyuiopasdfghjklñzxcvbnmáéíóú";
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
                    ivImagenRepresentacion.setImageResource(R.drawable.hangman_0);
                    tvIntentosRestantes.setText(String.valueOf(juegoAhorcado.getIntentosRestantes()));
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
        char letra = textoIntroducido.charAt(0);

        if (comprobarLetra(letra)) {
            ArrayList<Integer> posiciones = juegoAhorcado.comprobarLetra(letra);
            if (posiciones == null) {
                tvLetrasGastadas.setText(juegoAhorcado.getLetrasGastadas().toString());
                tvIntentosRestantes.setText(String.valueOf(juegoAhorcado.getIntentosRestantes()));
                switch (juegoAhorcado.getIntentosRestantes()) {
                    case 0:
                        ivImagenRepresentacion.setImageResource(R.drawable.hangman_0);
                        tvIntentosRestantes.setText(String.valueOf(juegoAhorcado.getIntentosRestantes()));
                        tvAdvertencias.setText("Intentos agotados");
                        btnJugarDeNuevo.setVisibility(View.VISIBLE);
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
            } else if (posiciones.get(0) == -1) {
                tvAdvertencias.setText("Letra ya introducida anteriormente");
            } else {
                tvLetrasGastadas.setText(juegoAhorcado.getLetrasGastadas().toString());

                for (int i = 0; i < posiciones.size(); i++) {
                    textoMostrado.set(posiciones.get(i), letra);
                }

                StringBuilder mostrarResultado = new StringBuilder();
                for (int i = 0; i < textoMostrado.size(); i++) {
                    if (i == textoMostrado.size() - 1) {
                        mostrarResultado.append(textoMostrado.get(i));
                    } else {
                        mostrarResultado.append(textoMostrado.get(i)).append("  ");
                    }
                }
                tvPalabra.setText(mostrarResultado);

                for (int i = 0; i < mostrarResultado.length(); i++) {
                    if (mostrarResultado.charAt(i) == '_') {
                        break;
                    } else if (i == mostrarResultado.length() - 1 && mostrarResultado.charAt(i) != '_') {
                        juegoTerminado = true;
                        tvAdvertencias.setText("Has acertado la palabra, enhorabuena!!");
                        btnJugarDeNuevo.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public boolean comprobarLetra(char letra) {
        for (int i = 0; i < LETRAS.length(); i++) {
            if (letra == LETRAS.charAt(i)) {
                return true;
            }
        }

        return false;
    }

    public void inicializar() {
        StringBuilder totalGuionesPalabra = new StringBuilder();
        StringBuilder mostrarGuiones = new StringBuilder();
        tvIntentosRestantes.setText("6");

        for (int i = 0; i < palabraAleatoria.length(); i++) {
            if(i == palabraAleatoria.length() - 1) {
                mostrarGuiones.append("_");
            } else {
                mostrarGuiones.append("_  ");
            }
            totalGuionesPalabra.append('_');
        }

        for (int i = 0; i < totalGuionesPalabra.length(); i++) {
            textoMostrado.add(totalGuionesPalabra.charAt(i));
        }

        tvPalabra.setText(mostrarGuiones);
    }

    public void juegoNuevo() {
        palabraAleatoria = juegoAhorcado.getPalabraAleatoria();
        textoMostrado.clear();
        juegoTerminado = false;
        ivImagenRepresentacion.setImageResource(R.drawable.hangman_6);
        tvIntentosRestantes.setText("6");
        tvLetrasGastadas.setText("");
        tvAdvertencias.setText("");
        inicializar();
    }
}
