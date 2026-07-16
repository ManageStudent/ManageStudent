package model;

public class Student extends Person {
    private int MSSV;
    private String Lop;
    private String ChoO;

    public Student(String Ten, int namSinh, int MSSV, String Lop, String ChoO) {
        super(Ten, namSinh);
        this.MSSV = MSSV;
        this.Lop = Lop;
        this.ChoO = ChoO;
    }

    // setter chinh sua MSSV, Lop, ChoO
    public void setMSSV(int MSSV) {
        this.MSSV = MSSV;
    }

    public void setLop(String Lop) {
        this.Lop = Lop;
    }

    public void setChoO(String ChoO) {
        this.ChoO = ChoO;
    }

    // getter lay MSSV, Lop va ChoO
    public int getMSSV() {
        return MSSV;
    }

    public String getLop() {
        return Lop;
    }

    public String getChoO() {
        return ChoO;
    } 

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println(MSSV + " " + Lop + " " + ChoO);
    }
}
