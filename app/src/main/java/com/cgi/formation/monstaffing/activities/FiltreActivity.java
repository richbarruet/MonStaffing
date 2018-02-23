package com.cgi.formation.monstaffing.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import com.cgi.formation.monstaffing.R;
import com.cgi.formation.monstaffing.models.Mission;

public class FiltreActivity extends AppCompatActivity {

    public static final String BUNDLE_MOT_CLE = "BUNDLE_MOT_CLE";
    public static final String BUNDLE_VILLE = "BUNDLE_VILLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtre);

        final EditText motCleRentre = findViewById(R.id.motCleUser);
        final Spinner villeChoisie = findViewById(R.id.villeChoisie);
        Button boutonValidation = findViewById(R.id.boutonValidation);

        // Permet de récupérer les valeurs de l'enum avec les villes
        villeChoisie.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Mission.Lieu.values()));


        // Lors de la validation de la recherche, ferme l'activité en sauvegardant les critères de recherches
        boutonValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String motChoisi = motCleRentre.getText().toString();
                intent.putExtra(BUNDLE_MOT_CLE, motChoisi);
                Mission.Lieu lieuChoisie = (Mission.Lieu) villeChoisie.getSelectedItem();
                intent.putExtra(BUNDLE_VILLE, lieuChoisie.getValue());
                setResult(RESULT_OK, intent);
                finish();

            }
        });


    }


}
