package model.bike;

import interfaces.IRacing;
import model.general.Vendor;
import model.general.Wheel;

public class RaceBike extends BaseBike implements IRacing {

    public RaceBike(String name, String color, Vendor vendor, Wheel[] wheels, int maxSpeed, int width, int height, boolean isAutoTransmission) {
        super(name, color, vendor, wheels, maxSpeed, width, height, isAutoTransmission);
    }

    @Override
    public void race() {
        System.out.println(this.getName() + " Memulai Balapan !");
    }

    @Override
    public void turbo() {
        System.out.println(this.getName() +  " Mengaktifkan Turbo !");
    }

}
