package entity;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

// Model --> Controller

public class StudentModel {

    private static ArrayList<Student> studentList = new ArrayList<>();

    // Model --> EditData.java
    public void add(Student student) {
        studentList.add(student);
    }

    public void remove(Student student) {
        studentList.remove(student);
    }

    public boolean isExistId(String id) {
        for (Student s : studentList) {
            if (s.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    // Model --> Find.java
    public ArrayList<Student> isExistNameList(String keyword) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Student> isExistIdList(String number) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getId().contains(number)) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Student> isExistYearOfBirthList(String number) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getYearOfBirth().contains(number)) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Student> isExistClassID(String keyword) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getClassId().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    public ArrayList<Student> isExistAccommodationList(String keyword) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getAccommodation().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    // Model --> Sort.java
    public enum Order {
        ASC, // Ascending
        DESC // Descending
    }

    public void SortNameList (Order order) {
        studentList.sort((s1,s2) -> {
            String name1 = s1.getName().trim();
            String name2 = s2.getName().trim();
            String lastName1 = name1.substring(name1.lastIndexOf(" ") + 1);
            String lastName2 = name2.substring(name2.lastIndexOf(" ") + 1);

            int result = lastName1.compareToIgnoreCase(lastName2);
            if (result == 0) {
                result = name1.compareToIgnoreCase(name2);
            }
            return order == Order.ASC ? result : -result;
        });

    }

    public void SortStudentIDList (Order order) {
        studentList.sort((s1, s2) -> {
            int result = s1.getId().compareToIgnoreCase(s2.getId());
            return order == Order.ASC ? result : -result;
        });
    }

    public void SortYearOfBirthList (Order order) {
        studentList.sort((s1, s2) -> {
            int result = s1.getYearOfBirth().compareToIgnoreCase(s2.getYearOfBirth());
            return order == Order.ASC ? result : -result;
        });
    }

    public void SortClassID (Order order) {
        studentList.sort((s1, s2) -> {
            int result = s1.getClassId().compareToIgnoreCase(s2.getClassId());
            return order == Order.ASC ? result : -result;
        });
    }

    public ArrayList<Student> GetStudentList() {
        return studentList;
    }

    // Model --> UpdateData.java
    public Student FindOnlyStudentID (String studentID) {
        for (Student s : studentList) {
            if (s.getId().equals(studentID)) {
                return s;
            }
        }
        return null;
    }

    // Model --> Show.java
    public List<Student> GetAllStudents() {
        return studentList;
    }

    //Function Read
    public void loadFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    Student s = new Student(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim());
                    studentList.add(s);
                }
            }
            System.out.println("Loaded sample data from file successfully.");
        } catch (Exception e) {
            System.out.println("No existing data file found. Starting fresh.");
        }
    }

    //Function Write
    public void saveToFile(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Student s : studentList) {
                String line = s.getName() + "," + s.getYearOfBirth() + "," + s.getId() + "," + s.getClassId() + "," + s.getAccommodation();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Saved data to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}