package clean.code.design_patterns.requirements;

import java.util.*;

public class CarShowInit {

    private final adaptSeats seats = new adaptSeats();
    private final ArrayList<Car> carList = new ArrayList<Car>();

    private static final CarShowInit carShow = new CarShowInit();
    private CarShowInit() {}
    public static CarShowInit getInstance(){
        return carShow;
    }

    public void addCarToShow(Car car){
        this.carList.add(car);
    }
    public void setSeats(int seatSet){
        seats.setSeatsNumber(seatSet);
        seats.resizeNumberOfPeopleAndSeats();
    }

    public void presentCars(){
        System.out.println("\nCars added to the show:\n");
        int i = 1;
        for(Car o : this.carList){
            System.out.println("Car #" + i);
            System.out.println(o.getCarManufacturer() + " " + o.getCarModel());
            System.out.println("Wheels: " + o.getNumberOfWheels());
            System.out.println("Seats: " + o.getNumberOfSeats());
            System.out.println("Engine Model: " + o.withEngineModel());
            System.out.println("Color: " + o.withCarColor());
            System.out.println("Navigation: " + o.withNavigation());
            System.out.println("Bluetooth: " + o.withBluetooth());
            System.out.println("Adaptive Cruise Control: " + o.withAdaptiveCruiseControl() + "\n");
            i++;
        }

        System.out.println("\nEnjoy watching them!");
    }

    public static void main(String[] args) {

                CarShowInit show = getInstance();
                show.setSeats(6000);
                Car car1 = new Car.Builder("Audi","A8",4,4).withCarColor("Green").withBluetooth(true).withNavigation(true).buildCar();
                Car car2 = new Car.Builder("BMW","i8",4,4).withCarColor("Green").withEngineModel("ALH").withBluetooth(true).withNavigation(true).buildCar();
                show.addCarToShow(car1);
                show.addCarToShow(car2);
                show.presentCars();
    }
}
