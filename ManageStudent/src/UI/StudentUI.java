package UI;

import java.util.Scanner;

public class StudentUI {
    private final int EXIT_CHOICE = 0;
    private Scanner scanner;

    // Khai báo Service ở đây
    // private StudentService studentService;

    public StudentUI() {
        this.scanner = new Scanner(System.in);
        // this.studentService = new StudentService();
    }

    public void start() {
        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            int userChoice = getIntInput("Mời bạn nhập lựa chọn: ");

            switch (userChoice) {
                case 1:
                    inputStudentInfo();
                    break;
                case 2:
                    displayStudentList();
                    break;
                case 3:
                    System.out.println("Tính năng cập nhật đang phát triển.");
                    break;
                case EXIT_CHOICE:
                    isRunning = false;
                    System.out.println("Đã thoát chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n----- CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN -----");
        System.out.println("1. Thêm sinh viên mới");
        System.out.println("2. Hiển thị danh sách sinh viên");
        System.out.println("3. Cập nhật thông tin sinh viên");
        System.out.println("4. Thoát");
    }

    private int getIntInput(String prompt) {

    }

    private void inputStudentInfo() {
        System.out.println("\n----- NHẬP THÔNG TIN SINH VIÊN -----");


        System.out.println("\nĐang xử lý và lưu thông tin...");

        // Service sẽ nhận 5 tham số này để tạo đối tượng Student mới:
        // studentService.addStudent(name, yearOfBirth, id, classId, accommodation);

        System.out.println("Thêm sinh viên thành công!");
    }

    private void updateStudentInfo() {
        System.out.println("\n----- CẬP NHẬT THÔNG TIN SINH VIÊN -----");
        }
    }

    private void displayStudentList() {
        System.out.println("\n----- DANH SÁCH SINH VIÊN -----");
         String listData = studentService.getAllStudentsInfo();
        if (listData == null || listData.isEmpty()) {
            System.out.println("(Danh sách trống)");
        } else {
            System.out.println(listData);
        }
    }
}
