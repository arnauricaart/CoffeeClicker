package business.entities.generators;


public class CoffeeMachineGenerator extends Generator{

    public CoffeeMachineGenerator(String name, float baseCost, double production, double timeInterval, String imagePath, double costIncrement) {
        super(name, baseCost, production, timeInterval, imagePath, costIncrement);
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            generateCoffee();


        }
    }
}
