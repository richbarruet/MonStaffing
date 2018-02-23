package com.cgi.formation.monstaffing.managers;

import com.cgi.formation.monstaffing.BuildConfig;
import com.cgi.formation.monstaffing.models.Mission;
import com.cgi.formation.monstaffing.models.ResponseAuthent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**TODO
 * Created by elkaissounia on 22/02/2018.
 */

public class WebServiceManager {

    //Instance
    private static WebServiceManager INSTANCE;

    private static final String URLWEBSERVICE = BuildConfig.BASE_URL;

    private static final String TOKEN = "token";

    private static final String KEYWORD = "keyword";

    private static final String VILLE = "ville";

    private static OkHttpClient okHttpClient;

    private final Gson gson = new Gson();

    private CacheManager cacheManagerInstance = CacheManager.getInstance();

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
     * Méthode qui fait l'appelle WS pour récupérer la lisre des miession.
     * Si les params sont vides toute la liste est renvoyée
     *
     * @param ville filtrer sur la ville
     * @param keyWord filtrer sur un mot clé
     * @return
     */
    public List<Mission> getMissionsFlitred(String ville,String keyWord){
        //Result
        List<Mission> founderList = new ArrayList<Mission>();
        //Gestion des params
        String filterPram = "";
        if(keyWord != null && !keyWord.isEmpty()){
            filterPram=filterPram+"?"+KEYWORD+"="+keyWord;
        }
        if(ville != null && !ville.isEmpty()){
            if(filterPram.equals("")){
                filterPram = filterPram+"?"+ VILLE + "=" + ville;
            } else {
                filterPram = filterPram +"&"+ VILLE + "=" + ville;
            }
        }
        //Gestion de Token d'authentification
        String token = "";
        if(cacheManagerInstance.getResponseAuthent() != null){
            token = cacheManagerInstance.getResponseAuthent().getToken();
        }
        //La requete
        Request myGetRequest = new Request.Builder()
                .url(URLWEBSERVICE+"/list"+filterPram)
                .addHeader(TOKEN,token)
                .build();

        //Gestion de la réponse
        Response response = null;
        try {
            response = okHttpClient.newCall(myGetRequest).execute();
            if(response.isSuccessful()){
                Type missionType = new TypeToken<ArrayList<Mission>>(){}.getType();

                String responseData = response.body().string();
                List<Mission> listTmp = (List<Mission>)gson.fromJson(responseData,missionType);
                founderList.addAll(listTmp);
            } else {
                founderList = null;
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
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseAuthent;
    }
}
