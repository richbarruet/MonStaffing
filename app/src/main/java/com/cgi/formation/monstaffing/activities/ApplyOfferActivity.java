package com.cgi.formation.monstaffing.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cgi.formation.monstaffing.R;
import com.cgi.formation.monstaffing.models.Mission;

import java.util.logging.Logger;

public class ApplyOfferActivity extends AppCompatActivity {

    TextView textViewVertical;
    TextView textViewPoste;
    TextView textViewCompetences;
    TextView textViewLieu;
    TextView textViewDate;
    TextView textViewContact;
    TextView textViewDescriptif;

    EditText editTextMessage;

    Button buttonApply;

    Mission mission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_offer);

        textViewVertical = findViewById(R.id.textview_vertical);
        textViewPoste = findViewById(R.id.textview_poste);
        textViewCompetences = findViewById(R.id.textview_competences);
        textViewLieu = findViewById(R.id.textview_lieu);
        textViewDate = findViewById(R.id.textview_date);
        textViewContact = findViewById(R.id.textview_contact);
        textViewDescriptif = findViewById(R.id.textview_descriptif);
        editTextMessage = findViewById(R.id.edittext_message);

        // Recuperer l'objet mission depuis l'intent
        mission = (Mission) getIntent().getSerializableExtra("Mission");

        if(mission != null){
            // Convertir les listes en string
            String listCompetence = TextUtils.join(", ", mission.getCompetences());
            String listContact = TextUtils.join(", ", mission.getContacts());

            // Affecter les valeurs de l'objet mission au textview
            textViewVertical.setText(mission.getVertical().getValue());
            textViewPoste.setText(mission.getPoste());
            textViewCompetences.setText(listCompetence);
            textViewLieu.setText(mission.getLieu().getValue());
            textViewDate.setText(mission.getDateDeMission().toString());
            textViewContact.setText(listContact);
            textViewDescriptif.setText(mission.getDescriptif());
        }

    }
}
