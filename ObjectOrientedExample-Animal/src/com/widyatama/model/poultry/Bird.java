package com.widyatama.model.poultry;

import com.widyatama.interfaces.IFly;

public class Bird extends BasePoultry implements IFly {

    public Bird(String name, boolean isCarnivore, int footCount, int weight, int age, String gender) {
        super(name, isCarnivore, footCount, weight, age, gender);
    }

    public Bird() {
        super();
    }

    @Override
    public void fly(String locationName) {
        System.out.println(this.getName() + " terbang ke kota " + locationName);
    }
}
