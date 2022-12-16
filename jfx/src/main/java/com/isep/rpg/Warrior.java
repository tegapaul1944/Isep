package com.isep.rpg;

public class Warrior extends Hero {
    //Attributs
    int charge;

    //Constructeur
    public Warrior(String name,String classe){
        super(name,classe);
        this.charge=0;
    }

    //Fonction récup données
    public int getCharge(){return charge;}
    public void setCharge(int charge){this.charge=charge;}


    //Fonctions actions
    @Override
    public int attack(Enemy enemy){
        int life = enemy.getLifePoints();
        int degats = this.getWeaponDamage() - enemy.getArmor();
        this.charge += 1;
        return (enemy.setLifePoints(life-degats));
    };

    //Au bout de 5 charges simples, charge du taureau avec atk x3
    //Le warrior peut accumuler ses charges pour plus tard
    public int chargeDuTaureau(Enemy enemy){
        if (this.charge<5){
            return (attack(enemy));
        }else{
            int life = enemy.getLifePoints();
            int degats = this.getWeaponDamage() * 3 - enemy.getArmor();
            this.charge -= 5;
            return (enemy.setLifePoints(life - degats));
        }
    }





}