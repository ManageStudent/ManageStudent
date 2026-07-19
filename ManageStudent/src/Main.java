import controller.EditData;
import entity.Student;

import java.util.Scanner;

import UI.UI;
public class Main {
    public static void main(String[] args) {
        // Test
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Year: ");
        String yearOfBirth = sc.nextLine();
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Class ID: ");
        String classId = sc.nextLine();
        System.out.print("Residence: ");
        String accommodation = sc.nextLine();

        Student st = new Student(name, yearOfBirth, id, classId, accommodation);
        EditData e = new EditData();
        e.CheckStudent(st.getName(), st.getYearOfBirth(), st.getId(), st.getClassId(), st.getAccommodation());
    }
}