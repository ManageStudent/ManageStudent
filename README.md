# ManageStudent

Chỉnh sửa 16_07_2026:
Góp ý phân tầng
- Model: Person -> Student, Dormitory (Lưu thông tin - repo của chương trình)
- Control: Xử lý: các method như chỉnh sửa, cập nhật thông tin, lớp học,...
- RAM: lưu danh sách Student
- UI: Thêm, sửa, xoá, in ra danh sách, thoát.

Note: Đã làm Person -> Student


Chỉnh sửa 20_07_2026:
- Model: 
    + Đã thêm class StudentModel bao gồm xử lý lưu dữ liệu và method add, remove cho cân xứng với bên EditData.java. 
    + Đã sửa lại getInfo có khả năng trả về một chuỗi String trong Person và Student
- Controller: Trong EditData.java tạo một object mang tên model để có thể hoạt động add và remove.