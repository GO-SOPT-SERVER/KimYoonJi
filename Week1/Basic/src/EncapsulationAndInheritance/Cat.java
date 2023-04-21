package EncapsulationAndInheritance;

public class Cat extends Animal {
    private String gender;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void jump() {
        System.out.println(this.name + "(이)가 점프를 합니다.");
    }

    public void meow() {
        System.out.println(this.name + "(이)가 웁니다. 야옹~");
    }

    @Override
    public String toString() {
        return "Cat { " +
                "Name = '" + name + '\'' +
                ", Age = " + age +
                ", Gender = '" + gender + '\'' +
                " }";
    }
}

