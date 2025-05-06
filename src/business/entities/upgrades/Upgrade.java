package business.entities.upgrades;

public abstract class Upgrade {
    private float basePrice;
    private final double INCREMENT_COST = 1.07;

    public Upgrade(float basePrice) {
        this.basePrice = basePrice;
    }

    public float calculateNewPrice(int numberOfUpgrades) {
        return Math.round(basePrice*Math.pow(INCREMENT_COST, numberOfUpgrades));
    }

    public float getBasePrice() {
        return basePrice;
    }




}

