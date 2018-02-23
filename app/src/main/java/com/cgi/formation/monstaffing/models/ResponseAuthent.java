package com.cgi.formation.monstaffing.models;

import java.io.Serializable;

/**
 * Created by elkaissounia on 23/02/2018.
 */

public class ResponseAuthent implements Serializable{

    //attribut Token
    private String token;

    public ResponseAuthent(String token){
        setToken(token);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
