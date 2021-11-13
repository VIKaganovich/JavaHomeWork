package com.pb.kaganovich.hw7;

/**
 * Класс ателье
 */

public class Atelier {
    /**
     * Класс одежда
     */
    abstract class Clothes {
        private Size size;
        private String color;
        private float price;

        public Clothes(Size size, String color, float price) {
            this.size = size;
            this.color = color;
            this.price = price;
        }

        @Override
        public String toString() {
            return size.getDescription() + ": " +
                    size.getEuroSize() + ", цвет " +
                    color + ". Цена " + price;
        }
    }

    /**
     * Класс футболка
     */
    class Tshirt extends Clothes implements ManClothes, WomenClothes {

        public Tshirt(Size size, String color, float price) {
            super(size, color, price);
        }

        @Override
        public void dressMan() {
            System.out.println("Муж. футболка. " + this);
        }

        @Override
        public void dressWomen() {
            System.out.println("Жен. футболка. " + this);
        }

    }

    /**
     * Класс штаны
     */
    class Pants extends Clothes implements ManClothes, WomenClothes {

        public Pants(Size size, String color, float price) {
            super(size, color, price);
        }

        @Override
        public void dressMan() {
            System.out.println("Муж. брюки. " + this);
        }

        @Override
        public void dressWomen() {
            System.out.println("Жен. брюки. " + this);
        }

    }

    /**
     * Класс юбка
     */
    class Skirt extends Clothes implements WomenClothes {

        public Skirt(Size size, String color, float price) {
            super(size, color, price);
        }

        @Override
        public void dressWomen() {
            System.out.println("Юбка. " + this);
        }

    }

    /**
     * Класс галстук
     */
    class Tie extends Clothes implements ManClothes {

        public Tie(Size size, String color, float price) {
            super(size, color, price);
        }

        @Override
        public void dressMan() {
            System.out.println("Галстук. " + this);
        }

    }

    public static void dressMan(Clothes[] clothes) {
        System.out.println("Наша одежда для мужчин:");
        for (Clothes cloth : clothes)
            if (cloth instanceof ManClothes) ((ManClothes) cloth).dressMan();


    }

    public static void dressWomen(Clothes[] clothes) {
        System.out.println("Наша одежда для женщин:");
        for (Clothes cloth : clothes) {
            if (cloth instanceof WomenClothes) ((WomenClothes) cloth).dressWomen();
        }

    }
    public static void main(String[] args) {
        System.out.println("Ателье.");

        Atelier atelier = new Atelier();
        Clothes[] clothes = new Clothes[]{
                atelier.new Tshirt(Size.XXS, "черный", 15f),
                atelier.new Pants(Size.S, "зеленый", 45.3f),
                atelier.new Skirt(Size.XXS, "белый", 200f),
                atelier.new Tie(Size.L, "красный", 2.2f)
        };
        dressMan(clothes);
        System.out.println("---------------");
        dressWomen(clothes);
    }
}
