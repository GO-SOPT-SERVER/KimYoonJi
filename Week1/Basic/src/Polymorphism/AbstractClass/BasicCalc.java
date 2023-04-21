package Polymorphism.AbstractClass;

public class BasicCalc extends Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public double add(double a, double b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return 0;
    }

    @Override
    public double average(int[] a) {
        double sum = 0;
        for (int i : a) {
            sum += i;
        }
        return sum/a.length;
    }

    public static void main(String [] args) {
        BasicCalc c = new BasicCalc();
        System.out.println(c.add(3,2));
        System.out.println(c.add(3.0, 2.0));
        System.out.println(c.subtract(3,2));
        System.out.println(c.average(new int [] { 2, 3, 4 }));
    }
}
햣