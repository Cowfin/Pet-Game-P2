/*
Kelvin Wong 18027893
Pet class storing details of the pet
 */
package PetGame;

public class Pet {

    private String name;
    private double hungerDecay;
    private double energyDecay;
    private double happinessDecay;
    private double age;

    private double hungerLevel;
    private double energyLevel;
    private double healthLevel;
    private double happinessLevel;
    private boolean isAlive;

    // Sets values for a new pet
    public Pet(String name) {
        this.name = name;
        this.hungerLevel = 100;
        this.energyLevel = 100;
        this.healthLevel = 100;
        this.happinessLevel = 100;
        this.age = 0;
        this.energyDecay = 0.25;
        this.happinessDecay = 0.15;
        this.hungerDecay = 0.5;
        this.isAlive = true;
    }

    // Loads values for pet
    public Pet(String name, double age, double healthLevel, double hungerLevel, double happinessLevel, double energyLevel, double hungerDecay, double happinessDecay, double energyDecay, boolean isAlive) {
        this.name = name;
        this.energyDecay = energyDecay;
        this.hungerDecay = hungerDecay;
        this.happinessDecay = happinessDecay;
        this.age = age;
        this.hungerLevel = hungerLevel;
        this.energyLevel = energyLevel;
        this.healthLevel = healthLevel;
        this.happinessLevel = happinessLevel;
        this.isAlive = isAlive;
    }

    //Feeds the pet specified food item
    public void Feed(Food food) {
        this.healthLevel += food.getHeal();
        this.hungerLevel += food.getHunger();
        if (this.hungerLevel >= 100) {
            this.hungerLevel = 100;
        }
        if (this.healthLevel >= 100) {
            this.healthLevel = 100;
        }
    }

    //Play with pet with the specified toy item
    public void Play(Toys toy) {
        this.happinessLevel += toy.getHappinessGain();
        this.energyLevel -= toy.getEnergyConsumption();
        if (this.energyLevel >= 100) {
            this.energyLevel = 100;
        }
        if (this.happinessLevel >= 100) {
            this.happinessLevel = 100;
        }
    }

    //Rest action for pet
    public void Rest() {
        this.energyLevel += 60;
        this.happinessLevel += 2;
        this.healthLevel += 10;
        this.hungerLevel -= 10;
        if (this.energyLevel >= 100) {
            this.energyLevel = 100;
        }
        if (this.happinessLevel >= 100) {
            this.happinessLevel = 100;
        }
        if (this.healthLevel >= 100) {
            this.healthLevel = 100;
        }
        if (this.hungerLevel <= 0) {
            this.hungerLevel = 0;
        }
    }

    // After every action the pet goes through one game tick
    synchronized public void gametick() {
        this.hungerLevel -= this.hungerDecay;
        this.energyLevel -= this.energyDecay;
        this.happinessLevel -= this.happinessDecay;
        this.happinessLevel = Math.round(this.happinessLevel * 100.0) / 100.0;
        this.age += 0.1;
        this.age = Math.round(this.age * 100.0) / 100.0;
        if ((this.getHungerLevel()) < 10 || (this.getEnergyLevel() < 10) || (this.getHappinessLevel() < 10)) {
            this.healthLevel -= 5;
        }

        if (this.healthLevel == 0) {
            this.isAlive = false;
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param hungerDecay the hungerDecay to set
     */
    public void setHungerDecay(double hungerDecay) {
        this.hungerDecay = hungerDecay;
    }

    /**
     * @param energyDecay the energyDecay to set
     */
    public void setEnergyDecay(double energyDecay) {
        this.energyDecay = energyDecay;
    }

    /**
     * @param happinessDecay the happinessDecay to set
     */
    public void setHappinessDecay(double happinessDecay) {
        this.happinessDecay = happinessDecay;
    }

    /**
     * @return the age
     */
    public double getAge() {
        return age;
    }

    /**
     * @return the hungerLevel
     */
    public double getHungerLevel() {
        return hungerLevel;
    }

    /**
     * @return the energyLevel
     */
    public double getEnergyLevel() {
        return energyLevel;
    }

    /**
     * @return the healthLevel
     */
    public double getHealthLevel() {
        return healthLevel;
    }

    /**
     * @return the happinessLevel
     */
    public double getHappinessLevel() {
        return happinessLevel;
    }

    /**
     * @return the hungerDecay
     */
    public double getHungerDecay() {
        return hungerDecay;
    }

    /**
     * @return the energyDecay
     */
    public double getEnergyDecay() {
        return energyDecay;
    }

    /**
     * @return the happinessDecay
     */
    public double getHappinessDecay() {
        return happinessDecay;
    }

    public boolean getIsAlive() {
        return isAlive;
    }
}
