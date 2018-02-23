package com.cgi.formation.monstaffing.models;

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
    private Vertical vertical;

    //Attribut poste
    private String poste;

    //Attribut competences
    private List<String> competences;

    //Attribut Lieu
    private Lieu lieu;

    //Attribut descriptif
    private String descriptif;

    //Attribut date de mission
    private Date dateDeMission;

    //Attribut Contact
    private List<Contact> contacts;

    public Mission(int id,Vertical vertical,String poste,List<String> competences,String descriptif,Lieu lieu,Date dateDeMission,List<Contact> contacts){
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

    public Vertical getVertical() {
        return vertical;
    }

    public void setVertical(Vertical vertical) {
        this.vertical = vertical;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public List<String> getCompetences() {
        return competences;
    }

    public void setCompetences(List<String> competences) {
        this.competences = competences;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public Date getDateDeMission() {
        return dateDeMission;
    }

    public void setDateDeMission(Date dateDeMission) {
        this.dateDeMission = dateDeMission;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    //Enums

    //Enum Lieu
    public static enum Lieu  {
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

        public void setValue(String value) {
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
