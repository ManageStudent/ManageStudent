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

        if (!newStudent.getId().isEmpty()) {
            if (!newStudent.getId().matches("^[0-9]+$")) errors.add("Student ID contains numbers only.");
            else if (!newStudent.getId().matches("\\d{8}")) errors.add("Student ID must contain 8 digits.");
            else if (!newStudent.getId().equals(student.getId()) && model.isExistId(newStudent.getId())) errors.add("Student ID already exists.");
        }

        if (!newStudent.getName().isEmpty()) {
            if (!newStudent.getName().matches("^[a-zA-Z\\s]")) errors.add("Name contains letters only.");
        }

        if (!newStudent.getYearOfBirth().isEmpty()) {
            if (!newStudent.getYearOfBirth().matches("\\d{4}")) errors.add("Birth year must contain 4 digits.");
            else if (!newStudent.getYearOfBirth().matches("^[0-9]+$")) errors.add("Birth year contains numbers only.");
            else if (Integer.parseInt(newStudent.getYearOfBirth()) > LocalDate.now().getYear() || Integer.parseInt(newStudent.getYearOfBirth()) < (LocalDate.now().getYear() - 100))
                errors.add("Invalid birth year.");
        }

        if (!newStudent.getClassId().isEmpty()) {
            if (!newStudent.getClassId().matches("^[a-zA-Z0-9]+$")) errors.add("Class ID contains letters and numbers.");
        }

        if (!errors.isEmpty()) throw new IllegalArgumentException(String.join("\n", errors));

        // Update
        if (!newStudent.getId().isEmpty()) student.setId(newStudent.getId());
        if (!newStudent.getName().isEmpty()) student.setName(newStudent.getName());
        if (!newStudent.getYearOfBirth().isEmpty()) student.setYearOfBirth(newStudent.getYearOfBirth());
        if (!newStudent.getClassId().isEmpty()) student.setClassId(newStudent.getClassId());
        if (!newStudent.getAccommodation().isEmpty()) student.setChoO(newStudent.getAccommodation());
    }
}
