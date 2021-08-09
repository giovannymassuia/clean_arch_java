public class FreightCalculator {

    public static double calculate(double distance, Item item) {
        double price = distance * item.getVolume() * (item.getDensity() / 100);
        return (price > 10) ? price : 10;
    }

}
