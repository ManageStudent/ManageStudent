package controller;

import entity.Student;
import entity.StudentModel;

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
            String formattedName = student.getName().trim().replaceAll("\\s+", " ");
            student.setName(formattedName);

            boolean hasNumberOrSpecial = formattedName.matches(".*[^\\p{L}\\s].*");
            boolean hasAccented = formattedName.matches(".*[\\p{L}&&[^a-zA-Z]].*");

            if (hasNumberOrSpecial && hasAccented) {
                errors.add("Name must contain only letters and no accented characters allowed.");
            } else if (hasNumberOrSpecial) {
                errors.add("Name must contain only letters.");
            } else if (hasAccented) {
                errors.add("No accented characters allowed.");
            }
        }

        // YearOfBirth
        if (student.getYearOfBirth() != null && !student.getYearOfBirth().isEmpty()) {
            if (student.getYearOfBirth().contains(" ")) {
                errors.add("Year of birth cannot contain spaces.");
            } else if (!student.getYearOfBirth().matches("^[0-9]+$")) {
                errors.add("Birth year must contain only numbers.");
            } else {
                int year = Integer.parseInt(student.getYearOfBirth());
                if (student.getYearOfBirth().length() != 4 || year < 1970 || year > 2008) {
                    errors.add("Only birth years from 1970 to 2008 are allowed.");
                }
            }
        }

        // StudentID
        if (student.getId() != null && !student.getId().isEmpty()) {
            if (student.getId().contains(" ")) {
                errors.add("Student ID cannot contain spaces.");
            } else if (!student.getId().matches("\\d+")) {
                errors.add("Invalid student ID.");
            } else if (student.getId().length() != 8) {
                errors.add("Student ID must be 8 digits.");
            }
        }

        // ClassID
        if (student.getClassId() != null && !student.getClassId().isEmpty()) {
            if (student.getClassId().contains(" ")) {
                errors.add("Class ID cannot contain spaces.");
            } else if (!student.getClassId().matches("^[A-Z0-9]+$")) {
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