package com.isep.rpg;

public class Potion extends Consumable {

    int addMana;

    //Constructeur
    public Potion (int addMana){
        this.addMana=addMana;
    }

    //Recup donnees
    public int getAddMana(){return addMana;}

}
