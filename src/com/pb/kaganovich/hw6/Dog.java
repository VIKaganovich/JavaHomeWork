package com.pb.kaganovich.hw6;

import java.util.Objects;

/**
 * Класс Пес
 */
public class Dog extends Animal {
    private boolean hunt; // Охотничья порода

    public Dog(String food, String location, boolean hunt) {
        super(food, location);
        this.hunt = hunt;
    }

    @Override
    public void makeNoise() {
        System.out.print("Пес ");
        super.makeNoise();
        System.out.println();
    }

    @Override
    public void eat() {
        System.out.print("Пес ");
        super.eat();
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog)) return false;
        if (!super.equals(o)) return false;
        Dog dog = (Dog) o;
        return hunt == dog.hunt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hunt);
    }

    @Override
    public String toString() {
        if (hunt)
            return "Охотничий пес, проживающий в г." + getLocation() + ". Любимое питание: " + getFood();
        else
            return "Пес, проживающий в г." + getLocation() + ". Любимое питание: " + getFood();
    }
}
