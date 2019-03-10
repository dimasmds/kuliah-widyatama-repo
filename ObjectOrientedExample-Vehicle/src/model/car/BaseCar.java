package model.car;

import model.abstraction.Vehicle;
import model.general.CarSeat;
import model.general.Vendor;
import model.general.Wheel;

public abstract class BaseCar extends Vehicle {

    private CarSeat[] carSeats;

    public BaseCar(String name, String color, Vendor vendor, Wheel[] wheels, int maxSpeed, int width, int height, CarSeat[] carSeats) {
        super(name, color, vendor, wheels, maxSpeed, width, height);
        this.carSeats = carSeats;
    }

    public BaseCar() {
        super();
    }

    public CarSeat[] getCarSeats() {
        return carSeats;
    }

    public void setCarSeats(CarSeat[] carSeats) {
        this.carSeats = carSeats;
    }

    @Override
    public String getInformation() {
        return super.getInformation() + "Jumlah Kursi: " + carSeats.length;
    }
}
