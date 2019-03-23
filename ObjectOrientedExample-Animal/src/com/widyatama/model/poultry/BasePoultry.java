package com.widyatama.model.poultry;

import com.widyatama.interfaces.IOvipar;
import com.widyatama.model.abstraction.Animal;

public abstract class BasePoultry extends Animal implements IOvipar {

    public BasePoultry(String name, boolean isCarnivore, int footCount, int weight, int age, String gender) {
        super(name, isCarnivore, footCount, weight, age, gender);
    }

    public BasePoultry() {
        super();
    }

    @Override
    public void layingEggs() {
        System.out.println(this.getName() + " mengeluarkan telur !");
    }

    @Override
    public void incubatingEggs() {
        System.out.println(this.getName() + " mengerami telur !");
    }
}
