package com.isep.maingame;

import com.isep.rpg.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Combat {

    private int numCombat;

    public Combat(int numCombat){
        this.numCombat=numCombat;
    }

    public static void playCombat() {
    }

    //Création des ennemis pour le combat
    public Enemy[] createEnemiesFight(int nbEnnemis){

        Enemy[] ennemis = new Enemy[nbEnnemis];
        int numEnn = 0;
        Random r = new Random();
        int ifBoss = r.nextInt(10);
        if (ifBoss>7){
            ennemis[numEnn] = new Boss(100,5,5,"Boss");
            numEnn++;
        }
        while(numEnn<nbEnnemis){
            String name = "Monstre"+numEnn;
            ennemis[numEnn] = new BasicEnemy(30,2,2,name);
            numEnn++;
        }
        return ennemis;
    }


    //Liste avec ordre aléatoire des actions des héros et des ennemis
    public Fighter[] orderOfAttack(Hero[] joueurs,Enemy[] ennemis){
        ArrayList fightersIni = new ArrayList();
        fightersIni.addAll(List.of(joueurs));
        fightersIni.addAll(List.of(ennemis));

        int nbFighters = fightersIni.size();;
        Fighter[] fighters = new Fighter[nbFighters];
        int numFighter = 0;

        while (nbFighters != 0) {
            Random r = new Random();
            int n = r.nextInt(nbFighters);
            fighters[numFighter] = (Fighter) fightersIni.get(n);
            numFighter=numFighter+1;
            fightersIni.remove(n);
            nbFighters--;
        }

        return fighters;
    }


    //Action de combat d'un ennemi

    public int actionCombatEnemy(Fighter fighter, int nbHeros, Hero[] heros){
        //Déterminant pour le numéro des actions que veut faire un héros
        int i = 1;

        //Si c'est un ennemi qui attaque (BasicEnemy ou Boss c'est pareil)
        if (fighter instanceof Enemy) {
            //Déterminer quel héros sera attaqué
            Random r = new Random();
            int n = r.nextInt(nbHeros);

            //Attaquer le héros
            System.out.println("");
            System.out.println(fighter.getName()+" attaque "+heros[n].getName());
            return(((Enemy) fighter).attack((Hero) heros[n]));
        }else{
            return 0;
        }
    }



    //Si action de combat d'un warrior
    public int actionCombatWarrior(Fighter fighter, Enemy[] enemies, int nbEnemies) {
        //Pour savoir si l'action est finie
        int endAction = 0;

        //Actions d'un Warrior
        while (endAction == 0) {
            //Définir l'action
            System.out.println("1.Obtenir des informations sur soi (ne coute pas d'action)");
            System.out.println("2.Consommer une potion ou un lembas");
            System.out.println("3.Attaquer un ennemi");
            if (((Warrior) fighter).getCharge() >= 5) {
                System.out.println("4.Attaque speciale : Charge de la bete");
            }
            Scanner enterAction = new Scanner(System.in);
            int action = enterAction.nextInt();

            //Faire l'action
            if (action == 1) {
                System.out.println("Vos statistiques : ");
                System.out.println("Nom : " + fighter.getName());
                System.out.println("Classe : " + ((Warrior) fighter).getClasse());
                System.out.println("Niveau : " + ((Warrior) fighter).getLevel());
                System.out.println("Points de vie : " + fighter.getLifePoints());
                System.out.println("Degats d'arme : " + ((Warrior) fighter).getWeaponDamage());
                System.out.println("Armure : " + ((Warrior) fighter).getArmor());
                System.out.println("Charges : " + ((Warrior) fighter).getCharge());
                System.out.println("Potions : " + ((Warrior) fighter).getNumberPotion());
                System.out.println("Lembas : " + ((Warrior) fighter).getNumberFood());

            }else if (action==2){
                System.out.println("1.Consommer une potion (+30 mana)");
                System.out.println("2.Consommer un lembas (+20 PV)");
                Scanner consumeAction = new Scanner(System.in);
                int consumption = consumeAction.nextInt();
                if(consumption==1 && ((Warrior) fighter).getNumberPotion()>0){
                    ((Warrior) fighter).consumePotion();
                }else if(consumption==2 && ((Warrior) fighter).getNumberFood()>0){
                    ((Warrior) fighter).consumeFood();
                }
                endAction=1;

            } else if (action == 3) {
                System.out.println("Quel ennemi voulez-vous attaquer ?");
                for (int j = 0; j < nbEnemies; j++) {
                    System.out.println(j + ". " + enemies[j].getName() + " (" + enemies[j].getLifePoints() + " points)");
                }
                Scanner enterEnemyTarget = new Scanner(System.in);
                int enemyTarget = enterEnemyTarget.nextInt();
                ((Warrior) fighter).attack(enemies[enemyTarget]);
                endAction=1;

            } else if (action == 4 && ((Warrior) fighter).getCharge()>=5){
                System.out.println("Quel ennemi voulez-vous attaquer ?");
                for (int j = 0; j < nbEnemies; j++) {
                    System.out.println(j + ". " + enemies[j].getName() + " (" + enemies[j].getLifePoints() + " points)");
                }
                Scanner enterEnemyTarget = new Scanner(System.in);
                int enemyTarget = enterEnemyTarget.nextInt();
                ((Warrior) fighter).chargeDuTaureau(enemies[enemyTarget]);
                endAction=1;
            } else {
                System.out.println("Erreur");
            }
        }
        return 1;
    }


    //Si action de combat d'un hunter
    public int actionCombatHunter(Fighter fighter, Enemy[] enemies, int nbEnemies) {
        //Pour savoir si l'action est finie
        int endAction = 0;

        //Actions d'un Hunter
        while (endAction == 0) {
            //Définir l'action
            System.out.println("1.Statistiques (ne coute pas d'action)");
            System.out.println("2.Consommer une potion ou un lembas");
            if (((Hunter) fighter).getArrows() >= 0) {
                System.out.println("3.Attaquer un ennemi");
            }
            System.out.println("4. Ramasser des fleches");
            Scanner enterAction = new Scanner(System.in);
            int action = enterAction.nextInt();

            //Faire l'action
            if (action == 1) {
                System.out.println("Vos statistiques : ");
                System.out.println("Nom : " + fighter.getName());
                System.out.println("Classe : " + ((Hunter) fighter).getClasse());
                System.out.println("Niveau : " + ((Hunter) fighter).getLevel());
                System.out.println("Points de vie : " + fighter.getLifePoints());
                System.out.println("Degats d'arme : " + ((Hunter) fighter).getWeaponDamage());
                System.out.println("Armure : " + ((Hunter) fighter).getArmor());
                System.out.println("Flèches : " + ((Hunter) fighter).getArrows());
                System.out.println("Potions : " + ((Hunter) fighter).getNumberPotion());
                System.out.println("Lembas : " + ((Hunter) fighter).getNumberFood());

            }else if (action==2){
                System.out.println("1.Consommer une potion (+30 mana)");
                System.out.println("2.Consommer un lembas (+20 PV)");
                Scanner consumeAction = new Scanner(System.in);
                int consumption = consumeAction.nextInt();
                if(consumption==1 && ((Hunter) fighter).getNumberPotion()>0){
                    ((Hunter) fighter).consumePotion();
                }else if(consumption==2 && ((Hunter) fighter).getNumberFood()>0){
                    ((Hunter) fighter).consumeFood();
                }
                endAction=1;

            } else if (action == 3 && ((Hunter) fighter).getArrows()>0) {
                System.out.println("Quel ennemi voulez-vous attaquer ?");
                for (int j = 0; j < nbEnemies; j++) {
                    System.out.println(j + ". " + enemies[j].getName() + " (" + enemies[j].getLifePoints() + " points)");
                }
                Scanner enterEnemyTarget = new Scanner(System.in);
                int enemyTarget = enterEnemyTarget.nextInt();
                ((Hunter) fighter).attack(enemies[enemyTarget]);
                endAction=1;

            } else if (action == 4){
                ((Hunter) fighter).recupArrow();
                endAction=1;

            } else {
                System.out.println("Erreur");
            }
        }
        return 1;
    }


    //Si action de combat d'un Mage
    public int actionCombatMage(Fighter fighter, Enemy[] enemies, int nbEnemies) {
        //Pour savoir si l'action est finie
        int endAction = 0;

        //Actions d'un Mage
        while (endAction == 0) {
            //Définir l'action
            System.out.println("1.Statistiques (ne coute pas d'action)");
            System.out.println("2.Consommer une potion ou un lembas");
            if (((Mage) fighter).getMana() >= 5) {
                System.out.println("3.Attaquer un ennemi (Cout : 5 mana)");
            }
            if (((Mage) fighter).getMana() >= 30) {
                System.out.println("4.Attaque speciale : Incantation (Cout : 30 mana)");
            }

            Scanner enterAction = new Scanner(System.in);
            int action = enterAction.nextInt();

            //Faire l'action
            if (action == 1) {
                System.out.println("Vos statistiques : ");
                System.out.println("Nom : " + fighter.getName());
                System.out.println("Classe : " + ((Mage) fighter).getClasse());
                System.out.println("Niveau : " + ((Mage) fighter).getLevel());
                System.out.println("Points de vie : " + fighter.getLifePoints());
                System.out.println("Points de Mana : " + ((Mage) fighter).getMana());
                System.out.println("Dégâts d'armes : " + ((Mage) fighter).getWeaponDamage());
                System.out.println("Armure : " + ((Mage) fighter).getArmor());
                System.out.println("Potions : " + ((Mage) fighter).getNumberPotion());
                System.out.println("Lembas : " + ((Mage) fighter).getNumberFood());

            }else if (action==2){
                System.out.println("1.Consommer une potion (+30 mana)");
                System.out.println("2.Consommer un lembas (+20 PV)");
                Scanner consumeAction = new Scanner(System.in);
                int consumption = consumeAction.nextInt();
                if(consumption==1 && ((Mage) fighter).getNumberPotion()>0){
                    ((Mage) fighter).consumePotion();
                }else if(consumption==2 && ((Mage) fighter).getNumberFood()>0){
                    ((Mage) fighter).consumeFood();
                }
                endAction=1;

            } else if (action == 3 && ((Mage) fighter).getMana()>=5) {
                System.out.println("Quel ennemi voulez-vous attaquer ?");
                for (int j = 0; j < nbEnemies; j++) {
                    System.out.println(j + ". " + enemies[j].getName() + " (" + enemies[j].getLifePoints() + " points)");
                }
                Scanner enterEnemyTarget = new Scanner(System.in);
                int enemyTarget = enterEnemyTarget.nextInt();
                ((Mage) fighter).attack(enemies[enemyTarget]);
                endAction=1;

            } else if (action == 4 && ((Mage) fighter).getMana()>=30) {
            System.out.println("Quel ennemi voulez-vous attaquer ?");
            for (int j = 0; j < nbEnemies; j++) {
                System.out.println(j + ". " + enemies[j].getName() + " (" + enemies[j].getLifePoints() + " points)");
            }
            Scanner enterEnemyTarget = new Scanner(System.in);
            int enemyTarget = enterEnemyTarget.nextInt();
            ((Mage) fighter).incantation(enemies[enemyTarget]);
            endAction=1;

            } else {
                System.out.println("Erreur");
            }
        }
        return 1;
    }


    //Si action de combat d'un Healer

    public int actionCombatHealer(Fighter fighter, Enemy[] enemies, Hero[] heros, int nbEnemies, int nbHeros) {
        //Pour savoir si l'action est finie
        int endAction = 0;

        //Actions d'un Healer
        while (endAction == 0) {
            //Définir l'action
            System.out.println("1.Statistiques (ne coute pas d'action)");
            System.out.println("2.Consommer une potion ou un lembas");
            if (((Healer) fighter).getMana() >= 5) {
                System.out.println("3.Soigner un heros (Cout : 5 mana)");
            }
            if (((Healer) fighter).getMana() >= 30) {
                System.out.println("4.Attaquer un ennemi (Cout : 15 mana)");
            }

            Scanner enterAction = new Scanner(System.in);
            int action = enterAction.nextInt();

            //Faire l'action
            if (action == 1) {
                System.out.println("Vos statistiques : ");
                System.out.println("Nom : " + fighter.getName());
                System.out.println("Classe : " + ((Healer) fighter).getClasse());
                System.out.println("Niveau : " + ((Healer) fighter).getLevel());
                System.out.println("Points de vie : " + fighter.getLifePoints());
                System.out.println("Points de Mana : " + ((Healer) fighter).getMana());
                System.out.println("Dégâts d'arme : " + ((Healer) fighter).getWeaponDamage());
                System.out.println("Armure : " + ((Healer) fighter).getArmor());
                System.out.println("Potions : " + ((Healer) fighter).getNumberPotion());
                System.out.println("Lembas : " + ((Healer) fighter).getNumberFood());

            }else if (action==2){
                System.out.println("1.Consommer une potion (+30 mana)");
                System.out.println("2.Consommer un lembas (+20 PV)");
                Scanner consumeAction = new Scanner(System.in);
                int consumption = consumeAction.nextInt();
                if(consumption==1 && ((Healer) fighter).getNumberPotion()>0){
                    ((Healer) fighter).consumePotion();
                }else if(consumption==2 && ((Healer) fighter).getNumberFood()>0){
                    ((Healer) fighter).consumeFood();
                }
                endAction=1;

            } else if (action == 3 && ((Healer) fighter).getMana()>=5) {
                System.out.println("Quel heros voulez-vous attaquer ?");
                for (int j = 0; j < nbHeros; j++) {
                    System.out.println(j + ". " + heros[j].getName() + " (" + heros[j].getLifePoints() + " points)");
                }
                Scanner enterHeroTarget = new Scanner(System.in);
                int heroTarget = enterHeroTarget.nextInt();
                ((Healer) fighter).heal(heros[heroTarget]);
                endAction=1;

            } else if (action == 4 && ((Healer) fighter).getMana()>=15) {
                System.out.println("Quel ennemi voulez-vous attaquer ?");
                for (int j = 0; j < nbEnemies; j++) {
                    System.out.println(j + ". " + enemies[j].getName() + " (" + enemies[j].getLifePoints() + " points)");
                }
                Scanner enterEnemyTarget = new Scanner(System.in);
                int enemyTarget = enterEnemyTarget.nextInt();
                ((Healer) fighter).attack(enemies[enemyTarget]);
                endAction=1;

            } else {
                System.out.println("Erreur");
            }
        }
        return 1;
    }

    //Fonctions de fin de combat

    public int scoreManche(int ifBoss,int nbBeginEnnemis){
        int score = nbBeginEnnemis * 10 + ifBoss * 70;
        return(score);
    }


    public int awards(Hero joueur) {
        System.out.println("Bravo pour ce combat ! Que souhaitez-vous recevoir en récompense ? ");
        System.out.println("1.Gagner 2 points de statistiques");
        System.out.println("2.Obtenir un consommable");
        System.out.println("3. Souhaitez-vous quitter le jeu");
        Scanner whatAction = new Scanner(System.in);
        int action = whatAction.nextInt();

        if (action == 1) {
            System.out.println("Quel statistique souhaitez-vous améliorer ? ");
            System.out.println("1.Degats d'arme +2pt");
            System.out.println("2.Armure +2pt");
            System.out.println("3.Degats d'arme +1pt & Armure +1pt");
            Scanner whatStat = new Scanner(System.in);
            int stat = whatStat.nextInt();

            if (stat == 1) {
                joueur.setWeaponDamage(joueur.getWeaponDamage() + 2);
            } else if (stat == 2) {
                joueur.setArmor(joueur.getArmor() + 2);
            } else {
                joueur.setWeaponDamage(joueur.getWeaponDamage() + 1);
                joueur.setArmor(joueur.getArmor() + 1);
            }

        } else if (action == 2) {
            System.out.println("Quel consommable souhaitez-vous obtenir ? ");
            System.out.println("1.Potion (+30 mana)");
            System.out.println("2.Lembas (+20 PV)");
            Scanner whatCons = new Scanner(System.in);
            int cons = whatCons.nextInt();

            if (cons == 1) {
                joueur.addPotion();
            } else {
                joueur.addFood();
            }
        } else if (action == 3) {
            System.exit(3);
        }
        //Et en bonus une augmentation du level
        joueur.setLevel(joueur.getLevel() + 1);
        return joueur.getLevel();

    }


    //Easter Egg - HappyEnd (or not)
    public int happyEnd(Hero[] heros){
        int win=0;
        System.out.println("VOUS TROUVEZ LE SAINT GRAAL ! ");
        System.out.println("Le vieux mage qui etait a l'entree du donjon apparait. Il est en rogne. ");
        System.out.println("Soudain, tout d'un coup, brusquement, Il se transforme en DEMON ! ");
        System.out.println("'VOUS NE LE PRENDREZ PAAAAAAAAS ! '");
        System.out.println("C'est le dernier boss, si vous le battez, vous gagnez. Sinon, vous mourrez ! ");
        System.out.println("Le vieux demon realise le sort RAGE");
        System.out.println("Vous ne pouvez plus qu'attaquer avec vos poings");
        System.out.println("En contrepartie, le demon est paralyse le temps que vous l'attaquiez tous une fois");
        System.out.println("Profitez de ce laps de temps pour le tuer ou il vous tuera tous a coup sur");
        for(int j = 0; j<List.of(heros).size(); j++){
            System.out.println("Quelle action souhaitez-vous faire ? ");
            System.out.println("1.Attaquer avec vos poings ");
            Scanner whatAction = new Scanner(System.in);
            String action = whatAction.nextLine();
            if(action.equalsIgnoreCase("Exorciser 666")){
                win=1;
                return win;
            }else{
                System.out.println("Vous attaquez de toutes vos forces mais ne lui faites aucun dégât...");
            }
        }
        System.out.println("Le demon lance le sort EXTENSION DU TERRITOIRE : ENFERS INFERNAUX");
        System.out.println("Votre ame est aspiree en enfer, vous mourrez. ");
        return win;
    }
}
