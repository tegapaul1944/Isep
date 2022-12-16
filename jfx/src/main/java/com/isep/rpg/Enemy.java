package com.isep.rpg;

import static java.lang.Math.abs;

abstract public class Enemy extends Fighter {
    //Attribut
    private int armor;
    private int weaponDamage;

    //Constructeur
    public Enemy(int lifePoints, int armor, int weaponDamage, String name) {
        super(name,lifePoints);
        this.armor = armor;
        this.weaponDamage = weaponDamage;
    }


    //Fonctions récup données
    public int getArmor(){return armor;}
    public int getWeaponDamage(){return weaponDamage;}

    //Fonction influence données
    public void setArmor(int armor){
        this.armor=armor;
    }

    public void setWeaponDamage(int damage){
        this.weaponDamage=damage;
    }

    //Fonctions actions
    public int attack(Hero hero){
        int life = hero.getLifePoints();
        int degats = abs(this.weaponDamage*2 - hero.getArmor());
        return(hero.setLifePoints(life-degats));
    }

}
