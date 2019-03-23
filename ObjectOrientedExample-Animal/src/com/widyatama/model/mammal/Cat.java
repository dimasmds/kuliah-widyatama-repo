package com.widyatama.model.mammal;

import com.widyatama.interfaces.IPet;

public class Cat extends BaseMammal implements IPet {

    public Cat(String name, boolean isCarnivore, int footCount, int weight, int age, String gender) {
        super(name, isCarnivore, footCount, weight, age, gender);
    }

    public Cat() {
        super();
    }

    @Override
    public void playWithHuman() {
        System.out.println(this.getName() + " bermain dengan Manusia !");
    }
}
