package controller;

import entity.Student;
import entity.StudentModel;

import java.time.LocalDate;
import java.util.ArrayList;

public class EditData {
//    String name;
//    String yearOfbirth;
//    String studentId;
//    String classId;
//    String residence;

    private StudentModel model = new StudentModel();
    
    public void CheckStudent(String name, String yearOfbirth, String studentId, String classId, String residence) {
        ArrayList<String> errors = new ArrayList<>();
        // Name
        if (name == null || name.isEmpty()) errors.add("Name is required.");
        else if (!name.matches("^[a-zA-Z\\s]+$")) errors.add("Name must contain only letters.");

        // YearOfBirth
        if (yearOfbirth.length() != 4 || Integer.parseInt(yearOfbirth) > LocalDate.now().getYear() || Integer.parseInt(yearOfbirth) < (LocalDate.now().getYear() - 100))
            errors.add("Invalid birth year.");
        else {
            if (yearOfbirth.isEmpty() || yearOfbirth == null) errors.add("Year of birth is required.");
            if (!yearOfbirth.matches("^[0-9]+$")) errors.add("Birth year must contain only digits.");
        }

        // StudentID
        if (studentId.isEmpty() || studentId == null) errors.add("ID is required.");
        else {
            if (!studentId.matches("\\d+")) errors.add("Invalid student ID.");
            if (studentId.length() != 8) errors.add("Student ID must be 8 digits.");
        }


        // ClassID
        if (classId.isEmpty() || classId == null) errors.add("Class ID is required.");
        else if (!classId.matches("^[A-Z0-9]+$"))
            errors.add("Class ID must contain only uppercase letters and digits.");


        // Accommodation
        if (residence == null || residence.isEmpty()) errors.add("Residence is required.");
        else if (!residence.matches("^[a-zA-Z0-9\\s]+$"))
            errors.add("Address can only contain letters and numbers.");

        // Return errors
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errors));
        }
    }

    public void AddStudent (Student student) throws Exception {
        if (model.isExistId(student.getId())) throw new Exception("Student ID already exists.");
        model.add(student);
    }

    public void DeleteStudent (Student student) throws Exception{
        if (!model.isExistId(student.getId())) throw new Exception("Student not found.");
        model.remove(student);
    }
}