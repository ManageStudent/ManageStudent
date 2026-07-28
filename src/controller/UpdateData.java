package controller;

import entity.Student;
import entity.StudentModel;
import java.util.ArrayList;

public class UpdateData {
    private StudentModel model;

    public UpdateData (StudentModel model) {
        this.model = model;
    }

    public Student findStudent(String studentID) throws Exception {
        Student student = model.findOnlyStudentID(studentID);
        if (student == null) throw new Exception("Student not found.");
        return student;
    }

    public void updateStudent(String studentID, Student newStudent) throws Exception {
        Student student = model.findOnlyStudentID(studentID);
        ArrayList<String> errors = new ArrayList<>();

        String idToCheck = (newStudent.getId() == null || newStudent.getId().isEmpty()) ? student.getId() : newStudent.getId();
        if (idToCheck.contains(" ")) {
            errors.add("Student ID cannot contain spaces.");
        } else if (!idToCheck.matches("^[0-9]+$")) {
            errors.add("Student ID contains numbers only.");
        } else if (!idToCheck.matches("\\d{8}")) {
            errors.add("Student ID must contain 8 digits.");
        } else if (!idToCheck.equals(student.getId()) && model.isExistId(newStudent.getId())) {
            errors.add("Student ID already exists.");
        }

        String nameToCheck = (newStudent.getName() == null || newStudent.getName().isEmpty()) ? student.getName() : newStudent.getName();
        nameToCheck = nameToCheck.trim().replaceAll("\\s+", " ");
        boolean hasNumberOrSpecial = nameToCheck.matches(".*[^\\p{L}\\s].*");
        boolean hasAccented = nameToCheck.matches(".*[\\p{L}&&[^a-zA-Z]].*");

        if (hasNumberOrSpecial && hasAccented) {
            errors.add("Name must contain only letters and no accented characters allowed.");
        } else if (hasNumberOrSpecial) {
            errors.add("Name must contain only letters.");
        } else if (hasAccented) {
            errors.add("No accented characters allowed.");
        }

        String yobToCheck = (newStudent.getYearOfBirth() == null || newStudent.getYearOfBirth().isEmpty()) ? student.getYearOfBirth() : newStudent.getYearOfBirth();
        if (yobToCheck.contains(" ")) {
            errors.add("Year of birth cannot contain spaces.");
        } else if (!yobToCheck.matches("^\\d{4}$") || !yobToCheck.matches("^[0-9]+$")) {
            errors.add("Year of Birth must be 4 digits containing numbers only.");
        } else {
            int year = Integer.parseInt(yobToCheck);
            if (year < 2001 || year > 2008) {
                errors.add("Only birth years from 2001 to 2008 are allowed.");
            }
        }

        String classToCheck = (newStudent.getClassId() == null || newStudent.getClassId().isEmpty()) ? student.getClassId() : newStudent.getClassId();
        if (classToCheck.contains(" ")) {
            errors.add("Class ID cannot contain spaces.");
        } else if (!classToCheck.matches("^[A-Z0-9]+$")) {
            errors.add("Class ID must contain only uppercase letters and digits.");
        }

        String accToCheck = (newStudent.getAccommodation() == null || newStudent.getAccommodation().isEmpty()) ? student.getAccommodation() : newStudent.getAccommodation();
        if (!accToCheck.matches("^[a-zA-Z\\s]+$")) {
            errors.add("Accommodation must be alphanumeric.");
        }

        if (!errors.isEmpty()) {
            throw new Exception(String.join("\n", errors));
        }

        student.setId(idToCheck);
        student.setName(nameToCheck);
        student.setYearOfBirth(yobToCheck);
        student.setClassId(classToCheck);
        student.setAccommodation(accToCheck);
    }
}