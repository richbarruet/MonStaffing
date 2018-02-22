package com.cgi.formation.monstaffing.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.cgi.formation.monstaffing.R;

public class ApplyOfferActivity extends AppCompatActivity {

    TextView textViewClient;
    TextView textViewPoste;
    TextView textViewCompetences;
    TextView textViewLieu;
    TextView textViewDate;
    TextView textViewContact;
    TextView textViewDescriptif;

    EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_offer);

        textViewClient = findViewById(R.id.textview_client);
        textViewPoste = findViewById(R.id.textview_poste);
        textViewCompetences = findViewById(R.id.textview_competences);
        textViewLieu = findViewById(R.id.textview_lieu);
        textViewDate = findViewById(R.id.textview_date);
        textViewContact = findViewById(R.id.textview_contact);
        textViewDescriptif = findViewById(R.id.textview_descriptif);
        editTextMessage = findViewById(R.id.edittext_message);

    }
}
