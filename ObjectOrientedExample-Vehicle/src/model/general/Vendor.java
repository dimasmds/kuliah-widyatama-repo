package model.general;

public class Vendor {
    private String name;
    private String location;

    public Vendor(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
