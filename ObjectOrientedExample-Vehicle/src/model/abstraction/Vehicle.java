package model.abstraction;

import interfaces.IVehicle;
import model.general.Vendor;
import model.general.Wheel;

public abstract class Vehicle implements IVehicle {

    private String name;
    private String color;
    private Vendor vendor;
    private Wheel[] wheels;
    private int maxSpeed;
    private int width;
    private int height;


    public Vehicle() {
    }

    public Vehicle(String name, String color, Vendor vendor, Wheel[] wheels, int maxSpeed, int width, int heigth) {
        this.name = name;
        this.color = color;
        this.vendor = vendor;
        this.wheels = wheels;
        this.maxSpeed = maxSpeed;
        this.width = width;
        this.height = heigth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Wheel[] getWheels() {
        return wheels;
    }

    public void setWheels(Wheel[] wheels) {
        this.wheels = wheels;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLarge(){
        return this.height * this.width;
    }

    @Override
    public void goStraight() {
        System.out.println(this.getName() + " Melaju !");
    }

    @Override
    public void turnLeft() {
        System.out.println(this.getName() + " Belok Kanan !");
    }

    @Override
    public void turnRight() {
        System.out.println(this.getName() + " Belok Kiri !");
    }

    @Override
    public void stop() {
        System.out.println(this.getName() + " Berhenti !");
    }

    @Override
    public String getInformation() {
        return "Nama Kendaraan: " + name + "\n"
                + "Warna Kendaraan: " + color + "\n"
                + "Nama Vendor: " + vendor.getName() + " (" + vendor.getLocation() + ")" + "\n"
                + "Jumlah Roda: " + wheels.length + "\n"
                + "Maksimal Kecepatan: " + maxSpeed + "\n";
    }
}


