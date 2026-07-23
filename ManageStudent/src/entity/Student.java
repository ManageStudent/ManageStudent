package entity;

public class Student extends Person {
    private String id;
    private String classId;
    private String accommodation;

    public Student(String name, String yearOfBirth, String id, String classId, String accommodation) {
        super(name, yearOfBirth);
        this.id = id;
        this.classId = classId;
        this.accommodation = accommodation;
    }

    // setter chinh sua id (MSSV), classID (Lop), accommodation (ChoO)
    public void setId(String id) {
        this.id = id;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    // getter lay MSSV, Lop va ChoO
    public String getId() {
        return id;
    }

    public String getClassId() {
        return classId;
    }

    public String getAccommodation() {
        return accommodation;
    } 

    @Override
    public String getInfo() {
        return super.getInfo();
    }
}
