package com.cgi.formation.monstaffing.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.cgi.formation.monstaffing.R;
import com.cgi.formation.monstaffing.models.Contact;
import com.cgi.formation.monstaffing.models.Mission;
import com.cgi.formation.monstaffing.views.adapters.MissionAdapter;

import java.util.ArrayList;
import java.util.Date;

public class DisplayActivity extends AppCompatActivity implements MissionAdapter.MissionListener{

    private static final String KEYMISSION = "keyMission";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ListView listView = findViewById(R.id.missionListId);
        ArrayList<Mission> missionsList = new ArrayList<Mission>();
        ArrayList<String> listesCompte = new ArrayList<String>();
        listesCompte.add("Android");
        listesCompte.add("Java");
        ArrayList<Contact> listesContacts = new ArrayList<Contact>();
        listesContacts.add(new Contact(1,"Ali","Kaiss"));
        missionsList.add(new Mission(1,Mission.Vertical.ADEME,"Poste1",listesCompte,"Descriptif",Mission.Lieu.Angers,new Date(),listesContacts));
        missionsList.add(new Mission(2,Mission.Vertical.ADEME,"Poste1",listesCompte,"Descriptif",Mission.Lieu.Angers,new Date(),listesContacts));
        missionsList.add(new Mission(3,Mission.Vertical.ADEME,"Poste1",listesCompte,"Descriptif",Mission.Lieu.Angers,new Date(),listesContacts));


        MissionAdapter adapter = new MissionAdapter(this,missionsList,this);

        listView.setAdapter(adapter);

    }

    @Override
    public void onClickMissionItem(Mission mission) {
        Intent applyOfferIntent = new Intent(this,ApplyOfferActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEYMISSION,mission);
        applyOfferIntent.putExtras(bundle);
        startActivity(applyOfferIntent);
    }
}
