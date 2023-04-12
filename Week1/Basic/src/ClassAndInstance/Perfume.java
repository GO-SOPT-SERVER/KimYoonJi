package ClassAndInstance;

public class Perfume {
    private String brand;
    private String name;
    private String top;
    private String middle;
    private String base;
    private int salesCount;

    public Perfume(String brand, String name, String top, String middle, String base, int salesCount) {
        this.brand = brand;
        this.name = name;
        this.top = top;
        this.middle = middle;
        this.base = base;
        this.salesCount = salesCount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    public void addSalesCount() {
        salesCount = salesCount + 100;
    }

    public void printPerfumeInfo() {
        System.out.println("향수 브랜드 : " + brand);
        System.out.println("향수 이름 : " + name);
        System.out.println("탑 노트 : " + top);
        System.out.println("미들 노트 : " + middle);
        System.out.println("베이스 노트 : " + base);
        System.out.println("향수 판매량 : " + salesCount + "개");
    }
}
