package model.general;

public class CarSeat {
    private Vendor vendor;
    private String material;

    public CarSeat(Vendor vendor, String material) {
        this.vendor = vendor;
        this.material = material;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public String getMaterial() {
        return material;
    }
}
