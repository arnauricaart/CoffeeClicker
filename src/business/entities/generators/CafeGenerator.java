package business.entities.generators;

public class CafeGenerator extends Generator {

    public CafeGenerator(String name, float baseCost, double production, double timeInterval, String imagePath, double costIncrement) {
        super(name, baseCost, production, timeInterval, imagePath, costIncrement);
    }

    @Override
    public void run() {

    }
}
