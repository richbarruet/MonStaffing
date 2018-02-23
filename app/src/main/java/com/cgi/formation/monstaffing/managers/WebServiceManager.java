package com.cgi.formation.monstaffing.managers;

import com.cgi.formation.monstaffing.models.Mission;
import com.cgi.formation.monstaffing.models.ResponseAuthent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

/**TODO
 * Created by elkaissounia on 22/02/2018.
 */

public class WebServiceManager {

    //Instance
    private static WebServiceManager INSTANCE;

    private static final String URLWEBSERVICE = "http://monstaffing.getsandbox.com/v1";

    private static OkHttpClient okHttpClient;

    private final Gson gson = new Gson();

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

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

    /**
     *
     * @return
     */
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
        return founderList;

    }

    public ResponseAuthent login(final String username, final String password) {
        JSONObject jsonObject = null;
        ResponseAuthent responseAuthent = null;
        try {
            jsonObject = new JSONObject().put("password", password).put("username",username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON,jsonObject.toString());

        Request myGetRequest  = new Request.Builder()
                .url(URLWEBSERVICE+"/user/login")
                .post(body)
                .build();
        Response response = null;

        try {
            response = okHttpClient.newCall(myGetRequest).execute();
            if(response.isSuccessful()){
                String responseData = response.body().string();
                responseAuthent = (ResponseAuthent)gson.fromJson(responseData,ResponseAuthent.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseAuthent;
    }
}
