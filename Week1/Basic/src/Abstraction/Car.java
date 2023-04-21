package Abstraction;

public class Car {
    private String brand;
    private String model;
    private String color;
    private String engine;

    public void start() {
        System.out.println("start");
    }

    public void stop() {
        System.out.println("stop");
    }

    public void accelerate() {
        System.out.println("accelerate");
    }

    public void brake() {
        System.out.println("brake");
    }
}
