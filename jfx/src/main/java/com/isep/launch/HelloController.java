package com.isep.launch;

import com.isep.rpg.BasicEnemy;
import com.isep.rpg.Mage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    Mage joueur = new Mage("Joueur","mage");
    int manche = 1;
    BasicEnemy enemy = new BasicEnemy(20,3,2,"enemy");




    public void initialize(){
        round.setText(String.valueOf(manche));
        majStatsJ();
        majStatsM();
    }


    @FXML
    private Label level;
    @FXML
    private Label levelM;
    @FXML
    private Label life;
    @FXML
    private Label lifeM;
    @FXML
    private Label weaponDamage;
    @FXML
    private Label weaponDamageM;
    @FXML
    private Label armor;
    @FXML
    private Label armorM;
    @FXML
    private Label mana;
    @FXML
    private Label potions;
    @FXML
    private Label lembas;
    @FXML
    private Label round;
    @FXML
    private Label action;
    @FXML
    private Label action1;


    public void majStatsJ() {
        //Stats du joueur
        level.setText(String.valueOf(joueur.getLevel()));
        life.setText(String.valueOf(joueur.getLifePoints()));
        weaponDamage.setText(String.valueOf(joueur.getWeaponDamage()));
        armor.setText(String.valueOf(joueur.getArmor()));
        mana.setText(String.valueOf(joueur.getMana()));
        potions.setText(String.valueOf(joueur.getNumberPotion()));
        lembas.setText(String.valueOf(joueur.getNumberFood()));
    }

    public void majStatsM() {
        //Stats de l'ennemi/monstre
        levelM.setText(String.valueOf(manche));
        lifeM.setText(String.valueOf(enemy.getLifePoints()));
        weaponDamageM.setText(String.valueOf(enemy.getWeaponDamage()));
        armorM.setText(String.valueOf(enemy.getArmor()));
    }

    public void sort(ActionEvent actionEvent) {
        joueur.attack(enemy);
        majStatsM();
        majStatsJ();
        action.setText("Joueur fait l'attaque Boule de Feu contre Monstre. ");
        enemy.attack(joueur);
        majStatsM();
        majStatsJ();
        action1.setText("Monstre fait l'attaque Tranchant contre Joueur. ");
    }

    public void incantation(ActionEvent actionEvent) {
        joueur.incantation(enemy);
        majStatsM();
        majStatsJ();
        action.setText("Joueur fait l'attaque Gel contre Monstre. ");
        enemy.attack(joueur);
        majStatsM();
        majStatsJ();
        action1.setText("Monstre fait l'attaque Tranchant contre Joueur. ");
    }

}