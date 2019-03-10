package model.general;

public class Wheel {
    private Vendor vendor;
    private String material;

    public Wheel(Vendor vendor, String material) {
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
