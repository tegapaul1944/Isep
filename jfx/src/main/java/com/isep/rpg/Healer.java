package com.isep.rpg;


public class Healer extends SpellCaster {

    //Attributs
    int mana;

    //Constructeur
    public Healer(String name,String classe){
        super(name,classe);
        this.mana=100;
    }

    //Fonctions récup données
    public int getMana(){
        return mana;
    }

    //Fonctions influençant sur les données
    public int setMana(int setManaPoints){
        this.mana = setManaPoints;
        return (setManaPoints);
    }

    //Fonctions actions
    @Override
    public int attack(Enemy enemy){
        int life = enemy.getLifePoints();
        int degats = this.getWeaponDamage() - enemy.getArmor();
        mana -= 15;
        return (enemy.setLifePoints(life-degats));
    };


    //Action de heal en plus
    public int heal(Hero ami) {
        int life = ami.getLifePoints();
        int healPoints = this.getWeaponDamage();
        mana -= 5;
        return (ami.setLifePoints(life + healPoints));
    };

}
