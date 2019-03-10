package model.bike;

import model.abstraction.Vehicle;
import model.general.Vendor;
import model.general.Wheel;

public abstract class BaseBike extends Vehicle {
    private boolean isAutoTransmission;

    public BaseBike(String name, String color, Vendor vendor, Wheel[] wheels, int maxSpeed, int width, int height, boolean isAutoTransmission) {
        super(name, color, vendor, wheels, maxSpeed, width, height);
        this.isAutoTransmission = isAutoTransmission;
    }

    public BaseBike() {
        super();
    }

    public boolean isAutoTransmission() {
        return isAutoTransmission;
    }

    public void setAutoTransmission(boolean autoTransmission) {
        isAutoTransmission = autoTransmission;
    }

    @Override
    public String getInformation() {
        return super.getInformation() + " Matic: " + isAutoTransmission;
    }
}
