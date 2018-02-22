package com.cgi.formation.monstaffing.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cgi.formation.monstaffing.R;
import com.cgi.formation.monstaffing.models.Mission;

import java.util.List;

/**
 * Created by elkaissounia on 22/02/2018.
 */

public class MissionView extends LinearLayout {

    //
    private LinearLayout rootLinearLayout;

    //
    private Mission mission;

    public MissionView(Context context, Mission mission) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootLinearLayout = (LinearLayout) inflater.inflate(R.layout.mission_item,this,true);
        this.mission = mission;
    }

    public void initMissionItemView(){
        //Récupération des textsViews
        TextView clientView  =  (TextView)rootLinearLayout.findViewById(R.id.client);
        TextView postView  =  (TextView)rootLinearLayout.findViewById(R.id.poste);
        TextView competencesView  =  (TextView)rootLinearLayout.findViewById(R.id.competences);
        TextView villeView  =  (TextView)rootLinearLayout.findViewById(R.id.ville);

        //Initialisation
        if(clientView != null){
            clientView.setText(mission.getVertical().getValue());
        }
        if(postView != null){
            postView.setText(mission.getPoste());
        }
        if(competencesView == null){
            List<String> listCompetences = mission.getCompetences();
            if(!listCompetences.isEmpty()){
                if(listCompetences.size() > 1) {
                    competencesView.setText(listCompetences.get(0) + " " + listCompetences.get(1));
                } else {
                    competencesView.setText(listCompetences.get(0));
                }
            }
        }
        if(villeView != null){
            villeView.setText(mission.getLieu().getValue());
        }
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
