package model.bike;

import interfaces.IPolice;
import model.general.Vendor;
import model.general.Wheel;

public class PoliceBike extends BaseBike implements IPolice {

    public PoliceBike(String name, String color, Vendor vendor, Wheel[] wheels, int maxSpeed, int width, int height, boolean isAutoTransmission) {
        super(name, color, vendor, wheels, maxSpeed, width, height, isAutoTransmission);
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
