package com.pb.kaganovich.hw6;

import java.util.Objects;

/**
 * Класс Кот
 */
public class Cat extends Animal {
    private String wool; //тип шерсти

    public Cat(String food, String location, String wool) {
        super(food, location);
        this.wool = wool;
    }

    @Override
    public void makeNoise() {
        System.out.print("Кот ");
        super.makeNoise();
        System.out.println();
    }

    @Override
    public void eat() {
        System.out.print("Кот ");
        super.eat();
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat)) return false;
        if (!super.equals(o)) return false;
        Cat cat = (Cat) o;
        return wool.equals(cat.wool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wool);
    }

    @Override
    public String toString() {
        return wool.replace(wool.charAt(0), Character.toUpperCase(wool.charAt(0))) + " кот";
    }
}
