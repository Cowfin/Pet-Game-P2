/*
Kelvin Wong 18027893
LoadFile class will load game from specified file.
 */
package PetGame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadFile {

    private Scanner scan;

    public LoadFile() {

    }

    /**
     * Will load all details from specified text file
     *
     * @param file file name
     * @return Loaded player
     */
    public Player load(String file) {
        FileInputStream fin;

        String player = "", name = "";
        double money = -1, age = -1, health = -1, hunger = -1, happiness = -1,
                energy = -1, hungerDecay = -1, happinessDecay = -1, energyDecay = -1;
        boolean isAlive = false;

        try {
            fin = new FileInputStream(file);
            scan = new Scanner(fin);

            while (scan.hasNextLine()) {
                String line = scan.nextLine().toUpperCase();
                String[] str = line.split("\\s+");
                switch (str[0]) {
                    case ("PLAYER"):
                        for (int i = 1; i < str.length; i++) {
                            player += str[i] + " ";
                        }
                        break;
                    case ("MONEY"):
                        money = Double.parseDouble(str[1]);
                        break;
                    case ("NAME"):
                        for (int i = 1; i < str.length; i++) {
                            name += str[i] + " ";
                        }
                        break;
                    case ("AGE"):
                        age = Double.parseDouble(str[1]);
                        break;
                    case ("HEALTH"):
                        health = Double.parseDouble(str[1]);
                        break;
                    case ("HUNGER"):
                        hunger = Double.parseDouble(str[1]);
                        break;
                    case ("HAPPINESS"):
                        happiness = Double.parseDouble(str[1]);
                        break;
                    case ("ENERGY"):
                        energy = Double.parseDouble(str[1]);
                        break;
                    case ("HUNGERDECAY"):
                        hungerDecay = Double.parseDouble(str[1]);
                        break;
                    case ("HAPPINESSDECAY"):
                        happinessDecay = Double.parseDouble(str[1]);
                        break;
                    case ("ENERGYDECAY"):
                        energyDecay = Double.parseDouble(str[1]);
                        break;
                    case ("ALIVE"):
                        isAlive = Boolean.parseBoolean(str[1]);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading file");
        }
        if (player.equals("") || name.equals("") || money == -1 || age == -1 || hunger == -1 || energy == -1
                || health == -1 || happiness == -1 || hungerDecay == -1 || energyDecay == -1 || happinessDecay == -1) {
            return null;
        } else {
            Pet pet = new Pet(name, hungerDecay, energyDecay, happinessDecay, age, hunger, energy, health, happiness, isAlive);
            Player pl = new Player(player, money, pet);
            return pl;
        }
    }
}
