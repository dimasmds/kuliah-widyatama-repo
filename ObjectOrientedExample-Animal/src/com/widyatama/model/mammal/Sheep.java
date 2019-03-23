package com.widyatama.model.mammal;

import com.widyatama.interfaces.ICattle;

public class Sheep extends BaseMammal implements ICattle {

    public Sheep(String name, boolean isCarnivore, int footCount, int weight, int age, String gender) {
        super(name, isCarnivore, footCount, weight, age, gender);
    }

    public Sheep() {
        super();
    }

    @Override
    public void farmed() {
        System.out.println(this.getName() + " sedang diperah susunya !");
    }
}
