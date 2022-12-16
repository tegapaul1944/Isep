package com.isep.rpg;
import java.util.ArrayList;
import java.util.List;

abstract public class Hero extends Fighter {
    //attributs

    private String classe;
    private int armor; //armor = points de défense
    private int weaponDamage;
    private int level;
    List<Potion> potion;
    List<Food> lembas;

    //Constructeur
    public Hero(String name, String classe){
        super(name,100); //LifePoints = 100
        this.classe = classe;
        this.armor=1;
        this.weaponDamage=5;
        this.level=1;
        this.potion = new ArrayList<>();
        this.lembas = new ArrayList<>();
    }



    public String getClasse(){return classe;}
    public int getArmor(){return armor;}
    public int getWeaponDamage(){return weaponDamage;}
    public int getLevel(){return level;}

    public List<Potion> getPotion(){return potion;}

    public int getNumberPotion(){return potion.size();}

    public List<Food> getFood(){return lembas;}

    public int getNumberFood(){return lembas.size();}

    //Fonctions influancant les données

    public int setArmor(int armorPoints){
        this.armor = armorPoints;
        return armorPoints;
    }
    public int setWeaponDamage(int weaponPoints){
        this.weaponDamage = weaponPoints;
        return weaponPoints;
    }
    public int setLevel(int level){
        this.level = level;
        return level;
    }

    public int setLifePoints;


    //Fonctions inventaire

    //Ajout de potions et de lembas à l'inventaire

    public List<Potion> addPotion(){
        int addMana=30;
        //Ajouter la potion à l'inventaire
        this.potion.add(new Potion(addMana));
        return potion;
    }

    public List<Food> addFood(){
        int addLife=20;
        //Ajouter la potion à l'inventaire
        this.lembas.add(new Food(addLife));
        return this.lembas;
    }

    //Consommation de potions et de lembas
    public List<Potion> consumePotion(){
        setLifePoints(getLifePoints()+potion.get(0).getAddMana());
        this.potion.remove(0);
        return this.potion;
    }

    public List<Food> consumeFood(){
        setLifePoints(getLifePoints()+lembas.get(0).getAddPV());
        this.lembas.remove(0);
        return this.lembas;
    }


    //Fonctions actions
    public abstract int attack(Enemy enemy);
}








