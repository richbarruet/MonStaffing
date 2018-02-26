package com.cgi.formation.monstaffing.views.adapters;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cgi.formation.monstaffing.models.Mission;
import com.cgi.formation.monstaffing.views.MissionView;

import java.util.List;

/**
 * TODO
 * Created by elkaissounia on 22/02/2018.
 */

public class MissionAdapter extends BaseAdapter {



    private Context context;

    private List<Mission> data;

    private MissionListener missionListener;

    public MissionAdapter(Context context, List<Mission> data,MissionListener missionListener){
        super();
        this.context = context;
        this.data = data;
        this.missionListener = missionListener;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = new MissionView(context,(Mission)this.getItem(i));
        }
        final MissionView missionView = (MissionView)view;

        missionView.setMission((Mission) this.getItem(i));
        missionView.initMissionItemView();

        missionView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                missionListener.onClickMissionItem(missionView.getMission());
            }
        });


        return view;
    }

    public void putMissions(List<Mission> missions){
        data.addAll(missions);
        notifyDataSetChanged();
    }


    public interface MissionListener {
        public void onClickMissionItem(Mission mission);
    }
}
