package com.pb.kaganovich.hw6;

/**
 * Класс ветеринар
 */
public class Veterinarian {
    private String name;

    public Veterinarian(String name) {
        this.name = name;
    }

    public void treatAnimal(Animal animal) {
        System.out.println(Animal.checkAnimal(animal) + " из г." + animal.getLocation()
                + ", любимая пища " + animal.getFood());
    }

    @Override
    public String toString() {
        return "Прием ведет ветеринар " + name;
    }
}
