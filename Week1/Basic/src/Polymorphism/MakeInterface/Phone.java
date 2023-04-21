package Polymorphism.MakeInterface;

public interface Phone {
    void sendCall();
    void receiveCall();
    default void printLogo() { System.out.println("** Phone **"); }
}
