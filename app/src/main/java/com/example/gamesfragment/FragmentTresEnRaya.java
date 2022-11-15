package com.example.gamesfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentTresEnRaya extends Fragment {
    private TextView tvMarcadorJugador;
    private TextView tvMarcadorCpu;
    private ImageButton ibZona1;
    private ImageButton ibZona2;
    private ImageButton ibZona3;
    private ImageButton ibZona4;
    private ImageButton ibZona5;
    private ImageButton ibZona6;
    private ImageButton ibZona7;
    private ImageButton ibZona8;
    private ImageButton ibZona9;
    private TextView tvResultado;
    private Button btnNuevoJuego;
    private Juego3EnRaya juego3EnRaya;
    private boolean juegoTerminado = false;
    private ArrayList<Integer> zonasSeleccionadas;

    public FragmentTresEnRaya() {
        super(R.layout.fragment_3raya);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        zonasSeleccionadas = new ArrayList<>();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        juego3EnRaya = new Juego3EnRaya();
        juego3EnRaya.inicializar();

        tvMarcadorJugador = view.findViewById(R.id.tvMarcadorJugador);
        tvMarcadorCpu = view.findViewById(R.id.tvMarcadorCpu);
        ibZona1 = view.findViewById(R.id.ibZona1);
        ibZona2 = view.findViewById(R.id.ibZona2);
        ibZona3 = view.findViewById(R.id.ibZona3);
        ibZona4 = view.findViewById(R.id.ibZona4);
        ibZona5 = view.findViewById(R.id.ibZona5);
        ibZona6 = view.findViewById(R.id.ibZona6);
        ibZona7 = view.findViewById(R.id.ibZona7);
        ibZona8 = view.findViewById(R.id.ibZona8);
        ibZona9 = view.findViewById(R.id.ibZona9);
        tvResultado = view.findViewById(R.id.tvResultado);
        btnNuevoJuego = view.findViewById(R.id.btnNuevoJuego);

        ibZona1.setImageResource(R.drawable.tictactoe_o);
        inicializar();
        ibZona1.setImageResource(R.drawable.tictactoe_o);

        if (!juegoTerminado) {
            ibZona1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ibZona1.setImageResource(R.drawable.tictactoe_o);
                }
            });
            ibZona2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ibZona2.setImageResource(R.drawable.tictactoe_o);
                }
            });
            ibZona3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            ibZona4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            ibZona5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            ibZona6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            ibZona7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            ibZona8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            ibZona9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        } else {
            btnNuevoJuego.setVisibility(View.VISIBLE);
        }
    }

    public void inicializar() {
        ibZona1.setImageResource(R.drawable.tictactoe_void);
        ibZona2.setImageResource(R.drawable.tictactoe_void);
        ibZona3.setImageResource(R.drawable.tictactoe_void);
        ibZona4.setImageResource(R.drawable.tictactoe_void);
        ibZona5.setImageResource(R.drawable.tictactoe_void);
        ibZona6.setImageResource(R.drawable.tictactoe_void);
        ibZona7.setImageResource(R.drawable.tictactoe_void);
        ibZona8.setImageResource(R.drawable.tictactoe_void);
        ibZona9.setImageResource(R.drawable.tictactoe_void);
        btnNuevoJuego.setVisibility(View.GONE);
        tvResultado.setText("");
    }
}
