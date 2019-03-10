package model.garage;

import model.abstraction.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Garage {

    private String name;
    private int large;
    private int usedSpace = 0;
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    public Garage(String name, int large) {
        this.name = name;
        this.large = large;
    }

    public String getName() {
        return name;
    }

    public int getLarge() {
        return large;
    }

    public int getUsedSpace() {
        return usedSpace;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        if (usedSpace < large && usedSpace + vehicle.getLarge() <= large) {
            if (vehicles.contains(vehicle)) System.out.println("Maaf ! Kendaraan sudah dimasukan !");
            else {
                vehicles.add(vehicle);
                System.out.println("Kendaraaan " + vehicle.getName() + " dimasukan ke dalam garasi !");
                usedSpace += vehicle.getLarge();
            }
        } else {
            System.out.println("Garasi tidak cukup lagi untuk memasukan kendaraan!");
        }
    }

    public void removeVehicle(int index) {
        if (vehicles.size() > 0) {
            System.out.println("Kendaraan " + vehicles.get(index).getName() + " dikeluarkan dari garasi !");
            vehicles.remove(index);
            usedSpace -= vehicles.get(index).getLarge();
        } else System.out.println("Garasi Kosong !");
    }

    public void removeVehicle(Vehicle vehicle) {
        if (vehicles.size() > 0) {
            if (!vehicles.contains(vehicle)) System.out.println("Maaf kendaraan tidak ada didalam garasi !");
            else {
                System.out.println("Kendaraan " + vehicle.getName() + " dikeluarkan dari garasi !");
                vehicles.remove(vehicle);
                usedSpace -= vehicle.getLarge();
            }
        } else System.out.println("Garasi Kosong !");
    }

    public void showVehicles() {
        System.out.println("Daftar kendaraan pada garasi " + name + " :");
        int i = 1;
        for (Vehicle vehicle : vehicles) {
            System.out.println(i + ". " + vehicle.getName());
            i++;
        }
    }
}
