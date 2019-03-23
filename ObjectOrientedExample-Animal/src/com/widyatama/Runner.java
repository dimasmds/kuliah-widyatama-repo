package com.widyatama;

import com.widyatama.model.general.Zoo;
import com.widyatama.model.mammal.Cat;
import com.widyatama.model.mammal.Horse;
import com.widyatama.model.mammal.Sheep;
import com.widyatama.model.poultry.Bird;
import com.widyatama.model.poultry.Eagle;
import com.widyatama.model.reptile.Crocodile;
import com.widyatama.model.reptile.Snake;

import static com.widyatama.model.abstraction.Animal.Gender.FEMALE;
import static com.widyatama.model.abstraction.Animal.Gender.MALE;

public class Runner {

    public static String SPARATE = "**************************************************";

    public static void main(String[] args) {
        Cat cat = new Cat("Chandra's Cat", true, 4, 3, 2, MALE);
        cat.playWithHuman();
        System.out.println(cat.getInformation());
        sparate();

        Horse horse = new Horse("Dimas's Horse", false, 4, 25, 12, FEMALE);
        horse.run(10);
        System.out.println(horse.getInformation());
        sparate();

        Sheep sheep = new Sheep("Maulana's Sheep", false, 4, 15, 10, MALE);
        sheep.farmed();
        System.out.println(sheep.getInformation());
        sparate();

        Bird bird = new Bird("Ifran's Bird", true, 2, 2, 2, MALE);
        bird.fly("Bandung");
        System.out.println(bird.getInformation());
        sparate();

        Eagle eagle = new Eagle("Saputra's Eagle", true, 2, 5, 10, FEMALE);
        eagle.preyOn();
        eagle.fly("Jakarta");
        System.out.println(eagle.getInformation());
        sparate();

        Crocodile crocodile = new Crocodile("Abdul's Eagle", true, 4, 40, 20, FEMALE);
        crocodile.preyOn();
        System.out.println(crocodile.getInformation());
        sparate();

        Snake snake = new Snake("Eka's Snake", true, 0, 4, 12, MALE);
        snake.removePoison();
        System.out.println(crocodile.getInformation());
        sparate();

        Zoo zoo = new Zoo();
        zoo.setName("Kebun Binatang Ragunan");

        zoo.addAnimal(cat);
        zoo.addAnimal(horse);
        zoo.addAnimal(sheep);
        zoo.addAnimal(bird);
        zoo.addAnimal(eagle);
        zoo.addAnimal(crocodile);
        zoo.addAnimal(snake);

        zoo.removeAnimal(eagle);

        zoo.showAnimals();
    }

    private static void sparate() {
        System.out.println(SPARATE);
    }
}
