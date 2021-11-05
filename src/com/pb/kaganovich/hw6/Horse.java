package com.pb.kaganovich.hw6;

import java.util.Objects;

/**
 * Класс Конь
 */
public class Horse extends Animal {
    private String horseshoe; // Тип подков

    public Horse(String food, String location, String horseshoe) {
        super(food, location);
        this.horseshoe = horseshoe;
    }

    @Override
    public void makeNoise() {
        System.out.print("Конь ");
        super.makeNoise();
        System.out.println();
    }

    @Override
    public void eat() {
        System.out.print("Конь ");
        super.eat();
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Horse)) return false;
        if (!super.equals(o)) return false;
        Horse horse = (Horse) o;
        return Objects.equals(horseshoe, horse.horseshoe);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), horseshoe);
    }

    @Override
    public String toString() {
        // Вариант toString()
        // return "Конь, проживающий в г." + getLocation() + ". Любимое питание: " + getFood() + ", тип подков: " + horseshoe;
        return "Конь";
    }
}
