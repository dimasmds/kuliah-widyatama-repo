package com.widyatama.model.reptile;

import com.widyatama.interfaces.IPredator;

public class Crocodile extends BaseReptile implements IPredator {

    public Crocodile(String name, boolean isCarnivore, int footCount, int weight, int age, String gender) {
        super(name, isCarnivore, footCount, weight, age, gender);
    }

    public Crocodile() {
        super();
    }

    @Override
    public void preyOn() {
        System.out.println(this.getName() + " memangsa dengan taring yang tajam !");
    }
}
