package com.isep.rpg;

public class Fighter {
    private String name;
    private int lifePoints;

    public Fighter(String name, int lifePoints){
        this.name=name;
        this.lifePoints=lifePoints;
    }

    //Fonctions recup données
    public String getName(){return name;}
    public int getLifePoints() {
        return lifePoints;
    }

    //Fonctions influents données
    public int setLifePoints(int life){
        this.lifePoints = life;
        return life;
    }



}
