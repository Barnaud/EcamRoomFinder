package com.example.benoitarnaud.ecamroomfinder;

import java.util.ArrayList;

public class Point extends Place {
    public Salle nrstStairs;
    private ArrayList<Salle> sallesAdj;

    public Salle getNrstStairs() {
        return nrstStairs;
    }

    public void setNrstStairs(Salle nrstStairs) {
        this.nrstStairs = nrstStairs;
    }

    public Point(String nom, int etage, float[] position) {
        super(nom, etage, position);

        sallesAdj = new ArrayList<Salle>();


    }




    public ArrayList<Salle> getSallesAdj() {
        return sallesAdj;
    }

    public void addSallesAdj(Salle room) {
        this.sallesAdj.add(room);
    }

}
