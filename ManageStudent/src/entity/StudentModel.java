package entity;

import controller.Find;

import java.lang.reflect.Array;
import java.lang.String;
import java.util.ArrayList;
import controller.Find;

// Model --> Controller

public class StudentModel {

    private ArrayList<Student> studentList = new ArrayList<>();

    // Model --> EditData.java
    public void add(Student student) {
        studentList.add(student);
    }

    public void remove(Student student) {
        studentList.remove(student);
    }

    public boolean isExistId(String id) {
        for (Student s : studentList) {
            if (s.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    // Model --> Find.java
    public ArrayList<Student> isExistNameList(String keyword) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Student> isExistIdList(String number) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getId().contains(number)) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Student> isExistYearOfBirthList(String number) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getYearOfBirth().contains(number)) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Student> isExistClassID(String keyword) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getClassId().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Student> isExistAccommodationList(String keyword) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getAccommodation().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    // Model --> Sort.java
    public enum Order {
        ASC, // Ascending
        DESC // Descending
    }

    public void SortNameList (Order order) {
        studentList.sort((s1,s2) -> {
            int result = s1.getName().compareToIgnoreCase(s2.getName());
            return order == Order.ASC ? result : -result;
        });

    }

    public void SortStudentIDList (Order order) {
        studentList.sort((s1, s2) -> {
            int result = s1.getId().compareToIgnoreCase(s2.getId());
            return order == Order.ASC ? result : -result;
        });
    }

    public void SortYearOfBirthList (Order order) {
        studentList.sort((s1, s2) -> {
            int result = s1.getYearOfBirth().compareToIgnoreCase(s2.getYearOfBirth());
            return order == Order.ASC ? result : -result;
        });
    }

    public void SortClassID (Order order) {
        studentList.sort((s1, s2) -> {
            int result = s1.getClassId().compareToIgnoreCase(s2.getClassId());
            return order == Order.ASC ? result : -result;
        });
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    // Model --> UpdateData.java
    public Student FindOnlyStudentID (String studentID) {
        for (Student s : studentList) {
            if (s.getId().equals(studentID)) {
                return s;
            }
        }
        return null;
    }

    public void UpdateModel (String studentID) throws Exception {

    }
}