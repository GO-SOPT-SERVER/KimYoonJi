package EncapsulationAndInheritance;

public class Animal {
    String name;
    int age;

    void setName(String name) {
        this.name = name;
    }

    void setAge(int age) {
        this.age = age;
    }

    void eat() {
        System.out.println(this.name + "(이)가 먹습니다.");
    }

    void sleep() {
        System.out.println(this.name + "(이)가 잡니다.zz");
    }
}
