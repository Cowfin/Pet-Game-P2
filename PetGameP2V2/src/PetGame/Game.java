/*
Kelvin Wong 18027893
Game class the main controller of the game
 */
package PetGame;

import PetGame.FoodItems.*;
import PetGame.ToyItems.*;

public class Game {

    private Player player;
    private Pet pet;

    public Food apple = new Apple();
    public Food steak = new Steak();
    public Food spinach = new Spinach();
    public Food honey = new Honey();

    public Toys ball = new Ball();
    public Toys doll = new Doll();
    public Toys frisbee = new Frisbee();
    public Toys featherToy = new FeatherToy();

    /**
     * Main game controller
     */
    public Game() {

    }

    public void foodItems(String string) {

    }

    /**
     * Checks if the pet is still alive if not then end the game
     */
    public void checkPet() {
        if (!this.player.pet.getIsAlive()) {

        }
    }

    /*
    * Method to save the game
     */
    public void saveGame() {
        SaveFile sf = new SaveFile();
        sf.save(this.player);
    }

    // Will bring out all food items and a description
    public boolean feedPet(String food) {
        switch (food) {
            case ("apple"): {
                if ((this.player.getMoney() - apple.getCost()) >= 0) {
                    player.feed(apple);
                    return true;
                } else {
                    return false;
                }
            }
            case ("honey"): {
                if ((this.player.getMoney() - honey.getCost()) >= 0) {
                    player.feed(honey);
                    return true;
                } else {
                    return false;
                }
            }
            case ("spinach"): {
                if ((this.player.getMoney() - spinach.getCost()) >= 0) {
                    player.feed(spinach);
                    return true;
                } else {
                    return false;
                }
            }
            case ("steak"): {
                if ((this.player.getMoney() - steak.getCost()) >= 0) {
                    player.feed(steak);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

// Will bring out all toys the pet can play with and a description
    public boolean playPet(String toy) {
        switch (toy) {
            case ("ball"): {
                if ((this.player.getMoney() - ball.getCost()) >= 0) {
                    player.play(ball);
                    return true;
                } else {
                    return false;
                }
            }

            case ("doll"): {
                if ((this.player.getMoney() - doll.getCost()) >= 0) {
                    player.play(doll);
                    return true;
                } else {
                    return false;
                }
            }

            case ("frisbee"): {
                if ((this.player.getMoney() - frisbee.getCost()) >= 0) {
                    player.play(frisbee);
                    return true;
                } else {
                    return false;
                }
            }
            case ("feather toy"): {
                if ((this.player.getMoney() - featherToy.getCost()) >= 0) {
                    player.play(featherToy);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

}
