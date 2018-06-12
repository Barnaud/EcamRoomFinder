package com.example.benoitarnaud.ecamroomfinder;

public abstract class Place {
    protected String nom;
    protected int etage;
    protected float[] position;

    public Place(String nom, int etage, float[] position) {
        this.nom = nom;
        this.etage = etage;
        this.position = position;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }



    public String getNom() {
        return nom;
    }

    public Place() {

    }

    public int getEtage() {
        return etage;

    }


}
