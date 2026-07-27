package controller;

import entity.Student;
import entity.StudentModel;

import java.util.ArrayList;

public class Find {

    private StudentModel model;

    public Find(StudentModel sharedModel) {
        this.model = sharedModel;
    }

    public ArrayList<Student> findByName(String keyword) throws Exception {
        ArrayList<String> errors = new ArrayList();
        ArrayList<Student> result = model.isExistNameList(keyword);
        if (keyword == null || keyword.isEmpty()) errors.add("Keyword is required.");
        else if (keyword.matches("^[a-zA-Z]+$\\s")) {
            errors.add("Name contains letters only.");
        }else if (keyword.length() < 2) {
            errors.add("Keyword must be at least 2 letters long.");
        } else {
            for (Student s : model.getStudentList()) {
                String[] words = s.getName().split("\\s+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(keyword)) {
                        result.add(s);
                    }
                    break;
                }
            }
            if (result.isEmpty()) throw new Exception("Student not found");
        }

        if (!errors.isEmpty()) throw new IllegalArgumentException(String.join("\n", errors));
        return result;
    }

    public ArrayList<Student> findByStudentId(String number) throws Exception {
        ArrayList<String> errors = new ArrayList<>();
        ArrayList<Student> result = model.isExistIdList(number);
        if (number== null || number.isEmpty()) errors.add("Student ID is required.");
        else {
            if (!number.matches("^[0-9]+$")) errors.add("Student ID contains numbers only.");
            if (number.length() > 8) errors.add("Invalid student ID.");
            if (result.isEmpty()) throw new Exception("No matching Student ID found.");
        }
        if (!errors.isEmpty()) throw new IllegalArgumentException(String.join("\n", errors));
        return result;
    }

    public ArrayList<Student> findByYearOfBirth(String number) throws Exception {
        ArrayList<String> errors = new ArrayList<>();
        ArrayList<Student> result = model.isExistYearOfBirthList(number);
        if (number == null || number.isEmpty()) errors.add("Year of birth is required.");
        else {
            if (!number.matches("^[0-9]+$")) errors.add("Year Of Birth contains numbers only.");
            if (number.length() != 4) errors.add("Invalid year of birth");
            if (result.isEmpty()) throw new Exception("No matching Year Of Birth found.");
        }
        if (!errors.isEmpty()) throw new IllegalArgumentException(String.join("\n", errors));
        return result;
    }

    public ArrayList<Student> findByClassID(String keyword) throws Exception {
        ArrayList<String> errors = new ArrayList<>();
        ArrayList<Student> result = model.isExistClassID(keyword);
        if (keyword == null || keyword.isEmpty()) errors.add("ClassID is required.");
        else {
            if (!keyword.matches("^[a-zA-Z0-9]+$")) errors.add("Class ID contains letters and numbers only.");
            if (result.isEmpty()) throw new Exception("No matching Class ID found.");
        }
        if (!errors.isEmpty()) throw new IllegalArgumentException(String.join("\n", errors));
        return result;
    }

    public ArrayList<Student> findByAccommodation(String keyword) throws Exception {
        ArrayList<String> errors = new ArrayList<>();
        ArrayList<Student> result = model.isExistAccommodationList(keyword);
        if (keyword == null || keyword.isEmpty()) errors.add("Accommodation is required.");
        else {
            if (!keyword.equalsIgnoreCase("Rented House") && !keyword.equalsIgnoreCase("Dormitory")) errors.add("Accommodation not found.");
            if (result.isEmpty()) throw new Exception("No matching Accommodation found.");
        }
        if (!errors.isEmpty()) throw new IllegalArgumentException(String.join("\n", errors));
        return result;
    }
}
