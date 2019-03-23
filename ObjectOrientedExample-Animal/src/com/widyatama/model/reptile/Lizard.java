package com.widyatama.model.reptile;

public class Lizard extends BaseReptile{

    public Lizard(String name, boolean isCarnivore, int footCount, int weight, int age, String gender) {
        super(name, isCarnivore, footCount, weight, age, gender);
    }

    public Lizard() {
        super();
    }
}
