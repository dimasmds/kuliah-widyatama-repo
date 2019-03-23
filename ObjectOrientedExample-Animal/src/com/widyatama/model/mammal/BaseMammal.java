package com.widyatama.model.mammal;

import com.widyatama.interfaces.IVivipar;
import com.widyatama.model.abstraction.Animal;

public abstract class BaseMammal extends Animal implements IVivipar {

    public BaseMammal(String name, boolean isCarnivore, int footCount, int weight, int age, String gender) {
        super(name, isCarnivore, footCount, weight, age, gender);
    }

    public BaseMammal() {
        super();
    }

    @Override
    public void giveBirth() {
        System.out.println(this.getName() + " melahirkan !");
    }

    @Override
    public void breastFeeding() {
        System.out.println(this.getName() + " Sedang menyusui !");
    }
}
