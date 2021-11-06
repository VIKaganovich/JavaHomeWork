package com.pb.kaganovich.hw6;

/**
 * Класс ветеринар
 */
public class Veterinarian {
    private String name;

    public Veterinarian(String name) {
        this.name = name;
    }

    /**
     * Ветеринар определяет животное
     * @param animal неизвестное животное
     * @return определенное животное
     */
    public static String checkAnimal(Animal animal) {
        if (animal instanceof Cat)
            return "Кот";
        if (animal instanceof Dog)
            return "Пес";
        if (animal instanceof Horse)
            return "Конь";
        return "Неизвестное животное";
    }

    public void treatAnimal(Animal animal) {
        System.out.println(checkAnimal(animal) + " из г." + animal.getLocation()
                + ", употребляющий " + animal.getFood());
    }

    @Override
    public String toString() {
        return "Прием ведет ветеринар " + name;
    }
}
