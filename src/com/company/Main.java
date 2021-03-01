package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int bossHealth = 8000;
    public static int bossDamage = 90;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {1200, 1020, 930};
    public static int[] heroesDamage = {180, 158, 90};
    public static String[] heroesClass = {"Warrior", "Wizard", "Necromancer"};
    public static String[] heroesAttackType = {"Physical", "Magical", "Soul magic"};
    public static int roundNumber = 0;


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }

    }

    public static void round() {
        roundNumber++;
        System.out.println("\n    ROUND: " + roundNumber + "\n     FIGHT!");
        defenseType();
        //System.out.println("Bosses change:" + bossChoice());
        if (bossHealth > 0) {
            bossAttacks();
        }
        heroesHits();
        printStatistics();
    }
    /*public static void bossChoice () {
     Random random = new Random();
     int randomChoice = random.nextInt(heroesClass.length);
    }*/


    public static String heroesChanges() {
        Scanner str = new Scanner(System.in);
        System.out.println(str.nextLine());
        return str.nextLine();

    }

    public static void printStatistics() {
        System.out.println("____________________________________");
        System.out.println("Boss health: " + bossHealth + " [DPS: " + bossDamage + " ]\n");
        for (int i = 0; i < heroesClass.length; i++) {
            System.out.println(heroesClass[i] + " health: " + heroesHealth[i]
                    + " [DPS: " + heroesDamage[i] + " ]");
        }
    }

    public static void defenseType() {
        Random random = new Random();
        int randomDefense = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomDefense];
        System.out.println("Boss defense type:" + bossDefenceType);
    }

    public static void bossAttacks() {
        for (int i = 0; i < heroesHealth.length; i++) {
            //int bossChoice = 0;
            if (heroesHealth[i] < 0) {
                heroesHealth[i] = 0;
            }
            if (heroesHealth[i] > 0) {
                heroesHealth[i] = heroesHealth[i] - bossDamage;
            } else if (heroesHealth[0] < 0) {
                heroesHealth[0] = 0;
            }

        }
    }

    public static void heroesHits() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(5) + 1; //1,2,3,4,5,6,7,8,9,10,11
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }
    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("____________________________________");
            System.out.println("    You win.");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("____________________________________");
            System.out.println("     DEFEAT");
        }
        return allHeroesDead;
    }

}


