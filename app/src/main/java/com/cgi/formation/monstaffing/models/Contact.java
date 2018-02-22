package com.cgi.formation.monstaffing.models;

import java.io.Serializable;

/**
 * Created by elkaissounia on 22/02/2018.
 */

public class Contact implements Serializable{
    //Attribut Id
    private int id;
    
    //Attribut Prenom
    private String prenom;

    //AAttribut Nom
    private String nom;

    public Contact(int id,String firstName,String lastName){
        setId(id);
        setPrenom(firstName);
        setNom(lastName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
