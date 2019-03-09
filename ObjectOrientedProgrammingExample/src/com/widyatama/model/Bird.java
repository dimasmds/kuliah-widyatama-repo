package com.widyatama.model;

import com.widyatama.interfaces.IFly;
import com.widyatama.model.abstraction.Animal;

public class Bird extends Animal implements IFly {

    public Bird() {
        super();
    }

    public Bird(String name, boolean isMamalia, int footCount, int weight) {
        super(name, isMamalia, footCount, weight);
    }

    @Override
    public void fly(String locationName) {
        System.out.println(this.getName() + " Terbang mengitari kota " + locationName);
    }
}
