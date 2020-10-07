/*
Kelvin Wong 18027893
Game class the main controller of the game
 */
package PetGame;

import PetGame.FoodItems.*;
import PetGame.ToyItems.*;
import java.util.Scanner;

public class Game implements Runnable {

    private Player player;
    private Scanner scan;
    private Boolean gameAlive;

    /**
     * Main game controller
     */
    public Game() {
        scan = new Scanner(System.in);
        gameAlive = true;
        Boolean checker = true;
        int userIn;

        while (checker) { // Loops until user has made all the correct inputs.
            System.out.println("1. Start new game");
            System.out.println("2. Load game from file");
            System.out.println("3. Exit");
            userIn = scan.nextInt();
            switch (userIn) {
                case (1):
                    this.player = newplayer(); // create a new player
                    checker = false;
                    break;
                case (2):
                    System.out.println("Please enter file name (ie. 'save.txt')"); // load a player from file
                    String fileName = scan.nextLine();
                    fileName = scan.nextLine();
                    LoadFile lf = new LoadFile();
                    this.player = lf.load(fileName);
                    if (this.player != null) {
                        checker = false;
                    }
                    break;
                case (3):
                    gameAlive = false;
                    checker = false;
                    break;
                default:
                    checker = true;
                    break;
            }
        }
    }

    /**
     * Main loop keeping game active
     */
    @Override
    public void run() {
        while (gameAlive) {
            System.out.println(cui());
            this.player.gametick();
            userAction();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + e);
            }
        }
    }

    /**
     * Method to print out the CUI
     *
     * @return the CUI block
     */
    public String cui() {
        String str = "";
        str += (String) ("Money: " + this.player.getMoney());
        str += (String) ("\nPet Name: " + this.player.pet.getName());
        str += (String) ("\nPet Age: " + this.player.pet.getAge());
        str += (String) ("\nPet Health: " + this.player.pet.getHealthLevel());
        str += (String) ("\nPet Hunger: " + this.player.pet.getHungerLevel());
        str += (String) ("\nPet Energy: " + this.player.pet.getEnergyLevel());
        str += (String) ("\nPet Happiness: " + this.player.pet.getHappinessLevel());

        str += (String) ("\nPet Hunger Decay: " + this.player.pet.getHungerDecay());
        str += (String) ("\nPet Energy Decay: " + this.player.pet.getEnergyDecay());
        str += (String) ("\nPet Happiness Decay: " + this.player.pet.getHappinessDecay());

        str += "\nUser Inputs:";
        str += "\n1. Feed Pet \n2. Play with Pet \n3. Rest \n5. Cycle Time \n9. Save and Exit Game \n0. Exit";

        return str;
    }

    /**
     * Checks if the pet is still alive if not then end the game
     */
    public void checkPet() {
        if (!this.player.pet.getIsAlive()) {
            this.gameAlive = false;
            System.out.println(this.player.pet.getName() + " has died at age: " + this.player.pet.getAge());
        }
    }

    /**
     * Method to determine the user input
     */
    public void userAction() {
        scan = new Scanner(System.in);
        int userIn = 0;
        boolean checker = true;
        while (checker) {
            userIn = scan.nextInt();
            switch (userIn) {
                case (1):
                    this.feedPet();
                    checker = false;
                    break;
                case (2):
                    this.playPet();
                    checker = false;
                    break;
                case (3):
                    this.player.pet.Rest();
                    checker = false;
                    break;
                case (5):
                    checker = false;
                    break;
                case (9):
                    saveGame();
                    this.gameAlive = false;
                    checker = false;
                    break;
                case (0):
                    this.gameAlive = false;
                    checker = false;
                    break;
                default:
                    checker = true;
                    break;
            }
        }
    }

    /*
    * Method to save the game
     */
    public void saveGame() {
        SaveFile sf = new SaveFile();
        sf.save(this.player);
    }

    /*
    * Method to fill out new player details
     */
    public Player newplayer() {
        scan = new Scanner(System.in);

        String name = "";
        Boolean confirm = true;
        Boolean confirm2 = true;

        while (confirm) {
            System.out.println("Enter player name: ");
            scan.next();
            name = scan.nextLine();
            while (confirm2) {
                System.out.println("Player name: " + name + "\nConfirm? (Y/N)");
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
        return new Player(name);
    }

    // Will bring out all food items and a description
    synchronized public void feedPet() {
        scan = new Scanner(System.in);
        int userIn;
        Boolean checker = true;

        Food apple = new Apple();
        Food steak = new Steak();
        Food spinach = new Spinach();
        Food honey = new Honey();

        System.out.println("Balance: " + player.getMoney());
        System.out.println("Pick food item: ");
        System.out.println("0. BACK");
        System.out.println("1. " + apple.toString());
        System.out.println("2. " + steak.toString());
        System.out.println("3. " + spinach.toString());
        System.out.println("4. " + honey.toString());

        while (checker) {
            userIn = scan.nextInt();
            if (userIn >= 0 && (userIn <= 4)) {
                switch (userIn) {
                    case (0):
                        checker = false;
                        break;
                    case (1): {
                        if ((this.player.getMoney() - apple.getCost()) >= 0) {
                            player.feed(apple);
                            checker = false;
                        } else {
                            System.out.println("Not enough money :(");
                        }
                    }
                    break;
                    case (2): {
                        if ((this.player.getMoney() - steak.getCost()) >= 0) {
                            player.feed(steak);
                            checker = false;
                        } else {
                            System.out.println("Not enough money :(");
                        }
                    }
                    break;
                    case (3): {
                        if ((this.player.getMoney() - spinach.getCost()) >= 0) {
                            player.feed(spinach);
                            checker = false;
                        } else {
                            System.out.println("Not enough money :(");
                        }
                    }
                    break;
                    case (4): {
                        if ((this.player.getMoney() - honey.getCost()) >= 0) {
                            player.feed(honey);
                            checker = false;
                        } else {
                            System.out.println("Not enough money :(");
                        }
                    }
                    break;
                }
            } else {
                System.out.println("Please enter a valid input");
            }
        }
    }

    // Will bring out all toys the pet can play with and a description
    synchronized public void playPet() {
        scan = new Scanner(System.in);
        int userIn;
        Boolean checker = true;

        Toys ball = new Ball();
        Toys doll = new Doll();
        Toys frisbee = new Frisbee();
        Toys featherToy = new FeatherToy();

        System.out.println("Balance: " + player.getMoney());
        System.out.println("Pick food item: ");
        System.out.println("0. BACK");
        System.out.println("1. " + ball.toString());
        System.out.println("2. " + doll.toString());
        System.out.println("3. " + frisbee.toString());
        System.out.println("4. " + featherToy.toString());

        while (checker) {
            userIn = scan.nextInt();
            if (userIn >= 0 && (userIn <= 4)) {
                switch (userIn) {
                    case (0):
                        checker = false;
                        break;
                    case (1): {
                        if ((this.player.getMoney() - ball.getCost()) >= 0) {
                            player.play(ball);
                            checker = false;
                        } else {
                            System.out.println("Not enough money :(");
                        }
                    }
                    break;
                    case (2): {
                        if ((this.player.getMoney() - doll.getCost()) >= 0) {
                            player.play(doll);
                            checker = false;
                        } else {
                            System.out.println("Not enough money :(");
                        }
                    }
                    break;
                    case (3): {
                        if ((this.player.getMoney() - frisbee.getCost()) >= 0) {
                            player.play(frisbee);
                            checker = false;
                        } else {
                            System.out.println("Not enough money :(");
                        }
                    }
                    break;
                    case (4): {
                        if ((this.player.getMoney() - featherToy.getCost()) >= 0) {
                            player.play(featherToy);
                            checker = false;
                        } else {
                            System.out.println("Not enough money :(");
                        }
                    }
                    break;
                }
            } else {
                System.out.println("Please enter a valid input");
            }
        }
    }

}
