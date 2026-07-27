package controller;

import entity.Student;
import entity.StudentModel;

import java.time.LocalDate;
import java.util.ArrayList;

public class EditData {

    private StudentModel model;

    public EditData(StudentModel sharedModel) {
        this.model = sharedModel;
    }

    public void checkStudent(Student student) throws IllegalArgumentException {
        ArrayList<String> errors = new ArrayList<>();

        // Name
        if (student.getName() != null && !student.getName().isEmpty()) {
            if (!student.getName().matches("^[a-zA-Z\\s]+$")) {
                errors.add("Name must contain only letters.");
            }
        }

        // YearOfBirth
        if (student.getYearOfBirth() != null && !student.getYearOfBirth().isEmpty()) {
            if (!student.getYearOfBirth().matches("^[0-9]+$")) {
                errors.add("Birth year must contain only numbers.");
            } else {
                int year = Integer.parseInt(student.getYearOfBirth());
                int currentYear = LocalDate.now().getYear();
                if (student.getYearOfBirth().length() != 4 ||
                        year > currentYear ||
                        year < currentYear - 100) {
                    errors.add("Invalid birth year.");
                }
            }
        }

        // StudentID
        if (student.getId() != null && !student.getId().isEmpty()) {
            if (!student.getId().matches("\\d+")) {
                errors.add("Invalid student ID.");
            } else if (student.getId().length() != 8) {
                errors.add("Student ID must be 8 digits.");
            }
        }

        // ClassID
        if (student.getClassId() != null && !student.getClassId().isEmpty()) {
            if (!student.getClassId().matches("^[A-Z0-9]+$")) {
                errors.add("Class ID must contain only uppercase letters and digits.");
            }
        }

        // Accommodation
        if (student.getAccommodation() != null && !student.getAccommodation().isEmpty()) {
            if (!student.getAccommodation().matches("^[a-zA-Z0-9\\s]+$")) {
                errors.add("Address can only contain letters and numbers.");
            }
        }

        // Return errors
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errors));
        }
    }

    public void addStudent(Student student) throws Exception {
        if (model.isExistId(student.getId())) {
            throw new Exception("Student ID already exists.");
        }
        model.add(student);
    }

    public void deleteStudent(Student student) throws Exception {
        if (!model.isExistId(student.getId())) {
            throw new Exception("Student not found.");
        }
        model.remove(student);
    }
}