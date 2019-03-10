package model.car;

import interfaces.IPolice;
import model.general.CarSeat;
import model.general.Vendor;
import model.general.Wheel;

public class PoliceCar extends BaseCar implements IPolice {

    public PoliceCar(String name, String color, Vendor vendor, Wheel[] wheels, int maxSpeed, int width, int height, CarSeat[] carSeats) {
        super(name, color, vendor, wheels, maxSpeed, width, height, carSeats);
    }

    @Override
    public void beep() {
        System.out.println(this.getName() + " Menyalakan Klakson !");
    }

    @Override
    public void turnOnLamp() {
        System.out.println(this.getName() + " Menyalakan Lampu Alarm !");

    }
}
