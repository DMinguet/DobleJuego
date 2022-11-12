package com.example.gamesfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Elige juego --->");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ahorcado:
                jugarAhorcado();
                return true;
            case R.id.tresEnRaya:
                jugarTresEnRaya();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void jugarAhorcado() {

    }

    public void jugarTresEnRaya() {

    }
}