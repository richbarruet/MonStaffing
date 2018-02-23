package com.cgi.formation.monstaffing.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cgi.formation.monstaffing.R;
import com.cgi.formation.monstaffing.models.Contact;
import com.cgi.formation.monstaffing.models.Mission;

import java.text.SimpleDateFormat;
import java.util.Locale;
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

        Button buttonApply;

        // Recuperer l'objet mission depuis l'intent
        mission = (Mission) getIntent().getSerializableExtra("keyMission");

        if(mission != null){
            // Convertir les listes en string
            String listCompetence = TextUtils.join(", ", mission.getCompetences());
            String listContact = "";
            for (Contact ct : mission.getContacts()) {
                if(!listContact.equals(""))
                    listContact = listContact.concat(", ");
                listContact = listContact.concat(ct.getPrenom() + " " + ct.getNom());
            }

            // Format de la date
            SimpleDateFormat simpleDate =  new SimpleDateFormat(getString(R.string.date_format), Locale.getDefault());
            String date = simpleDate.format(mission.getDateDeMission());

            // Affecter les valeurs de l'objet mission au textview
            textViewVertical.setText(mission.getVertical());
            textViewPoste.setText(mission.getPoste());
            textViewCompetences.setText(listCompetence);
            textViewLieu.setText(mission.getLieu());
            textViewDate.setText(date);
            textViewContact.setText(listContact);
            textViewDescriptif.setText(mission.getDescriptif());

            buttonApply = findViewById(R.id.button_apply);
            buttonApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"allan.sirdey@cgi.com"});
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, mission.getDescriptif());
                    startActivity(emailIntent);
                }
            });
        }

    }
}
