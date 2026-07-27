package controller;

import entity.Student;
import entity.StudentModel;

import java.util.ArrayList;

public class Sort {
    private StudentModel model;

    public Sort(StudentModel sharedModel) {
        this.model = sharedModel;
    }

    public void SortByName (StudentModel.Order order) {
        
        model.SortNameList(order);
    }

    public void SortByStudentID (StudentModel.Order order) {
        model.SortStudentIDList(order);
    }

    public void SortByYearOfBirth (StudentModel.Order order) {
        model.SortYearOfBirthList(order);
    }

    public void SortByClassID (StudentModel.Order order) {
        model.SortClassID(order);
    }

    public ArrayList<Student> GetStudentList() {
        return model.GetStudentList();
    }
}
