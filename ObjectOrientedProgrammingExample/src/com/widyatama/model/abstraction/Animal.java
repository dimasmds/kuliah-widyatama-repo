package com.widyatama.model.abstraction;

public abstract class Animal {
    private String name;
    private boolean isMamalia;
    private int footCount;
    private int weight;

    public Animal() {
        this.name = "Anonym";
        this.isMamalia = false;
        this.footCount = 0;
        this.weight = 0;
    }

    public Animal(String name, boolean isMamalia, int footCount, int weight) {
        this.name = name;
        this.isMamalia = isMamalia;
        this.footCount = footCount;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMamalia() {
        return isMamalia;
    }

    public void setMamalia(boolean mamalia) {
        isMamalia = mamalia;
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

    public void eat(int quantity){
        System.out.println(this.name + " Memakan rumput sebanyal " + quantity + " Kg");
        this.weight += quantity;
        System.out.println("Berat " + this.name + " menjadi " + this.weight + " Kg");
    }

    public void diet(int day){
        System.out.println(this.name + " diet selama " + day + " hari");
        this.weight -= day;
        System.out.println("Berat " + this.name + " menjadi " + this.weight + " Kg");
    }
}
