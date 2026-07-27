package UI;

import controller.*;
import entity.Student;
import entity.StudentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentUI {
    private final int EXIT_CHOICE = 0;
    private Scanner scanner;
    private EditData editDataController;
    private Find findController;
    private StudentModel sharedModel;
    private Show display;
    private Sort sort;

    public StudentUI() {
        this.scanner = new Scanner(System.in);
        this.sharedModel = new StudentModel();
        this.editDataController = new EditData(this.sharedModel);
        this.findController = new Find(this.sharedModel);
        this.display = new Show(this.sharedModel);
        this.sort = new Sort(this.sharedModel);
        this.sharedModel.loadFromFile("../ManageStudent/src/data/students.txt");
    }

    public void start() throws Exception {
        boolean isRunning = true;
        while (isRunning) {
            DisplayMenu();
            int userChoice = GetIntInput("Enter your choice: ");

            switch (userChoice) {
                case 1:
                    InputStudentInfo();
                    break;
                case 2:
                    DeleteStudent();
                    break;
                case 3:
                    DisplayStudentList();
                    break;
                case 4:
                    UpdateStudentInfo();
                    break;
                case 5:
                    SearchStudent();
                    break;
                case 6:
                    SortMenu();
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

    private void DisplayMenu() {
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        System.out.println("1. Add new student");
        System.out.println("2. Delete student");
        System.out.println("3. Display student list");
        System.out.println("4. Update student information");
        System.out.println("5. Search student");
        System.out.println("6. Sort student list");
        System.out.println("0. Exit");
    }

    private int GetIntInput(String prompt) {
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

    private void PrintStudentTable(List<Student> list) {
        if (list == null || list.isEmpty()) return;

        int maxId = 10;
        int maxName = 15;
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

    // 1. ADD STUDENT FEATURE
    private void InputStudentInfo() {
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
            accommodationChoice = GetIntInput("Choose residence (1 - Dormitory, 2 - Rented House): ");
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

    // 2. DELETE FEATURE
    private void DeleteStudent() {
        while (true) {
            System.out.print("Enter student ID to delete (type '0' to cancel): ");
            String deleteID = scanner.nextLine();
            if (deleteID.equals("0")) {
                break;
            }
            try {
                Student student = findController.FindByStudentId(deleteID).isEmpty() ? null : findController.FindByStudentId(deleteID).get(0);
                if (student == null) {
                    System.out.println("Student not found.");
                    continue;
                }
                System.out.println("Please confirm the information before deleting:");
                List<Student> students = new ArrayList<>();
                students.add(student);
                PrintStudentTable(students);

                System.out.println("\n1. Confirm.");
                System.out.println("2. Cancel.");
                System.out.print("Your choice: ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        editDataController.DeleteStudent(student);
                        System.out.println("Delete student successful!");
                        break;
                    case "2":
                        System.out.println("Deletion canceled.");
                        break;
                    default:
                        System.out.println("Invalid value. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // 3. DISPLAY LIST FEATURE
    private void DisplayStudentList() {
        List<Student> students = display.GetAllStudents();
        if (students.isEmpty()) {
            System.out.println("=> The student list is currently empty.");
            return;
        }
        System.out.println("\n--- STUDENT LIST ---");
        PrintStudentTable(students);
    }

    // 4. UPDATE INFORMATION FEATURE
    private void UpdateStudentInfo() throws Exception {

        System.out.println("\n--- UPDATE STUDENT INFORMATION ---");
        System.out.print("Enter student ID to edit: ");
        String targetId = scanner.nextLine();
        try {
            StudentModel model = new StudentModel();
            UpdateData data = new UpdateData(model);
            Student oldStudent = data.FindStudent(targetId);
            Student newStudent = new Student();
            if (oldStudent == null) {
                System.out.println("Student not found.");
                return;
            }
            else {
                System.out.print("New ID (" + oldStudent.getId() + "): ");
                String idInput = scanner.nextLine();
                newStudent.setId(idInput.isEmpty() ? oldStudent.getId() : idInput);

                System.out.print("New name (" + oldStudent.getName() + "): ");
                String nameInput = scanner.nextLine();
                newStudent.setName(nameInput.isEmpty() ? oldStudent.getName() : nameInput);

                System.out.print("New birth year (" + oldStudent.getYearOfBirth() + "): ");
                String yobInput = scanner.nextLine();
                newStudent.setYearOfBirth(yobInput.isEmpty() ? oldStudent.getYearOfBirth() : yobInput);

                System.out.print("New class ID (" + oldStudent.getClassId() + "): ");
                String classInput = scanner.nextLine();
                newStudent.setClassId(classInput.isEmpty() ? oldStudent.getClassId() : classInput);

                System.out.print("New accommodation (" + oldStudent.getAccommodation() + "): ");
                String accInput = scanner.nextLine();
                newStudent.setAccommodation(accInput.isEmpty() ? oldStudent.getAccommodation() : accInput);

                data.UpdateStudent(targetId, newStudent);
                System.out.println("Update successful!");
            }
        }
        catch (Exception e) {
            System.out.println("Detect error: " + e.getMessage());
            return;
        }
    }


    // 5. SEARCH FEATURE
    private void SearchStudent() {

        while (true) {
            System.out.println("\n--- SEARCH STUDENT ---");
            System.out.println("1. Search by Name");
            System.out.println("2. Search by Student ID");
            System.out.println("3. Search by Year of Birth");
            System.out.println("4. Search by Class ID");
            System.out.println("5. Search by Residence");
            System.out.println("0. Exit");
            int searchChoice = GetIntInput("Choose search method: ");

            if (searchChoice == 0) {
                System.out.println("Exiting search...");
                break;
            }

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
                    PrintStudentTable(results);
                }
            } catch (Exception e) {
                System.out.println("=> Cannot search: " + e.getMessage());
            }
        }
    }

    //6. SORT FEATURE
    private void DisplaySortedList() {
        ArrayList<Student> students = sort.GetStudentList();

        if (students.isEmpty()) {
            System.out.println("List have no students.");
            return;
        }

        System.out.println("\n--- SORT STUDENT LIST ---");
        PrintStudentTable(students);
    }

    private void SortMenu() {
        while (true) {
            System.out.println("\n--- SORT STUDENT ---");
            System.out.println("1. Sort by Name");
            System.out.println("2. Sort by Student ID");
            System.out.println("3. Sort by Year of Birth");
            System.out.println("4. Sort by Class ID");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                System.out.println("Returning to main menu...");
                break;
            }

            System.out.print("Sort order (ASC/DESC): ");
            String orderInput = scanner.nextLine().trim().toUpperCase();
            StudentModel.Order order = orderInput.equals("DESC") ? StudentModel.Order.DESC : StudentModel.Order.ASC;

            switch (choice) {
                case 1 -> sort.SortByName(order);
                case 2 -> sort.SortByStudentID(order);
                case 3 -> sort.SortByYearOfBirth(order);
                case 4 -> sort.SortByClassID(order);
                default -> System.out.println("Invalid choice!");
            }

            DisplaySortedList();
        }
    }
}