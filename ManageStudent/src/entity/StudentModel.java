package entity;

import java.lang.reflect.Array;
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

    public ArrayList<Student> isExistNameList(String keyword) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Student> isExistIdList (String number) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getId().contains(number)) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Student> isExistYearOfBirthList (String number) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getYearOfBirth().contains(number)) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Student> isExistClassID (String keyword) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getClassId().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Student> isExistAccommodationList (String keyword) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getAccommodation().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    public void add(Student student) {
        studentList.add(student);
    }

    public void remove(Student student) {
        studentList.remove(student);
    }
}