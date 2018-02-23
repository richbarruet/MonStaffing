package com.cgi.formation.monstaffing.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.cgi.formation.monstaffing.R;
import com.cgi.formation.monstaffing.managers.WebServiceManager;
import com.cgi.formation.monstaffing.models.Contact;
import com.cgi.formation.monstaffing.models.Mission;
import com.cgi.formation.monstaffing.views.adapters.MissionAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DisplayActivity extends AppCompatActivity implements MissionAdapter.MissionListener {



    private static final String KEYMISSION = "keyMission";

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        listView = findViewById(R.id.missionListId);

         AsyncTask asyncTask = new AsyncTask<Object,Void,List<Mission>>(){

            @Override
            protected List<Mission> doInBackground(Object[] objects) {
                return WebServiceManager.getInstance().getFullMissions();
            }

            @Override
            protected void onPostExecute(List<Mission> result){
                initDisplay(result);
            }
        };
        asyncTask.execute();
    }

    /**
     * TO DO
     * @param mission
     */
    @Override
    public void onClickMissionItem(Mission mission) {
        Intent applyOfferIntent = new Intent(this,ApplyOfferActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEYMISSION,mission);
        applyOfferIntent.putExtras(bundle);
        startActivity(applyOfferIntent);
    }

    private void initDisplay(List<Mission> missions ){
        MissionAdapter adapter = new MissionAdapter(this,missions,this);
        listView.setAdapter(adapter);
    }
}
