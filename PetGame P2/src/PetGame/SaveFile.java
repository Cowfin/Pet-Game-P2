/*
Kelvin Wong 18027893
SaveFile class will save the file to specified file name
 */
package PetGame;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveFile {

    private String fileName;

    // Will ask for file name
    public SaveFile() {
        Scanner scan = new Scanner(System.in);

        fileName = "";
        Boolean confirm = true;
        Boolean confirm2 = true;

        while (confirm) {
            System.out.println("Enter file name to save to: ");
            fileName = scan.nextLine() + ".txt";
            while (confirm2) {
                System.out.println("File name: " + fileName + "\nConfirm? (Y/N)");
                char conf = scan.next().toUpperCase().charAt(0);
                if (conf == 'Y') {
                    confirm = false;
                    confirm2 = false;
                } else if (conf == 'N') {
                    confirm2 = false;
                }
            }
            confirm2 = true;
        }
    }

    /**
     * Saves the game to specified file
     *
     * @param player the game
     */
    public void save(Player player) {
        try {
            FileWriter fw = new FileWriter(fileName);
            try ( PrintWriter file = new PrintWriter(fw)) {
                file.println("Player " + player.getUsername());
                file.println("Money " + player.getMoney());
                file.println("Name " + player.pet.getName());
                file.println("Age " + player.pet.getAge());
                file.println("Health " + player.pet.getHealthLevel());
                file.println("Hunger " + player.pet.getHungerLevel());
                file.println("Happiness " + player.pet.getHappinessLevel());
                file.println("Energy " + player.pet.getEnergyLevel());
                file.println("HungerDecay " + player.pet.getHungerDecay());
                file.println("HappinessDecay " + player.pet.getHappinessDecay());
                file.println("EnergyDecay " + player.pet.getEnergyDecay());
                file.println("Alive " + player.pet.getIsAlive());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
