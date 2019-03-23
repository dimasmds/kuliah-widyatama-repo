package com.widyatama.model.general;

import com.widyatama.model.abstraction.Animal;

import java.util.ArrayList;

public class Zoo {

    private ArrayList<Animal> animals = new ArrayList<>();
    private String name;


    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
        System.out.println(animal.getName() + " Telah dimasukan ke dalam kebun binatang !");
    }

    public void addAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
        System.out.println("Berhasil memasukan sekelompok hewan ke dalam kebun binatang !");
    }

    public void removeAnimal(int i) {
        animals.remove(i);
    }

    public void removeAnimal(Animal animal) {
        if (animals.contains(animal)) {
            animals.remove(animal);
            System.out.println(animal.getName() + " Berhasil dikeluarkan dari kebun binatang !");
        } else
            System.out.println("Hewan tidak ada");
    }

    public void showAnimals() {
        System.out.println("Kebun Binatang: " + getName());
        System.out.println("Jumlah Binatang: " + animals.size());
        System.out.println("****************** DAFTAR BINATANG ***********************");
        for (Animal animal : animals) {
            System.out.println("Nama Hewan : " + animal.getName());
        }
    }
}
