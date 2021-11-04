package com.pb.kaganovich.hw6;

import java.lang.reflect.Constructor;

/**
 * Класс вет. клиника
 */
public class VetClinic {
    public static void main(String[] args) throws Exception {
        System.out.println("\"Ветеринарная клиника.\"");
         Animal[] animals = {new Cat("консервы", "Париж", "длинношерстный"),
                new Dog("кости", "Киев", true),
                new Horse("сено", "Мадрид", "зимние")
        };

        System.out.println("Поступающие пациенты:");
        for (Animal animal : animals) {
            System.out.println(animal);
        }

        Class clazz = Class.forName("com.pb.kaganovich.hw6.Veterinarian");
        Object obj = clazz.getConstructor(new Class[]{String.class}).newInstance("Вася Иванов");
        if (obj instanceof Veterinarian) {
            System.out.println(obj);
            for (Animal animal : animals) {
                ((Veterinarian) obj).treatAnimal(animal);
            }
        }

    }
}
