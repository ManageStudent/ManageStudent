package entity;

public class Person {
    private String name;
    private int yearOfBirth; // nam sinh de tim khoa hoc cua sinh vien dc ko??

    // Constructor
    public Person(String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    // setter chinh Sua Ten va tuoi
    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    // getTen va getNamSinh
    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    // getInfo 
    public void getInfo() {
        System.out.println(name + " " + yearOfBirth);
    }
}
