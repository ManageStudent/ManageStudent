package controller;

import entity.Student;
import entity.StudentModel;

import java.util.List;

public class Show {
    private StudentModel model;

    public Show(StudentModel model) {
        this.model = model;
    }
    public List<Student> getAllStudents() {
        return model.getAllStudents();
    }
}
