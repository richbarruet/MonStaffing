package com.cgi.formation.monstaffing.activities;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.cgi.formation.monstaffing.R;
import com.cgi.formation.monstaffing.models.Mission;

public class FiltreActivity extends AppCompatActivity {

    private EditText motCleRentre;
    private EditText villeChoisie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtre);

        motCleRentre = (EditText) findViewById(R.id.motCleUser);
        villeChoisie = (EditText) findViewById(R.id.villeChoisie);

        AlertDialog.Builder menuVille = new AlertDialog.Builder(this);
        menuVille.setTitle("Choisissez la ville");


    }
}
