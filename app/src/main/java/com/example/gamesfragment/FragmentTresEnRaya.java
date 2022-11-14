package com.example.gamesfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

    public FragmentTresEnRaya() {
        super(R.layout.fragment_3raya);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    }

    public void jugar() {

    }
}
