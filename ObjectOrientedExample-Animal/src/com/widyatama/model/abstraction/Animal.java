package com.widyatama.model.abstraction;

import com.widyatama.interfaces.IAnimal;
import com.widyatama.interfaces.IReproduction;

public abstract class Animal implements IAnimal, IReproduction {

    public abstract class Gender{
        public static final String MALE = "MALE";
        public static final String FEMALE = "FEMALE";
    }

    private String name;
    private boolean isCarnivore;
    private int footCount;
    private int weight;
    private int age;
    private String gender;

    public Animal(String name, boolean isCarnivore, int footCount, int weight, int age, String gender) {
        this.name = name;
        this.isCarnivore = isCarnivore;
        this.footCount = footCount;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
    }

    public Animal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCarnivore() {
        return isCarnivore;
    }

    public void setCarnivore(boolean carnivore) {
        isCarnivore = carnivore;
    }

    public int getFootCount() {
        return footCount;
    }

    public void setFootCount(int footCount) {
        this.footCount = footCount;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void eat(int quantity) {
        String food = isCarnivore ? "Daging" : "Rumput";
        System.out.println(this.name + " Memakan " + food + " sebanyak " + quantity + " Kg");
        this.weight += quantity;
        System.out.println("Berat " + this.name + " menjadi " + this.weight + " Kg");
    }

    @Override
    public void sleep(int hour) {

    }

    @Override
    public void diet(int day) {
        System.out.println(this.name + " diet selama " + day + " hari");
        this.weight -= day;
        System.out.println("Berat " + this.name + " menjadi " + this.weight + " Kg");
    }

    @Override
    public void reproducing() {
        System.out.println(this.name + " sedang berkembang biak !");
    }

    public String getInformation(){
        return "Nama: " + this.getName() + "\n" +
                "Karnivora?: " + this.isCarnivore() + "\n" +
                "Jumlah Kaki: " + this.getFootCount() + "\n" +
                "Berat: " + this.getWeight() + "\n" +
                "Umur: " + this.getAge() + "\n" +
                "Kemain " + this.getGender();
    }
}
