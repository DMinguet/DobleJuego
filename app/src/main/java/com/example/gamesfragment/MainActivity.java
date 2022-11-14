package com.example.gamesfragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentAhorcado.IOnAttachListener{
    private ArrayList<String> palabras;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Elige juego --->");
    }

    private void loadData() {
        ParserPalabras parser = new ParserPalabras(this);

        if(parser.parse()) {
            palabras = parser.getPalabras();
        } else {
            Toast.makeText(this, "Error al obtener las palabras", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public ArrayList<String> getPalabras() {
        if (palabras == null) {
            loadData();
        }
        return palabras;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ahorcado:
                manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.frgPrincipal, FragmentAhorcado.class, null)
                        .commit();
                return true;
            case R.id.tresEnRaya:
                manager = getSupportFragmentManager();
                manager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.frgPrincipal, FragmentTresEnRaya.class, null)
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}