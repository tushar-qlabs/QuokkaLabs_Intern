class Car {
    protected String brand;
    private final int year;

    public Car() {
        this.brand = "Unknown";
        this.year = 0;
    }

    public Car(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    public void start() {
        System.out.println(brand + " car is starting (Year: " + year + ").");
    }
}

class ElectricCar extends Car {

    private final int batteryLife;

    public ElectricCar(String brand, int year, int batteryLife) {
        super(brand, year);
        this.batteryLife = batteryLife;
    }

    @Override
    public void start() {
        System.out.println(brand + " electric car is starting silently. Battery: " + batteryLife + "%");
    }
}

class SportsCar extends Car {

    private final int topSpeed;

    public SportsCar(String brand, int year, int topSpeed) {
        super(brand, year);
        this.topSpeed = topSpeed;
    }

    @Override
    public void start() {
        System.out.println(brand + " sports car goin' nut! Top Speed: " + topSpeed + " km/h");
    }
}
