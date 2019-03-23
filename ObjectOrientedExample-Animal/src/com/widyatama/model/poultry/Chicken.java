package com.widyatama.model.poultry;

import com.widyatama.interfaces.ICattle;

public class Chicken extends BasePoultry implements ICattle {
    public Chicken(String name, boolean isCarnivore, int footCount, int weight, int age, String gender) {
        super(name, isCarnivore, footCount, weight, age, gender);
    }

    public Chicken() {
        super();
    }

    @Override
    public void farmed() {
        System.out.println(this.getName() + " sedang diambil telurnya !");
    }
}
