/*
Kelvin Wong 18027893
Player class storing details of the player
 */
package PetGame;

import java.util.Scanner;

public class Player {

    private String username;
    private double money;
    protected Pet pet;

    // New player and pet created
    public Player(String name) {
        this.username = name;
        this.money = 100;
        this.pet = newPet();
    }

    // Load player with pet
    public Player(String name, double money, Pet pet) {
        this.username = name;
        this.money = money;
        this.pet = pet;
    }

    /**
     * This method will ask the user to input values for the pet.
     *
     * @return the new pet created
     */
    public static Pet newPet() {
        Scanner scan = new Scanner(System.in);

        String name = "";
        Boolean confirm = true;
        Boolean confirm2 = true;

        while (confirm) {
            System.out.println("Enter a pet name: ");
            scan.next();
            name = scan.nextLine();
            while (confirm2) {
                System.out.println("Pet name: " + name + "\nConfirm? (Y/N)");
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
        Pet pet = new Pet(name);
        return pet;
    }

    // Will go through one game tick every action cycle
    public void gametick() {
        pet.gametick();
        this.money += 1;
    }

    //Feed the pet with specified food and decrese total money
    public void feed(Food food) {
        this.pet.Feed(food);
        this.money -= food.getCost();
    }

    //Play with the pet with specified toy and decrese total money
    public void play(Toys toy) {
        this.pet.Play(toy);
        this.money -= toy.getCost();
    }

    /**
     * @return the pet
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * @return the money
     */
    public double getMoney() {
        return money;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

}
