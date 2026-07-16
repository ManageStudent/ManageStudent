package model;

public class Person {
    private String Ten;
    private int namSinh; // nam sinh de tim khoa hoc cua sinh vien dc ko??

    // Constructor
    public Person(String Ten, int namSinh) {
        this.Ten = Ten;
        this.namSinh = namSinh;
    }

    // setter chinh Sua Ten va tuoi
    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    // getTen va getNamSinh
    public String getTen() {
        return Ten;
    }

    public int getNamSinh() {
        return namSinh;
    }

    // getInfo 
    public void getInfo() {
        System.out.println(Ten + " " + namSinh);
    }
}
