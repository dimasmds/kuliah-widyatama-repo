package com.widyatama.model.reptile;

import com.widyatama.interfaces.IOvipar;
import com.widyatama.interfaces.IVivipar;
import com.widyatama.model.abstraction.Animal;

public abstract class BaseReptile extends Animal implements IVivipar, IOvipar {

    public BaseReptile(String name, boolean isCarnivore, int footCount, int weight, int age, String gender) {
        super(name, isCarnivore, footCount, weight, age, gender);
    }

    public BaseReptile() {
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

    @Override
    public void giveBirth() {
        System.out.println(this.getName() + " melahirkan !");
    }

    @Override
    public void breastFeeding() {
        System.out.println(this.getName() + " Sedang menyusui !");
    }
}
