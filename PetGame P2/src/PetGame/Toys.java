package PetGame;

public class Toys {

    private String name;
    private double price;
    private double happinessGain;
    private double energyConsumption;
    private String description;

    /**
     *
     * @param name
     * @param price
     * @param happinessGain
     * @param energyConsumption
     * @param description
     */
    public Toys(String name, double price, double happinessGain, double energyConsumption, String description) {
        this.name = name;
        this.price = price;
        this.happinessGain = happinessGain;
        this.energyConsumption = energyConsumption;
        this.description = description;
    }

    @Override
    public String toString() {
        return ("Toy Name: " + this.name + " Price: " + this.price + " Happiness Gain: " + this.happinessGain + " Energy Consumption: " + this.energyConsumption + " Description: " + this.description);
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
     * @return the happinessGain
     */
    public double getHappinessGain() {
        return happinessGain;
    }

    /**
     * @return the energyConsumption
     */
    public double getEnergyConsumption() {
        return energyConsumption;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

}
