package com.cgi.formation.monstaffing.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.app.ProgressDialog;

import com.cgi.formation.monstaffing.R;
import com.cgi.formation.monstaffing.managers.WebServiceManager;

import com.cgi.formation.monstaffing.models.Mission;
import com.cgi.formation.monstaffing.views.adapters.MissionAdapter;
import java.util.List;

public class DisplayActivity extends AppCompatActivity implements MissionAdapter.MissionListener {



    private static final String KEYMISSION = "keyMission";
    private static final int FILTRE_ACTIVITY= 1;
    private ListView listView;
    private Button buttonFiltre;
    private WebServiceManager webServiceManagerInstance = WebServiceManager.getInstance();
    private String city="";
    private String keyWord="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        //Récupérer la liste View
        listView = findViewById(R.id.missionListId);

        //Asynchroune Task pour l'appelle au WS
         AsyncTask asyncTask = new AsyncTask<Object,Void,List<Mission>>(){

            @Override
            protected List<Mission> doInBackground(Object[] objects) {
                return webServiceManagerInstance.getMissionsFlitred(city,keyWord);
            }

            @Override
            protected void onPostExecute(List<Mission> result){
                initDisplay(result);
            }
        };
        //Exécution de la tache
        asyncTask.execute();

        //Button pour filtrer
        buttonFiltre =(Button) findViewById(R.id.buttonFiltre);
        buttonFiltre.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                Intent intentF = new Intent(view.getContext(), FiltreActivity.class);
                startActivityForResult(intentF, FILTRE_ACTIVITY);
            }
        });
    }

    /**
     * TO DO
     *
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (FILTRE_ACTIVITY == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            String villeFilter = data.getStringExtra(FiltreActivity.BUNDLE_VILLE);
            String motclef = data.getStringExtra(FiltreActivity.BUNDLE_MOT_CLE);
            AsyncTask asyncTask = new AsyncTask<Object,Void,List<Mission>>(){
                ProgressDialog progress;
                @Override
                protected void onPreExecute(){
                    showProgress(progress);
                }

                @Override
                protected List<Mission> doInBackground(Object[] objects) {
                    return webServiceManagerInstance.getMissionsFlitred((String)objects[0],(String)objects[1]);
                }

                @Override
                protected void onPostExecute(List<Mission> result){
                    progress.dismiss();
                    initDisplay(result);
                }
            };
            city = villeFilter;
            keyWord = motclef;
            asyncTask.execute(city,keyWord);
        }
    }
    private void initDisplay(List<Mission> missions ){
        MissionAdapter adapter = new MissionAdapter(this,missions,this);
        listView.setAdapter(adapter);
    }

    /**
     * Montrer
     * @param progress
     */
    private void showProgress(ProgressDialog progress){
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        progress.dismiss();
    }

}
