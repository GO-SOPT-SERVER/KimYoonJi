package ClassAndInstance;

public class salesPerfume {
    public static void main(String[] args) {
        Perfume mojaveGhost = new Perfume("Byredo", "Mojave Ghost", "Nesbury", "Magnolia", "Chantal Musk", 8000);

        mojaveGhost.addSalesCount();
        mojaveGhost.printPerfumeInfo();
    }
}
