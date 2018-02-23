package com.cgi.formation.monstaffing.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by elkaissounia on 22/02/2018.
 */

public class Mission implements Serializable{

    //Attribut id
    private int id;

    //Attribut vertical
    private String vertical;

    //Attribut poste
    private java.lang.String poste;

    //Attribut competences
    private List<java.lang.String> competences;

    //Attribut String
    private String lieu;

    //Attribut descriptif
    private java.lang.String descriptif;

    //Attribut date de mission
    @SerializedName("date_debut_mission")
    private String dateDeMission;

    //Attribut Contact
    @SerializedName("liste_contact")
    private List<Contact> contacts;

    public Mission(int id, String vertical, java.lang.String poste, List<java.lang.String> competences, String descriptif,String lieu, String dateDeMission, List<Contact> contacts){
        setId(id);
        setVertical(vertical);
        setPoste(poste);
        setCompetences(competences);
        setDescriptif(descriptif);
        setDateDeMission(dateDeMission);
        setLieu(lieu);
        setContacts(contacts);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String string) {
        this.vertical = string;
    }

    public java.lang.String getPoste() {
        return poste;
    }

    public void setPoste(java.lang.String poste) {
        this.poste = poste;
    }

    public List<java.lang.String> getCompetences() {
        return competences;
    }

    public void setCompetences(List<java.lang.String> competences) {
        this.competences = competences;
    }

    public java.lang.String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(java.lang.String descriptif) {
        this.descriptif = descriptif;
    }

    public String getDateDeMission() {
        return dateDeMission;
    }

    public void setDateDeMission(String dateDeMission) {
        this.dateDeMission = dateDeMission;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String string) {
        this.lieu = string;
    }

    //Enums

    //Enum String
    public static enum Lieu {
        Angers("Angers"),
        AngersPossible3jParsemaineANantes("Angers (possible 3j/semaine à Nantes)"),
        Brest("Brest"),
        Carquefou("Carquefou"),
        CarquefouGieIris("Carquefou GIE IRIS"),
        Landerneau("Landerneau"),
        Nantes("Nantes"),
        NantesEraudiere("Nantes - Eraudière"),
        NantesMeae("Nantes - MEAE"),
        NantesSiteDeTardieu("Nantes - site de Tardieu (île de Nantes)"),
        NantesLaRiviere("Nantes ( La Rivière)"),
        NantesSiteDuCD44("Nantes, site du CD 44"),
        NantesTourdeBretagne("Nantes, Tour de Bretagne"),
        SaintHerblain("Saint Herblain"),
        StNazaire("St-Nazaire"),
        VannesCarquefou("Vannes/Carquefou");

        private String value;

        Lieu(String value){
            setValue(value);
        }

        public String getValue() {
            return value;
        }

        public void setValue(java.lang.String value) {
            this.value = value;
        }

        @Override
        public String toString(){
            return this.getValue();
        }

        }

    //Enum Vertical
    public static enum Vertical {
        ADEME("ADEME"),
        Agro("Agro"),
        AS24("AS24"),
        AXA("AXA"),
        Banque("Banque"),
        BanqueNationaleDuCanada("Banque Nationale du Canada"),
        CA("CA"),
        CD44("CD44"),
        DGFIP("DGFIP"),
        Eurofins("Eurofins"),
        HISM("HISM"),
        IBP("I-BP"),
        LuxiorAssurance("Luxior Assurance"),
        Manpower("Manpower"),
        MinuropeEtDesAffairesEtrangeres("Min. de l'Europe et des Affaires Etrangères"),
        MyMoneyBank("MyMoneyBank"),
        NavalGroup("Naval Group (DCNS)"),
        PoleEmploi("Pôle emploi"),
        ReseauLaPoste("Réseau La Poste"),
        SNCFReseau("SNCF Réseau"),
        SocieteGeneral("Société Générale"),
        STX("STX"),
        SystemU("System U"),
        TTI("TTI");

        String value;

        Vertical(String value){
            setValue(value);
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
