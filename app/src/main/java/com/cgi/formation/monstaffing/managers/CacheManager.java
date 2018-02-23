package com.cgi.formation.monstaffing.managers;

import com.cgi.formation.monstaffing.models.ResponseAuthent;

/**
 * Created by elkaissounia on 23/02/2018.
 */

public class CacheManager {

    private static CacheManager cacheManagerInstance;

    private  ResponseAuthent responseAuthent;

    CacheManager(){
    }

    public static CacheManager getInstance(){
        if(cacheManagerInstance == null){
            cacheManagerInstance = new CacheManager();
        }
        return cacheManagerInstance;
    }

    public ResponseAuthent getResponseAuthent() {
        return responseAuthent;
    }

    public void setResponseAuthent(ResponseAuthent responseAuthent) {
        this.responseAuthent = responseAuthent;
    }
}
