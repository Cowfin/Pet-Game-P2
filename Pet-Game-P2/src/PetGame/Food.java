/*
Kelvin Wong 18027893
Food class storing details of food items
 */
package PetGame;

public class Food {

    private String name;
    private double price;
    private double hungerGain;
    private double heal;
    private String description;

    /**
     *
     * @param name the name of food of food item
     * @param price the price of food item
     * @param hungerGain the amount of hunger gained when consumed
     * @param heal the amount of health gained when consumed
     * @param description description of the food item
     */
    public Food(String name, double price, double hungerGain, double heal, String description) {
        this.name = name;
        this.price = price;
        this.hungerGain = hungerGain;
        this.heal = heal;
        this.description = description;
    }

    @Override
    public String toString() {
        return ("Food Name: " + this.name + " Price: " + this.price + " Fills: " + this.hungerGain + " Heals: " + this.heal /*+ "Description: " + this.description*/);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price
     */
    public double getCost() {
        return price;
    }

    /**
     * @return the hungerGain
     */
    public double getHunger() {
        return hungerGain;
    }

    /**
     * @return the heal amount
     */
    public double getHeal() {
        return heal;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

}
