package com.cgi.formation.monstaffing.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.app.ProgressDialog;
import android.widget.ProgressBar;

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
    private int pagination = 0;
    private WebServiceManager webServiceManagerInstance = WebServiceManager.getInstance();
    private String city="";
    private String keyWord="";
    private boolean endOfTheList = false;
    private SwipeRefreshLayout swipeContainer;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        initProgress(progress);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("PULL_TO_REFRESH", "Pull to refresh");
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        //Récupérer la liste View
        listView = findViewById(R.id.missionListId);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int currentVisibleItemCount;
            private int currentScrollState;
            private int currentFirstVisibleItem;
            private int totalItem;
            private LinearLayout lBelow;


            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.currentScrollState = scrollState;
                this.loadMoreData();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.currentFirstVisibleItem = firstVisibleItem;
                this.currentVisibleItemCount = visibleItemCount;
                this.totalItem = totalItemCount;
            }

            private void loadMoreData() {
                if (totalItem - currentFirstVisibleItem == currentVisibleItemCount && this.currentScrollState == SCROLL_STATE_IDLE) {
                    if(!endOfTheList){
                        AsyncTask asyncTask = new AsyncTask<Object, Void, List<Mission>>() {

                            @Override
                            protected void onPreExecute() {
                                progress.show();
                            }

                            @Override
                            protected List<Mission> doInBackground(Object[] objects) {
                                return webServiceManagerInstance.getMissionsFlitred(city,keyWord,pagination);
                            }

                            @Override
                            protected void onPostExecute(List<Mission> result) {
                                progress.dismiss();
                                if(result.size() < 5)
                                    endOfTheList = true;
                                pagination++;
                                MissionAdapter adapter = (MissionAdapter) listView.getAdapter();
                                adapter.putMissions(result);
                            }
                        };
                        asyncTask.execute();
                    }
                }
            }
        });

        //Asynchroune Task pour l'appelle au WS
         AsyncTask asyncTask = new AsyncTask<Object,Void,List<Mission>>(){

             @Override
             protected void onPreExecute() {
                 progress.show();
             }

            @Override
            protected List<Mission> doInBackground(Object[] objects) {
                return webServiceManagerInstance.getMissionsFlitred(city,keyWord,pagination);
            }

            @Override
            protected void onPostExecute(List<Mission> result){
                progress.dismiss();
                initDisplay(result);
                pagination++;
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
        Intent applyOfferIntent = new Intent(this, ApplyOfferActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEYMISSION, mission);
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
                @Override
                protected void onPreExecute() {
                    progress.show();
                }

                @Override
                protected List<Mission> doInBackground(Object[] objects) {
                    return webServiceManagerInstance.getMissionsFlitred((String)objects[0],(String)objects[1], (int)objects[2]);
                }

                @Override
                protected void onPostExecute(List<Mission> result){
                    progress.dismiss();
                    initDisplay(result);
                }
            };
            city = villeFilter;
            keyWord = motclef;
            asyncTask.execute(city,keyWord, 0);
            pagination = 0;
        }
    }

    private void initDisplay(List<Mission> missions) {
        MissionAdapter adapter = new MissionAdapter(this, missions, this);
        listView.setAdapter(adapter);
    }

    private void initProgress(ProgressDialog progres){
        progress = new ProgressDialog(this);
        progress.setTitle("Chargement");
        progress.setMessage("En attente des résultats...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
    }

}
