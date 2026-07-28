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
    private String filePath = "./src/data/students.txt";

    public StudentUI() {
        this.scanner = new Scanner(System.in);
        this.sharedModel = new StudentModel();
        this.editDataController = new EditData(this.sharedModel);
        this.findController = new Find(this.sharedModel);
        this.display = new Show(this.sharedModel);
        this.sort = new Sort(this.sharedModel);
        this.sharedModel.loadFromFile(filePath);
    }

    public void start() throws Exception {
        boolean isRunning = true;
        while (isRunning) {
            displayMenu();
            int userChoice = getIntInput("Enter your choice: ");

            switch (userChoice) {
                case 1:
                    inputStudentInfo();
                    break;
                case 2:
                    deleteStudent();
                    break;
                case 3:
                    displayStudentList();
                    break;
                case 4:
                    updateStudentInfo();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    sortMenu();
                    break;
                case EXIT_CHOICE:
                    sharedModel.saveToFile(filePath);

                    isRunning = false;
                    System.out.println("Exited program. Goodbye!");
                    break;
                default:
                    System.out.println("Error: Invalid choice! Please choose an option from 1 to 6.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        System.out.println("1. Add new student");
        System.out.println("2. Delete student");
        System.out.println("3. Display student list");
        System.out.println("4. Update student information");
        System.out.println("5. Search student");
        System.out.println("6. Sort student list");
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

    private void printStudentTable(List<Student> list) {
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
    private void inputStudentInfo() throws Exception {
        System.out.println("\n--- ENTER STUDENT INFORMATION ---");
        Student newStudent = new Student("", "","","","");

        while (true) {
            System.out.print("Enter full name: ");
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Error: Name cannot be empty. Please try again.");
                continue;
            }
            newStudent.setName(input);
            try {
                editDataController.checkStudent(newStudent);
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
            }
        }

        while (true) {
            System.out.print("Enter year of birth (YYYY): ");
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Error: Year of birth cannot be empty. Please try again.");
                continue;
            }
            newStudent.setYearOfBirth(input);
            try {
                editDataController.checkStudent(newStudent);
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
            }
        }

        while (true) {
            System.out.print("Enter student ID: ");
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Error: Student ID cannot be empty. Please try again.");
                continue;
            }
            if (sharedModel.isExistId(input)) {
                System.out.println("Error: Student ID already exists. Please try again.");
                continue;
            }
            newStudent.setId(input);
            try {
                editDataController.checkStudent(newStudent);
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
            }
        }

        while (true) {
            System.out.print("Enter class ID: ");
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Error: Class ID cannot be empty. Please try again.");
                continue;
            }
            newStudent.setClassId(input);
            try {
                editDataController.checkStudent(newStudent);
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + ". Please try again.");
            }
        }

        int accommodationChoice = -1;
        while (accommodationChoice != 1 && accommodationChoice != 2) {
            accommodationChoice = getIntInput("Choose residence (1 - Dormitory, 2 - Rented House): ");
            if (accommodationChoice != 1 && accommodationChoice != 2) {
                System.out.println("Invalid choice. Please choose 1 or 2.");
            }
        }
        String accommodation = (accommodationChoice == 1) ? "Dormitory" : "Rented House";
        newStudent.setAccommodation(accommodation);

        editDataController.addStudent(newStudent);
        System.out.println("=> ADD STUDENT SUCCESSFULLY!");
    }

    // 2. DELETE FEATURE
    private void deleteStudent() {
        while (true) {
            System.out.print("Enter student ID to delete (type '0' to cancel): ");
            String deleteID = scanner.nextLine();
            if (deleteID.equals("0")) {
                break;
            }
            try {
                Student student = findController.findByStudentId(deleteID).isEmpty() ? null : findController.findByStudentId(deleteID).get(0);
                if (student == null) {
                    System.out.println("Student not found.");
                    continue;
                }
                System.out.println("Please confirm the information before deleting:");
                List<Student> students = new ArrayList<>();
                students.add(student);
                printStudentTable(students);

                System.out.println("\n1. Confirm.");
                System.out.println("2. Cancel.");
                System.out.print("Your choice: ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        editDataController.deleteStudent(student);
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
    private void displayStudentList() {
        List<Student> students = display.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("=> The student list is currently empty.");
            return;
        }
        System.out.println("\n--- STUDENT LIST ---");
        printStudentTable(students);
    }

    // 4. UPDATE INFORMATION FEATURE
    private void updateStudentInfo() {
        System.out.println("\n--- UPDATE STUDENT INFORMATION ---");
        System.out.print("Enter student ID to edit (type '0' to cancel): ");
        String targetId = scanner.nextLine();

        if (targetId.equals("0")) {
            System.out.println("Update canceled.");
            return;
        }

        try {
            UpdateData data = new UpdateData(this.sharedModel);
            Student oldStudent = data.findStudent(targetId);

            boolean isUpdating = true;
            while (isUpdating) {
                System.out.println("\n--- SELECT FIELD TO UPDATE ---");
                System.out.println("1. Name (" + oldStudent.getName() + ")");
                System.out.println("2. Year of Birth (" + oldStudent.getYearOfBirth() + ")");
                System.out.println("3. Student ID (" + oldStudent.getId() + ")");
                System.out.println("4. Class ID (" + oldStudent.getClassId() + ")");
                System.out.println("5. Residence (" + oldStudent.getAccommodation() + ")");
                System.out.println("0. Finish & Go Back");

                int choice = getIntInput("Enter choice: ");

                if (choice == 0) {
                    System.out.println("Finished updating.");
                    isUpdating = false;
                    continue;
                }

                Student updateAttempt = new Student();

                switch (choice) {
                    case 1:
                        while (true) {
                            System.out.print("New name (type '0' to cancel): ");
                            String input = scanner.nextLine();
                            if (input.equals("0")) break;

                            if (input.trim().isEmpty()) {
                                System.out.println("Error: Name cannot be empty. Please try again.");
                                continue;
                            }

                            if (input.trim().replaceAll("\\s+", " ").equalsIgnoreCase(oldStudent.getName())) {
                                System.out.println("Error: The new value must be different from the current value. Please try again.");
                                continue;
                            }

                            updateAttempt.setName(input);
                            try {
                                data.updateStudent(oldStudent.getId(), updateAttempt);
                                System.out.println("Name updated successfully!");
                                break;
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;

                    case 2:
                        while (true) {
                            System.out.print("New birth year (type '0' to cancel): ");
                            String input = scanner.nextLine();
                            if (input.equals("0")) break;

                            if (input.trim().isEmpty()) {
                                System.out.println("Error: Year of birth cannot be empty. Please try again.");
                                continue;
                            }

                            if (input.equals(oldStudent.getYearOfBirth())) {
                                System.out.println("Error: The new value must be different from the current value. Please try again.");
                                continue;
                            }

                            updateAttempt.setYearOfBirth(input);
                            try {
                                data.updateStudent(oldStudent.getId(), updateAttempt);
                                System.out.println("Year of birth updated successfully!");
                                break;
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;

                    case 3:
                        while (true) {
                            System.out.print("New student ID (type '0' to cancel): ");
                            String input = scanner.nextLine();
                            if (input.equals("0")) break;

                            if (input.trim().isEmpty()) {
                                System.out.println("Error: Student ID cannot be empty. Please try again.");
                                continue;
                            }

                            if (input.equals(oldStudent.getId())) {
                                System.out.println("Error: The new value must be different from the current value. Please try again.");
                                continue;
                            }

                            if (sharedModel.isExistId(input) && !input.equals(oldStudent.getId())) {
                                System.out.println("Error: Student ID already exists. Please try again.");
                                continue;
                            }

                            updateAttempt.setId(input);
                            try {
                                data.updateStudent(oldStudent.getId(), updateAttempt);
                                System.out.println("Student ID updated successfully!");
                                targetId = input;
                                oldStudent = data.findStudent(targetId);
                                break;
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;

                    case 4:
                        while (true) {
                            System.out.print("New class ID (type '0' to cancel): ");
                            String input = scanner.nextLine();
                            if (input.equals("0")) break;

                            if (input.trim().isEmpty()) {
                                System.out.println("Error: Class ID cannot be empty. Please try again.");
                                continue;
                            }

                            if (input.equalsIgnoreCase(oldStudent.getClassId())) {
                                System.out.println("Error: The new value must be different from the current value. Please try again.");
                                continue;
                            }

                            updateAttempt.setClassId(input);
                            try {
                                data.updateStudent(oldStudent.getId(), updateAttempt);
                                System.out.println("Class ID updated successfully!");
                                break;
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;

                    case 5:
                        while (true) {
                            String currentRes = oldStudent.getAccommodation();
                            String targetRes = currentRes.equalsIgnoreCase("Dormitory") ? "Rented House" : "Dormitory";

                            int accChoice = getIntInput("Current residence is '" + currentRes + "'. Change to '" + targetRes + "'? (1 - Yes, 0 - Cancel): ");

                            if (accChoice == 0) break;

                            if (accChoice == 1) {
                                updateAttempt.setAccommodation(targetRes);
                                try {
                                    data.updateStudent(oldStudent.getId(), updateAttempt);
                                    System.out.println("Residence updated successfully!");
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            } else {
                                System.out.println("Invalid choice. Please choose 1 (Yes) or 0 (Cancel).");
                            }
                        }
                        break;

                    default:
                        System.out.println("Error: Invalid choice! Please choose an option from 1 to 5.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Detect error: " + e.getMessage());
        }
    }


    // 5. SEARCH FEATURE
    private void searchStudent() {

        while (true) {
            System.out.println("\n--- SEARCH STUDENT ---");
            System.out.println("1. Search by Name");
            System.out.println("2. Search by Student ID");
            System.out.println("3. Search by Year of Birth");
            System.out.println("4. Search by Class ID");
            System.out.println("5. Search by Residence");
            System.out.println("0. Exit");
            int searchChoice = getIntInput("Choose search method: ");

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
                        results = findController.findByName(keyword);
                        break;
                    case 2:
                        results = findController.findByStudentId(keyword);
                        break;
                    case 3:
                        results = findController.findByYearOfBirth(keyword);
                        break;
                    case 4:
                        results = findController.findByClassID(keyword);
                        break;
                    case 5:
                        results = findController.findByAccommodation(keyword);
                        break;
                    default:
                        System.out.println("Error: Invalid choice! Please choose an option from 1 to 5.");
                        return;
                }

                if (results != null && !results.isEmpty()) {
                    System.out.println("\n=> FOUND " + results.size() + " RESULT(S):");
                    printStudentTable(results);
                }
            } catch (Exception e) {
                System.out.println("=> Cannot search: " + e.getMessage());
            }
        }
    }

    //6. SORT FEATURE
    private void displaySortedList() {
        ArrayList<Student> students = sort.getStudentList();

        if (students.isEmpty()) {
            System.out.println("List have no students.");
            return;
        }

        System.out.println("\n--- SORT STUDENT LIST ---");
        printStudentTable(students);
    }

    private void sortMenu() {
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

            if (choice < 1 || choice > 4) {
                System.out.println("Error: Invalid choice! Please choose an option from 1 to 4.");
                continue;
            }

            System.out.print("Sort order (ASC/DESC): ");
            String orderInput = scanner.nextLine().trim().toUpperCase();
            StudentModel.Order order = orderInput.equals("DESC") ? StudentModel.Order.DESC : StudentModel.Order.ASC;

            switch (choice) {
                case 1 -> sort.sortByName(order);
                case 2 -> sort.sortByStudentID(order);
                case 3 -> sort.sortByYearOfBirth(order);
                case 4 -> sort.sortByClassID(order);
            }

            displaySortedList();
        }
    }
}