package EncapsulationAndInheritance;

public class Haru {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.setName("Haru");
        cat.setAge(3);
        cat.setGender("Male");

        System.out.println(cat.toString());
        cat.eat();
        cat.sleep();
        cat.jump();
        cat.meow();
    }
}