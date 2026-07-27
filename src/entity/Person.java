package entity;

public abstract class Person {
    private String name;
    private String yearOfBirth;

    public Person(String name, String yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    // Mỗi loại Person tự quyết định cách hiển thị thông tin
    public abstract String getInfo();
}