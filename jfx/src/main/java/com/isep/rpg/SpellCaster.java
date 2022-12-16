package com.isep.rpg;

abstract public class SpellCaster extends Hero {
    //Attributs

    //Constructeur
    public SpellCaster(String name, String classe) {
        super(name, classe);
    }

    //Fonctions actions
    public abstract int attack(Enemy enemy);
}
