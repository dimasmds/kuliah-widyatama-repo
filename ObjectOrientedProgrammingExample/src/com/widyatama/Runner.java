package com.widyatama;

import com.widyatama.model.Bird;
import com.widyatama.model.Horse;

public class Runner {

    public static String SPARATE = "**************************************************";

    public static void main(String[] args) {
        Bird burung = new Bird("Rajawali", false, 2, 5);
        burung.eat(2);
        burung.fly("Banjar");
        burung.diet(4);

        if(burung.isMamalia()) System.out.println(burung.getName() + " merupakan hewan mamalia");
        else System.out.println(burung.getName() + " bukan hewan mamalia");

        sparate();

        Horse kuda = new Horse("Si Kuda", true, 4, 50);
        kuda.eat(10);
        kuda.run(7);
        kuda.diet(3);

        if(kuda.isMamalia()) System.out.println(kuda.getName() + " merupakan hewan mamalia");
        else System.out.println(kuda.getName() + " bukan hewan mamalia");
    }

    private static void sparate() {
        System.out.println(SPARATE);
    }
}
