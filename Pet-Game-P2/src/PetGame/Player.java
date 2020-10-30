/*
Kelvin Wong 18027893
Player class storing details of the player
 */
package PetGame;

public class Player {

    private double money;
    protected Pet pet;

    // Load player with pet
    public Player(double money, Pet pet) {
        this.money = money;
        this.pet = pet;
    }

    // Will go through one game tick every action cycle
    public void gametick() {
        pet.gametick();
        pet.Rest();
        this.money += 2;
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

}
