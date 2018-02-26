package com.cgi.formation.monstaffing.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cgi.formation.monstaffing.BuildConfig;
import com.cgi.formation.monstaffing.R;
import com.cgi.formation.monstaffing.models.Contact;
import com.cgi.formation.monstaffing.models.Mission;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

        // Affichage bouton Retour
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewVertical = findViewById(R.id.textview_vertical);
        textViewPoste = findViewById(R.id.textview_poste);
        textViewCompetences = findViewById(R.id.textview_competences);
        textViewLieu = findViewById(R.id.textview_lieu);
        textViewDate = findViewById(R.id.textview_date);
        textViewContact = findViewById(R.id.textview_contact);
        textViewDescriptif = findViewById(R.id.textview_descriptif);
        editTextMessage = findViewById(R.id.edittext_message);

        // Recuperer l'objet mission depuis l'intent
        mission = (Mission) getIntent().getSerializableExtra("keyMission");

        if (mission != null) {
            // Convertir les listes en string
            String listCompetence = TextUtils.join(", ", mission.getCompetences());
            String listContact = "";
            for (Contact ct : mission.getContacts()) {
                if (!listContact.equals(""))
                    listContact = listContact.concat(", ");
                listContact = listContact.concat(ct.getPrenom() + " " + ct.getNom());
            }

            // Format de la date
            //SimpleDateFormat simpleDate =  new SimpleDateFormat(getString(R.string.date_format), Locale.getDefault());
            //String date = simpleDate.format(mission.getDateDeMission());
            if (mission.getDateDeMission() == null) {
                SimpleDateFormat simpleDate = new SimpleDateFormat(getString(R.string.date_format), Locale.getDefault());
                String date = simpleDate.format(new Date());
                mission.setDateDeMission(date);
            }

            // Affecter les valeurs de l'objet mission au textview
            textViewVertical.setText(mission.getVertical());
            textViewPoste.setText(mission.getPoste());
            textViewCompetences.setText(listCompetence);
            textViewLieu.setText(mission.getLieu());
            textViewDate.setText(mission.getDateDeMission());
            textViewContact.setText(listContact);
            textViewDescriptif.setText(mission.getDescriptif());

            // Le bouton "postuler" ouvre l'application mail avec les valeurs pré remplis
            buttonApply = findViewById(R.id.button_apply);
            buttonApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{BuildConfig.TARGET_MAIL});
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Postuler offre CGI");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, mission.getDescriptif() + "\n\n" + editTextMessage.getText().toString());
                    startActivity(emailIntent);
                }
            });
        }

    }

    // Action Bouton Retour
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
