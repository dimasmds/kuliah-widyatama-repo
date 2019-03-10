package model.car;

import interfaces.IRacing;
import model.general.CarSeat;
import model.general.Vendor;
import model.general.Wheel;

public class RaceCar extends BaseCar implements IRacing {

    public RaceCar(String name, String color, Vendor vendor, Wheel[] wheels, int maxSpeed, int width, int height, CarSeat[] carSeats) {
        super(name, color, vendor, wheels, maxSpeed, width, height, carSeats);
    }

    @Override
    public void race() {
        System.out.println(this.getName() + " Memulai Balapan !");
    }

    @Override
    public void turbo() {
        System.out.println(this.getName() + " Mengaktifkan Turbo !");
    }
}
