package controller;

import entity.Student;
import entity.StudentModel;

import java.util.ArrayList;

public class Sort {
    private StudentModel model;

    public Sort(StudentModel sharedModel) {
        this.model = sharedModel;
    }

    public void sortByName(StudentModel.Order order) {
        
        model.sortNameList(order);
    }

    public void sortByStudentID(StudentModel.Order order) {
        model.sortStudentIDList(order);
    }

    public void sortByYearOfBirth(StudentModel.Order order) {
        model.sortYearOfBirthList(order);
    }

    public void sortByClassID(StudentModel.Order order) {
        model.sortClassID(order);
    }

    public ArrayList<Student> getStudentList() {
        return model.getStudentList();
    }
}
