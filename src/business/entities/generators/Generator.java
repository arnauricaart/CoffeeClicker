package business.entities.generators;

public abstract class Generator implements Runnable{
    private String name;
    private float basePrice;
    private float production;
    private float timeInterval;
    private final String imageLink;
    private float incrementCost;
    private float price;

    public Generator(String name, float baseCost, double production, double timeInterval, String imagePath, double costIncrement) {
        this.name = name;
        this.basePrice = baseCost;
        this.production = (float) production;
        this.timeInterval = (float) timeInterval;
        this.imageLink = imagePath;
        this.incrementCost = (float) costIncrement;
        this.price = baseCost;
    }

    public void addGenerator() {
        // hay que cambiar la lógica de todos estos
        price += incrementCost;
        // number of generators++?
    }

    public void generateCoffee(){
        //ToDo
    }

    public double getTotalProduction() {
        return production / timeInterval;
    }

    public double calculateNewPrice(int numberOfGenerators) {
        return Math.round(basePrice*Math.pow(1.07, numberOfGenerators));
    }

    public String getName() {
        return name;
    }

    public double getProduction() {
        return production;
    }
}
