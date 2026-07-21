package controller;

import entity.Student;
import entity.StudentModel;

import java.util.ArrayList;

public class Find {
    private StudentModel model = new StudentModel();
    private ArrayList<Student> FindByName (String keyword) throws Exception{
        try {
            ArrayList<String> errors = new ArrayList();
            ArrayList<Student> result = model.isExistNameList(keyword);
            if (keyword == null || keyword.isEmpty()) errors.add("Keyword is required.");
            else {
                if (keyword.matches("^[a-zA-Z]+$\\s")) errors.add("Name contains letters only.");
                if (result.isEmpty()) throw new Exception("Student not found");
            }

            if (!errors.isEmpty()) throw new IllegalArgumentException(String.join("\n", errors));
            return result;
        }
        catch (Exception e) {
            System.out.println("Detect error: " + e.getMessage());
        }
        return null;
    }

    private ArrayList<Student> FindByStudentId (String number) throws Exception {
        try {
            ArrayList<String> errors = new ArrayList<>();
            ArrayList<Student> result = model.isExistIdList(number);
            if (number== null || number.isEmpty()) errors.add("Student ID is required.");
            else {
                if (number.matches("^[0-9]+$")) errors.add("Student ID contains numbers only.");
                if (number.length() > 8) errors.add("Invalid student ID.");
                if (result.isEmpty()) throw new Exception("No matching Student ID found.");
            }
            if (!errors.isEmpty()) throw new IllegalArgumentException(String.join("\n", errors));
            return result;
        }
        catch (Exception e) {
            System.out.println("Detect error: " + e.getMessage());
        }
        return null;
    }

    private ArrayList<Student> FindByYearOfBirth (String number) throws Exception {
        try {
            ArrayList<String> errors = new ArrayList<>();
            ArrayList<Student> result = model.isExistYearOfBirthList(number);
            if (number == null || number.isEmpty()) errors.add("Year of birth is required.");
            else {
                if (number.matches("^[0-9]+$")) errors.add("Year Of Birth contains numbers only.");
                if (number.length() != 4) errors.add("Invalid year of birth");
                if (result.isEmpty()) throw new Exception("No matching Year Of Birth found.");
            }
            if (!errors.isEmpty()) throw new IllegalArgumentException(String.join("\n", errors));
            return result;
        }
        catch (Exception e) {
            System.out.println("Detect error: " + e.getMessage());
        }
        return null;
    }

    private ArrayList<Student> FindByClassID (String keyword) throws Exception {
        try {
            ArrayList<String> errors = new ArrayList<>();
            ArrayList<Student> result = model.isExistClassID(keyword);
            if (keyword == null || keyword.isEmpty()) errors.add("ClassID is required.");
            else {
                if (keyword.matches("^[a-zA-Z0-9]+$")) errors.add("Class ID contains letters and numbers only.");
                if (result.isEmpty()) throw new Exception("No matching Class ID found.");
            }
            if (!errors.isEmpty()) throw new IllegalArgumentException(String.join("\n", errors));
            return result;
        }
        catch (Exception e) {
            System.out.println("Detect error: " + e.getMessage());
        }
        return null;
    }

    private ArrayList<Student> FindByAccommodation (String keyword) throws Exception {
        try {
            ArrayList<String> errors = new ArrayList<>();
            ArrayList<Student> result = model.isExistAccommodationList(keyword);
            if (keyword == null || keyword.isEmpty()) errors.add("Accommodation is required.");
            else {
                if (result.isEmpty()) throw new Exception("No matching Accommodation found.");
            }
            if (!errors.isEmpty()) throw new IllegalArgumentException(String.join("\n", errors));
            return result;
        }
        catch (Exception e) {
            System.out.println("Detect error: " + e.getMessage());
        }
        return null;
    }
}
