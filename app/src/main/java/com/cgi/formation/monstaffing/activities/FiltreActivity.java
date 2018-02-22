package com.cgi.formation.monstaffing.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.cgi.formation.monstaffing.R;
import com.cgi.formation.monstaffing.models.Mission;

public class FiltreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtre);

        EditText motCleRentre = findViewById(R.id.motCleUser);
        Spinner villeChoisie = findViewById(R.id.villeChoisie);

        villeChoisie.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Mission.Lieu.values()));



    }


}
