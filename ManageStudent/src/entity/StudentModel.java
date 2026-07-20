package entity;

import java.util.ArrayList;

public class StudentModel {

    private ArrayList<Student> studentList = new ArrayList<>();

    public boolean isExistId(String id) {
        for (Student s : studentList) {
            if (s.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void add(Student student) {
        studentList.add(student);
    }

    public void remove(Student student) {
        studentList.remove(student);
    }
}