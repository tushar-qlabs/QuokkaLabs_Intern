
// Main class
public class Main {
    public static void main(String[] args) {
        // Polymorphic behavior
        Car genericCar = new Car("Generic", 2000);
        Car tesla = new ElectricCar("Tesla", 2022, 95);
        Car ferrari = new SportsCar("Ferrari", 2023, 340);

        genericCar.start();  // Generic car is starting (Year: 2000).
        tesla.start();       // Tesla electric car is starting silently. Battery: 95%
        ferrari.start();     // Ferrari sports car roars to life! Top Speed: 340 km/h
    }
}
