package Polymorphism.MakeInterface;

public class iPhone implements Phone {
    @Override
    public void sendCall() {
        System.out.println("띠리리리링");
    }

    @Override
    public void receiveCall() {
        System.out.println(">> 밀어서 전화 받기");
    }

    public void printBrand() {
        System.out.println("-----🍎-----");
    }
}
