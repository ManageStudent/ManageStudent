package UI;

import controller.EditData;
import controller.Find;
import entity.Student;
import entity.StudentModel;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentUI {
    private final int EXIT_CHOICE = 0;
    private Scanner scanner;

    private EditData editDataController;
    private Find findController;
    private StudentModel sharedModel;

    public StudentUI() {
        this.scanner = new Scanner(System.in);

        this.sharedModel = new StudentModel();

        this.editDataController = new EditData(this.sharedModel);
        this.findController = new Find(this.sharedModel);
    }

    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            displayMenu();
            int userChoice = getIntInput("Enter your choice: ");

            switch (userChoice) {
                case 1:
                    inputStudentInfo();
                    break;
                case 2:
                    displayStudentList();
                    break;
                case 3:
                    updateStudentInfo();
                    break;
                case 4:
                    searchStudent();
                    break;
                case 5:
                    System.out.println("=> Sort feature is waiting for Controller to be completed.");
                    break;
                case EXIT_CHOICE:
                    isRunning = false;
                    System.out.println("Exited program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        System.out.println("1. Add new student");
        System.out.println("2. Display student list");
        System.out.println("3. Update student information");
        System.out.println("4. Search student");
        System.out.println("5. Sort student list");
        System.out.println("0. Exit");
    }

    private int getIntInput(String prompt) {
        int value = -1;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print(prompt);
                value = Integer.parseInt(scanner.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter an integer only! Try again.");
            }
        }
        return value;
    }

    // 1. ADD STUDENT FEATURE
    private void inputStudentInfo() {
        System.out.println("\n--- ENTER STUDENT INFORMATION ---");

        System.out.print("Enter full name: ");
        String name = scanner.nextLine();

        System.out.print("Enter year of birth (YYYY): ");
        String yearOfBirthStr = scanner.nextLine();

        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter class ID: ");
        String classId = scanner.nextLine();

        int accommodationChoice = -1;
        while (accommodationChoice != 1 && accommodationChoice != 2) {
            accommodationChoice = getIntInput("Choose residence (1 - Dormitory, 2 - Rented House): ");
            if (accommodationChoice != 1 && accommodationChoice != 2) {
                System.out.println("Invalid choice. Please choose 1 or 2.");
            }
        }
        String accommodation = (accommodationChoice == 1) ? "Dormitory" : "Rented House";

        System.out.println("\n[SYSTEM] Checking and saving data...");

        try {
            editDataController.CheckStudent(name, yearOfBirthStr, id, classId, accommodation);

            Student newStudent = new Student(name, yearOfBirthStr, id, classId, accommodation);

            editDataController.AddStudent(newStudent);
            System.out.println("=> ADD STUDENT SUCCESSFULLY!");

        } catch (Exception e) {
            System.out.println("=> FAILED. Invalid data:\n" + e.getMessage());
        }
    }

    // 2. DISPLAY LIST FEATURE
    private void displayStudentList() {
        System.out.println("\n--- STUDENT LIST ---");
        System.out.println("=> Feature is waiting for data connection from Controller.");
    }

    // 3. UPDATE INFORMATION FEATURE
    private void updateStudentInfo() {
        System.out.println("\n--- UPDATE STUDENT INFORMATION ---");
        System.out.print("Enter student ID to edit: ");
        String targetId = scanner.nextLine();

        boolean isEditing = true;
        while (isEditing) {
            System.out.println("\n>> Editing student with ID: " + targetId);
            System.out.println("1. Edit Full Name");
            System.out.println("2. Edit Year of Birth");
            System.out.println("3. Edit Class ID");
            System.out.println("4. Edit Residence");
            System.out.println("0. Done & Return to Main Menu");

            int editChoice = getIntInput("Choose information to edit: ");

            switch (editChoice) {
                case 1:
                case 2:
                case 3:
                case 4:
                    System.out.println("=> Request recorded. Feature is waiting for UpdateData.java to be completed.");
                    break;
                case 0:
                    isEditing = false;
                    System.out.println("Exited editing mode!");
                    break;
                default:
                    System.out.println("Invalid choice, please select again.");
            }
        }
    }

    // 4. SEARCH FEATURE
    private void searchStudent() {
        System.out.println("\n--- SEARCH STUDENT ---");
        System.out.println("1. Search by Name");
        System.out.println("2. Search by Student ID");
        System.out.println("3. Search by Year of Birth");
        System.out.println("4. Search by Class ID");
        System.out.println("5. Search by Residence");
        int searchChoice = getIntInput("Choose search method: ");

        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();

        try {
            ArrayList<Student> results = null;

            switch (searchChoice) {
                case 1:
                    results = findController.FindByName(keyword);
                    break;
                case 2:
                    results = findController.FindByStudentId(keyword);
                    break;
                case 3:
                    results = findController.FindByYearOfBirth(keyword);
                    break;
                case 4:
                    results = findController.FindByClassID(keyword);
                    break;
                case 5:
                    results = findController.FindByAccommodation(keyword);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            if (results != null && !results.isEmpty()) {
                System.out.println("\n=> FOUND " + results.size() + " RESULT(S):");
                System.out.println("--------------------------------------------------");
                for (Student s : results) {
                    System.out.println("Student ID: " + s.getId() + " | Name: " + s.getName() + " | Year of Birth: " + s.getYearOfBirth() + " | Class: " + s.getClassId() + " | Residence: " + s.getAccommodation());
                }
                System.out.println("--------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("=> Cannot search: " + e.getMessage());
        }
    }
}