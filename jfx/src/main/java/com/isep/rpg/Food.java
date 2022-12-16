package com.isep.rpg;

public class Food extends Consumable{
    int addPV;

    //Constructeur
    public Food (int addPV){
        this.addPV=addPV;
    }

    //Recup données
    public int getAddPV(){return addPV;}

}
