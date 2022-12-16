package com.isep.maingame;

import com.isep.rpg.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
        game();
    }

    public static void game() {
        //SI TOUS LES HEROS MEURENT, FIN DE LA PARTIE
        //Introduction
        System.out.println("LINK START ! Bienvenue sur Mini RPG Lite 300. ");
        System.out.println("* Vous arrivez devant une immense tour... *");
        System.out.println("* Elle semble gardée par une vieux mage... *");
        System.out.println("* Celui-ci décide de s'approche de vous... *");
        System.out.println("<<Oye oye braves gens ! ");
        System.out.println("Bienvenue à la citadelle d'Avalon ! ");
        System.out.println("En haut de la tour se trouve le Graal, qui permettra de realiser tous vos voeux...");
        System.out.println("Seuls les plus braves d'entre vous pourront atteindre le sommet ! >>");


        //Choisir le nombre de héros
        System.out.println("Ma vue se faiblit... Combien etes-vous jeunes gens ?");
        Scanner scanner = new Scanner(System.in);
        boolean choice = false;
        int nbHeros = 0;
        while(!choice){
            try {
                nbHeros = scanner.nextInt();
                choice = true;


            }catch (Exception e){
                System.out.println("Entree mauvaise recommence");
                scanner.nextLine();

            }
        }

        Hero[] joueurs = new Hero[nbHeros];

        System.out.println("Il y a " + joueurs.length + " heros");

        //Création des héros avec la classe
        for (int nb=0 ; nb < nbHeros ; nb++) {
            System.out.println("Comment s'appelle le " + (nb+1) + "e heros ?");
            Scanner scanner1 = new Scanner(System.in);
            String nom = scanner1.nextLine();

            System.out.println("Bonjour " + nom);

            //Créer le perso avec sa classe

            System.out.println("Et quelle est sa classe ? Hunter, Mage, Warrior ou Healer ? ");
            Scanner scanner2 = new Scanner(System.in);
            String classe = scanner2.nextLine();

            String classe1="hunter";
            String classe2="mage";
            String classe3="healer";
            String classe4="warrior";

            int verifClasse = 0;
            while (verifClasse == 0) {
                if (classe.equalsIgnoreCase(classe1)) {
                    joueurs[nb] = new Hunter(nom, classe1);
                    verifClasse = 1;
                } else if (classe.equalsIgnoreCase(classe2)) {
                    joueurs[nb] = new Mage(nom, classe2);
                    verifClasse = 1;
                } else if (classe.equalsIgnoreCase(classe3)) {
                    joueurs[nb] = new Healer(nom, classe3);
                    verifClasse = 1;
                } else if (classe.equalsIgnoreCase(classe4)) {
                    joueurs[nb] = new Warrior(nom, classe4);
                    verifClasse = 1;
                } else {
                    System.out.println("Ceci n'est pas une classe valide");
                    System.out.println("Et quelle est sa classe ? Hunter, Mage, Warrior ou Healer ? ");
                    classe = scanner2.nextLine();
                }
            }
        }

        System.out.println("Nos heros sont : ");
        for (int nb=0; nb<nbHeros; nb++){
            System.out.println("-" + joueurs[nb].getName() + ", " + joueurs[nb].getClasse());
        }



        //Partie
        int numManche = 0;
        int score = 0;
        int nbTotalEnnemis=0;
        int nbTotalBoss=0;
        //La partie se termine quand tout le monde est mort
        while (nbHeros > 0) {

            //Comptage des morts
            ArrayList deadHeros = new ArrayList();

            numManche++;
            //Fonction de combat
            //Lancer le combat :
            Combat manche = new Combat(numManche);
            System.out.println(numManche+"e combat ! ");

            //Avant combat :
            //Creation des ennemis, dont 1 boss aléatoire
            int nbEnnemis = nbHeros;
            Enemy[] ennemis = manche.createEnemiesFight(nbEnnemis);
            System.out.println("Des ennemis apparaissent au nombre de "+nbEnnemis);

            //Ordre d'action des héros/ennemis aléatoire
            Fighter[] fighters = manche.orderOfAttack(joueurs,ennemis);
            System.out.print("L'ordre d'action sera le suivant : ");
            for (int i = 0; i < List.of(fighters).size(); i++){
                System.out.print(fighters[i].getName() + ", ");
            }
            System.out.println("");

            //Garder en mémoire s'il y a un boss et le nombre d'ennemis pour augmenter le score de fin de manche
            int ifBoss = 0;
            if(ennemis[0] instanceof Boss){
                ifBoss = 1;
            }
            int nbBeginEnnemis = nbEnnemis;

            while (nbEnnemis>0 && nbHeros>0 ){
                for (int numFighter = 0; numFighter < List.of(fighters).size(); numFighter++) {
                    if (fighters[numFighter] instanceof Enemy) {
                        manche.actionCombatEnemy(fighters[numFighter], nbHeros, joueurs);
                    } else if (fighters[numFighter] instanceof Warrior) {
                        manche.actionCombatWarrior(fighters[numFighter], ennemis, nbEnnemis);
                    } else if (fighters[numFighter] instanceof Hunter) {
                        manche.actionCombatHunter(fighters[numFighter], ennemis, nbEnnemis);
                    } else if (fighters[numFighter] instanceof Mage) {
                        manche.actionCombatMage(fighters[numFighter], ennemis, nbEnnemis);
                    } else if (fighters[numFighter] instanceof Healer) {
                        manche.actionCombatHealer(fighters[numFighter], ennemis, joueurs, nbEnnemis, nbHeros);
                    }

                    //Verification des morts et des vivants
                    //Chez les tous les combattants
                    for (int fighter1 = 0; fighter1 < List.of(fighters).size(); fighter1++){
                        if(fighters[fighter1].getLifePoints()<=0){
                            Fighter[] fightersAlive = new Fighter[List.of(fighters).size()-1];
                            int num = 0;
                            for(int i = 0; i<List.of(fighters).size(); i++) {
                                if (i != fighter1) {
                                    fightersAlive[num]=fighters[i];
                                    num++;
                                }
                            }
                            fighters=fightersAlive;
                            break;
                        }
                    }
                    //Chez tous les joueurs/héros
                    for (int joueur1 = 0; joueur1 < List.of(joueurs).size(); joueur1++){
                        if(joueurs[joueur1].getLifePoints()<=0){
                            Hero[] joueursAlive = new Hero[List.of(joueurs).size()-1];
                            int num = 0;
                            for(int i = 0; i<List.of(joueurs).size(); i++) {
                                if (i != joueur1) {
                                    joueursAlive[num]=joueurs[i];
                                    num++;
                                }else{
                                    deadHeros.add(joueurs[i]);
                                    System.out.println("Adieu "+joueurs[i].getName()+", puisse ton esprit rejoindre le Royaume des Cieux...");
                                }
                            }
                            joueurs=joueursAlive;
                            nbHeros--;
                            break;
                        }
                    }
                    //Chez tous les ennemis
                    for (int enemy1 = 0; enemy1 < List.of(ennemis).size(); enemy1++){
                        if(ennemis[enemy1].getLifePoints()<=0){
                            Enemy[] ennemisAlive = new Enemy[List.of(ennemis).size()-1];
                            int num = 0;
                            for(int i = 0; i<List.of(ennemis).size(); i++) {
                                if (i != enemy1) {
                                    ennemisAlive[num]=ennemis[i];
                                    num++;
                                }else{
                                    System.out.println("Et encore un monstre de tuer, un ! ");
                                }
                            }
                            ennemis=ennemisAlive;
                            nbEnnemis--;
                            break;
                        }
                    }
                }

                //Afficher les combattants avec leurs points de vie
                System.out.println(" ");
                for (int nb=0; nb<nbHeros; nb++){
                    System.out.println("-" + joueurs[nb].getName() + " : " + joueurs[nb].getLifePoints()+" pv");
                }
                for (int nb=0; nb<nbEnnemis; nb++){
                    System.out.println("-" + ennemis[nb].getName() + " : " + ennemis[nb].getLifePoints()+" pv");
                }
                //Fin du round
            }
            //Fin du combat/manche
            System.out.println("Fin du combat ! ");

            if(nbHeros>0) {
                //Récompenses
                //Calcul du score :
                int scoreManche = manche.scoreManche(ifBoss, nbBeginEnnemis);
                score = score + scoreManche;

                for (int i = 0; i < List.of(joueurs).size(); i++) {
                    System.out.println(joueurs[i].getName() + " c'est votre tour. ");
                    manche.awards(joueurs[i]);
                }

                System.out.println("____________________________________________________");
                System.out.println("RECAPITULATIF DE LA MANCHE");
                System.out.println("____________________________________________________");
                String StringIfBoss = "";
                if (ifBoss == 1) {
                    StringIfBoss = ", dont 1 boss";
                }
                System.out.println("Nombre d'ennemis tues : " + nbBeginEnnemis + StringIfBoss);
                System.out.println("");
                System.out.println("Nombre de heros morts au combat : " + deadHeros.size());
                System.out.println("____________________________________________________");
                System.out.println("Score obtenu : " + scoreManche);
                System.out.println("Score total de la partie: " + score);

                //Pour les stats de fin
                nbTotalBoss=nbTotalBoss+ifBoss;
                nbTotalEnnemis=nbTotalEnnemis+nbBeginEnnemis;
            }else{
                System.out.println("GAME OVER");
                nbTotalEnnemis=nbTotalEnnemis+nbBeginEnnemis-nbEnnemis;
                if(ifBoss==1 && ennemis[0] instanceof BasicEnemy){
                    nbTotalBoss++;
                }
            }
            System.out.println("Appuyez sur enter pour continuer");
            Scanner endManche = new Scanner(System.in);
            String nextManche = endManche.nextLine();

            //Easter Egg :
            if(nextManche.equalsIgnoreCase("Ouvrir le ventre d'un monstre avec l'épée Excalibur")){
                int win=manche.happyEnd(joueurs);
                if(win==0){
                    System.out.println("GAME OVER");
                    nbHeros=0;
                }else{
                    System.out.println("Vous avez gagné braves heros et êtes beni par le Saint Graal");
                    System.out.println("FELICITATIONS");
                    nbHeros=0;
                }
            }



        }
        //Fin de la partie
        System.out.println("____________________________________________________");
        System.out.println("RECAPITULATIF DE LA PARTIE");
        System.out.println("____________________________________________________");
        System.out.println("Score final : "+score);
        System.out.println("Nombre de manches jouées : " + numManche);
        System.out.println("____________________________________________________");
        System.out.println("Nombre de monstres combattus : "+nbTotalEnnemis+" dont "+nbTotalBoss+" Boss");
        System.out.println("");

        System.out.println("Appuyez sur enter pour continuer");
        Scanner endPartie = new Scanner(System.in);
        String endEndPartie = endPartie.nextLine();

        System.out.println("Dans vos derniers instants, vous découvrez le Saint Graal dans les tripes du monstre qui vient de vous dévorer...");
        System.out.println("Vous entendez egalement un rire maléfique d'un vieux mage...");
        System.out.println("Malgre cela, puissiez-vous ne pas avoir de regret et aller au Royaume des Cieux... ");

    }





}
//
