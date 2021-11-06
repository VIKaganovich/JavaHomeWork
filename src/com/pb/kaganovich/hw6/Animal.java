package com.pb.kaganovich.hw6;

import java.util.Objects;

/**
 * Класс Животное
 */

public class Animal {
    private String food, location;

    public Animal(String food, String location) {
        this.food = food;
        this.location = location;
    }

    public String getFood() {
        return food;
    }

    public String getLocation() {
        return location;
    }



    public void makeNoise() {
        System.out.print("шумит");
    }

    public void eat() {
        System.out.print("ест");
    }

    public void sleep() {
        System.out.print(this + " спит");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(food, animal.food) && Objects.equals(location, animal.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food, location);
    }

    @Override
    public String toString() {
        return "Животное";
    }
}
