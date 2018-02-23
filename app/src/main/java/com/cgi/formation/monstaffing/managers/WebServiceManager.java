package com.cgi.formation.monstaffing.managers;

import com.cgi.formation.monstaffing.models.Mission;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**TODO
 * Created by elkaissounia on 22/02/2018.
 */

public class WebServiceManager {

    //Instance
    private static WebServiceManager INSTANCE;

    private static final String URLWEBSERVICE = "http://monstaffing.getsandbox.com/v1";

    private static OkHttpClient okHttpClient;

    private final Gson gson = new Gson();

    //Constrctor
    public WebServiceManager(){
        this.okHttpClient = new OkHttpClient();
    }

    //Singleton
    public static WebServiceManager getInstance(){
        if(INSTANCE == null ){
            INSTANCE = new WebServiceManager();
        }
        return INSTANCE;
    }

    public List<Mission> getFullMissions()  {
        List<Mission> founderList = new ArrayList<Mission>();
        Request myGetRequest = new Request.Builder()
                .url(URLWEBSERVICE+"/list")
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(myGetRequest).execute();
            if(response.isSuccessful()){
                Type missionType = new TypeToken<ArrayList<Mission>>(){}.getType();

                String responseData = response.body().string();
                List<Mission> listTmp = (List<Mission>)gson.fromJson(responseData,missionType);
                founderList.addAll(listTmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



       /* new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //
                Type missionType = new TypeToken<ArrayList<Mission>>(){}.getType();

                String responseData = response.body().string();
                List<Mission> listTmp = (List<Mission>)gson.fromJson(responseData,missionType);
                founderList.addAll(listTmp);
            }
        });*/
        return founderList;

    }


}
