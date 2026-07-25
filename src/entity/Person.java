package entity;

public class Person {
    private String name;
    private String yearOfBirth; // nam sinh de tim khoa hoc cua sinh vien dc ko??

    // Constructor
    public Person(String name, String yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    // setter chinh Sua Ten va tuoi
    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    // getTen va getNamSinh
    public String getName() {
        return name;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    // getInfo 
    public String getInfo() {
        return name + " " + yearOfBirth;
    }
}
