package com.widyatama.model.mammal;

import com.widyatama.interfaces.IRun;

public class Horse extends BaseMammal implements IRun {

    public Horse(String name, boolean isCarnivore, int footCount, int weight, int age, String gender) {
        super(name, isCarnivore, footCount, weight, age, gender);
    }

    public Horse() {
        super();
    }

    @Override
    public void run(int distance) {
        System.out.println(this.getName() + " Berlari sejauh " + distance + " KM !");
    }
}
