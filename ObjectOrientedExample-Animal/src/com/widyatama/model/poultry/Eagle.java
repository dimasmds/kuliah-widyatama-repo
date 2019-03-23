package com.widyatama.model.poultry;

import com.widyatama.interfaces.IFly;
import com.widyatama.interfaces.IPredator;

public class Eagle extends BasePoultry implements IFly, IPredator {
    public Eagle(String name, boolean isCarnivore, int footCount, int weight, int age, String gender) {
        super(name, isCarnivore, footCount, weight, age, gender);
    }

    public Eagle() {
        super();
    }

    @Override
    public void preyOn() {
        System.out.println(this.getName() + " mengintai mangsa dari udara !");
    }

    @Override
    public void fly(String locationName) {
        System.out.println(this.getName() + " terbang ke kota " + locationName);
    }
}
