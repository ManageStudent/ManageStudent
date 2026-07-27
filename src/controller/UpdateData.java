package controller;

import entity.Student;
import entity.StudentModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UpdateData {
    private StudentModel model = new StudentModel();
    public UpdateData (StudentModel model) {
        this.model = model;
    }


    public Student findStudent (String studentID) throws Exception {
        Student student = model.FindOnlyStudentID(studentID);
        if (student == null) throw new Exception("Student not found.");
        return student;
    }
    public void UpdateStudent (String studentID, Student newStudent) throws Exception {
        Student student = model.FindOnlyStudentID(studentID);
        ArrayList<String> errors = new ArrayList<>();

        String idToCheck = newStudent.getId().isEmpty() ? student.getId() : newStudent.getId();
        if (!idToCheck.matches("^[0-9]+$")) {
            errors.add("Student ID contains numbers only.");
        } else if (!idToCheck.matches("\\d{8}")) {
            errors.add("Student ID must contain 8 digits.");
        } else if (!idToCheck.equals(student.getId()) && model.isExistId(newStudent.getId())) {
            errors.add("Student ID already exists.");
        } else {
            student.setId(idToCheck);
        }

        // Xử lý Name
        String nameToCheck = newStudent.getName().isEmpty() ? student.getName() : newStudent.getName();
        if (!nameToCheck.matches("^[\\p{L}\\s]+$")) {
            errors.add("Name contains letters only.");
        } else {
            student.setName(nameToCheck);
        }

        // Xử lý Year of Birth
        String yobToCheck = newStudent.getYearOfBirth().isEmpty() ? student.getYearOfBirth() : newStudent.getYearOfBirth();
        if (!yobToCheck.matches("^\\d{4}$")) {
            errors.add("Year of Birth must be 4 digits.");
        } else if (!yobToCheck.matches("^[0-9]+$")) errors.add("Birth year contains numbers only.");
        else if (Integer.parseInt(yobToCheck) > LocalDate.now().getYear() || Integer.parseInt(yobToCheck) < (LocalDate.now().getYear() - 100)) errors.add("Invalid birth year.");
        else {
            student.setYearOfBirth(yobToCheck);
        }

        // Xử lý Class ID
        String classToCheck = newStudent.getClassId().isEmpty() ? student.getClassId() : newStudent.getClassId();
        if (!classToCheck.matches("^[a-zA-Z0-9]+$")) {
            errors.add("Class ID must be alphanumeric.");
        } else {
            student.setClassId(classToCheck);
        }

        // Xử lý Accommodation
        String accToCheck = newStudent.getAccommodation().isEmpty() ? student.getAccommodation() : newStudent.getAccommodation();
        if (!accToCheck.matches("^[a-zA-Z0-9]+$")) {
            errors.add("Accommodation must be alphanumeric.");
        } else {
            student.setAccommodation(accToCheck);
        }

        // Nếu có lỗi thì ném exception
        if (!errors.isEmpty()) {
            throw new Exception(String.join("\n", errors));
        }
    }
}
