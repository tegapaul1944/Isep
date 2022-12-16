package com.isep.rpg;

public class Mage  extends SpellCaster {

    //Attributs
    int mana;

    //Constructeur
    public Mage(String name,String classe){
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
        if (this.mana < 5) {
            return 0;
        }else {
            int life = enemy.getLifePoints();
            int degats = this.getWeaponDamage() - enemy.getArmor();
            this.mana -= 5;
            return (enemy.setLifePoints(life - degats));
        }
    };

    //Action spéciale d'incantation qui quadruple la puissance du sort mais fait perdre bcp de mana
    public int incantation(Enemy enemy) {
        if(this.mana < 30){
            return (attack(enemy));
        }else {
            int life = enemy.getLifePoints();
            int degats = this.getWeaponDamage() * 4 - enemy.getArmor();
            this.mana -= 30;
            return (enemy.setLifePoints(life - degats));
        }
    }


}
