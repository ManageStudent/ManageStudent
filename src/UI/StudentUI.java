package UI;

import controller.EditData;
import controller.Find;
import controller.Sort;
import controller.UpdateData;
import entity.Student;
import entity.StudentModel;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentUI {
    private final int EXIT_CHOICE = 0;
    private Scanner scanner;

    private EditData editDataController;
    private Find findController;
    private Sort sortController;
    private UpdateData updateController;
    private StudentModel sharedModel;

    public StudentUI() {
        this.scanner = new Scanner(System.in);
        this.sharedModel = new StudentModel();

        this.sharedModel.loadFromFile("../ManageStudent/src/data/students.txt");

        this.findController = new Find(this.sharedModel);
        this.sortController = new Sort(this.sharedModel);
        this.editDataController = new EditData(this.sharedModel);
        this.updateController = new UpdateData(this.sharedModel);
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
                    sortStudentList();
                    break;
                case EXIT_CHOICE:
                    sharedModel.saveToFile("students.txt");

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
        ArrayList<Student> list = sharedModel.getStudentList();
        if (list.isEmpty()) {
            System.out.println("=> The student list is currently empty.");
        } else {
            System.out.println("---------------------------------STUDENT LIST---------------------------------");
            printStudentTable(list);
            System.out.println("------------------------------------------------------------------------------");
        }
    }

    // 3. UPDATE INFORMATION FEATURE
    private void updateStudentInfo() {
        System.out.println("\n--- UPDATE STUDENT INFORMATION ---");
        System.out.print("Enter student ID to edit: ");
        String targetId = scanner.nextLine();

        try {
            // Kiểm tra xem sinh viên có tồn tại không
            Student currentStudent = updateController.findStudent(targetId);
            System.out.println("=> Student found: " + currentStudent.getName());

            boolean isEditing = true;
            while (isEditing) {
                System.out.println("\n>> Editing student with ID: " + targetId);
                System.out.println("1. Edit Full Name");
                System.out.println("2. Edit Year of Birth");
                System.out.println("3. Edit Class ID");
                System.out.println("4. Edit Residence");
                System.out.println("0. Done & Return to Main Menu");

                int editChoice = getIntInput("Choose information to edit: ");

                if (editChoice == 0) {
                    isEditing = false;
                    System.out.println("Exited editing mode!");
                    continue;
                }

                // Khởi tạo các giá trị rỗng (UpdateData.java thiết kế chuỗi rỗng = không update)
                String newName = "", newYear = "", newClassId = "", newAccommodation = "";

                switch (editChoice) {
                    case 1:
                        System.out.print("Enter new Full Name: ");
                        newName = scanner.nextLine();
                        break;
                    case 2:
                        System.out.print("Enter new Year of Birth (YYYY): ");
                        newYear = scanner.nextLine();
                        break;
                    case 3:
                        System.out.print("Enter new Class ID: ");
                        newClassId = scanner.nextLine();
                        break;
                    case 4:
                        System.out.print("Enter new Residence: ");
                        newAccommodation = scanner.nextLine();
                        break;
                    default:
                        System.out.println("Invalid choice, please select again.");
                        continue;
                }


                Student updatePayload = new Student(newName, newYear, "", newClassId, newAccommodation);

                try {
                    updateController.UpdateStudent(targetId, updatePayload);
                    System.out.println("=> UPDATE SUCCESSFUL!");
                } catch (Exception e) {
                    System.out.println("=> UPDATE FAILED:\n" + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("=> " + e.getMessage());
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
    // 5. SORT FEATURE
    private void sortStudentList() {
        System.out.println("\n--- SORT STUDENT LIST ---");
        System.out.println("1. Sort by Name");
        System.out.println("2. Sort by Student ID");
        System.out.println("3. Sort by Year of Birth");
        System.out.println("4. Sort by Class ID");
        int sortChoice = getIntInput("Choose sort criteria: ");

        if (sortChoice < 1 || sortChoice > 4) {
            System.out.println("Invalid choice!");
            return;
        }

        System.out.println("1. Ascending (A-Z, 0-9)");
        System.out.println("2. Descending (Z-A, 9-0)");
        int orderChoice = getIntInput("Choose order: ");

        if (orderChoice < 1 || orderChoice > 2) {
            System.out.println("Invalid choice!");
            return;
        }

        StudentModel.Order order = (orderChoice == 1) ? StudentModel.Order.ASC : StudentModel.Order.DESC;

        switch (sortChoice) {
            case 1: sortController.SortByName(order); break;
            case 2: sortController.SortByStudentID(order); break;
            case 3: sortController.SortByYearOfBirth(order); break;
            case 4: sortController.SortByClassID(order); break;
        }

        System.out.println("=> SORT SUCCESSFUL!");

        displayStudentList();
    }

    //Create Table
    private void printStudentTable(ArrayList<Student> list) {
        if (list == null || list.isEmpty()) return;

        int maxId = 10;
        int maxName = 9;
        int maxYear = 4;
        int maxClass = 8;
        int maxRes = 9;

        for (Student s : list) {
            if (s.getId().length() > maxId) maxId = s.getId().length();
            if (s.getName().length() > maxName) maxName = s.getName().length();
            if (s.getYearOfBirth().length() > maxYear) maxYear = s.getYearOfBirth().length();
            if (s.getClassId().length() > maxClass) maxClass = s.getClassId().length();
            if (s.getAccommodation().length() > maxRes) maxRes = s.getAccommodation().length();
        }

        String HEAD = "┌" + "─".repeat(maxId + 2) + "┬" + "─".repeat(maxName + 2) + "┬" + "─".repeat(maxYear + 2) + "┬" + "─".repeat(maxClass + 2) + "┬" + "─".repeat(maxRes + 2) + "┐";
        String MID  = "├" + "─".repeat(maxId + 2) + "┼" + "─".repeat(maxName + 2) + "┼" + "─".repeat(maxYear + 2) + "┼" + "─".repeat(maxClass + 2) + "┼" + "─".repeat(maxRes + 2) + "┤";
        String BOT  = "└" + "─".repeat(maxId + 2) + "┴" + "─".repeat(maxName + 2) + "┴" + "─".repeat(maxYear + 2) + "┴" + "─".repeat(maxClass + 2) + "┴" + "─".repeat(maxRes + 2) + "┘";

        String format = "│ %-" + maxId + "s │ %-" + maxName + "s │ %-" + maxYear + "s │ %-" + maxClass + "s │ %-" + maxRes + "s │%n";

        System.out.println(HEAD);
        System.out.printf(format, "Student ID", "Full Name", "Year", "Class ID", "Residence");
        System.out.println(MID);

        for (Student s : list) {
            System.out.printf(format, s.getId(), s.getName(), s.getYearOfBirth(), s.getClassId(), s.getAccommodation());
        }

        System.out.println(BOT);
    }
}