package com.isep.rpg;

public class Hunter extends Hero {
    //Attributs
    int arrows;

    //Constructeur
    public Hunter(String name,String classe){
        super(name,classe);
        this.arrows=10;
    }

    //Fonctions récup données
    public int getArrows(){return arrows;}

    //Fonctions influençant sur les données
    public int setArrows(int setArrows){
        this.arrows = setArrows;
        return (setArrows);
    }

    //Fonctions actions
    @Override
    public int attack(Enemy enemy){
        int life = enemy.getLifePoints();
        int degats = 0;
        if (this.arrows>0) {
            degats = this.getWeaponDamage() - enemy.getArmor();
            this.arrows -= 1;
        }else{
            degats = 0;
        }
        return (enemy.setLifePoints(life-degats));
    };




    public int recupArrow() {
        int recupArrows = Math.round(getLevel()/2)+1; //Plus on monte de niveau, plus on recup de fleches
        return (setArrows(getArrows() + recupArrows));
    }
}
