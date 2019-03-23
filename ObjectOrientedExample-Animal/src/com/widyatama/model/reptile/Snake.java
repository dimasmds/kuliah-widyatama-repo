package com.widyatama.model.reptile;

import com.widyatama.interfaces.IPoison;

public class Snake extends BaseReptile implements IPoison {

    public Snake(String name, boolean isCarnivore, int footCount, int weight, int age, String gender) {
        super(name, isCarnivore, footCount, weight, age, gender);
    }

    public Snake() {
        super();
    }

    @Override
    public void removePoison() {
        System.out.println(this.getName() + " mengeluarkan bisa racun !");
    }
}
