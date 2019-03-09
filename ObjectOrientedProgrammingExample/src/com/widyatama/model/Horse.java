package com.widyatama.model;

import com.widyatama.interfaces.IRun;
import com.widyatama.model.abstraction.Animal;

public class Horse extends Animal implements IRun {

    public Horse() {
        super();
    }

    public Horse(String name, boolean isMamalia, int footCount, int weight) {
        super(name, isMamalia, footCount, weight);
    }

    @Override
    public void run(int distance) {
        System.out.println(this.getName() + " berlari sejauh " + distance + " KM");
    }
}
