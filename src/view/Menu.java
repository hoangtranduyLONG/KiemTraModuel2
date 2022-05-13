package view;
import control.PhoneBook;
        import date.DateFormatter;
        import model.User;
        import validate.DateValidate;
        import validate.EmailValidate;
        import validate.PhoneNumberValidate;

        import java.text.DateFormat;
        import java.time.LocalDate;
        import java.time.format.DateTimeFormatter;
        import java.util.Scanner;

public class Menu {
    public static void builtMenu() {
        System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ----");
        System.out.println("Chọn chức năng theo số để tiếp tục: ");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Đọc từ file");
        System.out.println("7. Ghi từ file");
        System.out.println("8. Thoát");
    }

    public static User createUser(Scanner scl) {
        DateValidate dateValidate = new DateValidate();
        EmailValidate emailValidate = new EmailValidate();
        PhoneNumberValidate phoneNumberValidate = new PhoneNumberValidate();
        System.out.print("Nhập tên: ");
        String name = scl.nextLine().trim();
        String phoneNumber = "";
        while (true) {
            System.out.print("Nhập số điện thoại: ");
            String phoneNumberTemp = scl.nextLine().trim();
            if (phoneNumberValidate.validate(phoneNumberTemp)) {
                phoneNumber = phoneNumberTemp;
                break;
            }
            System.out.println("Số điện thoại của bạn chưa đúng định dạng");
        }
        System.out.print("Nhập nhóm: ");
        String group = scl.nextLine().trim();
        System.out.print("Nhập giới tính: ");
        String sex = scl.nextLine().trim().toLowerCase();
        System.out.print("Nhập địa chỉ: ");
        String address = scl.nextLine();
        String email = "";
        while (true) {
            System.out.print("Nhập email: ");
            String emailTemp = scl.nextLine().trim();
            if (emailValidate.validate(emailTemp)) {
                email = emailTemp;
                break;
            }
            System.out.println("Email của bạn chưa đúng định dạng");
        }
        LocalDate dateOfBirth = LocalDate.now();
        while (true) {
            System.out.print("Nhập ngày sinh(năm-tháng-ngày): ");
            String date = scl.nextLine();
            if (dateValidate.validate(date)) {
                dateOfBirth = DateFormatter.parseDate(date);
                break;
            }
            System.out.println("Bạn phải nhập đúng định dạng");
        }
        return new User(phoneNumber, group, name, sex, address, dateOfBirth, email);
    }

    public static void editUser(Scanner scl, PhoneBook phoneBook) {
        DateValidate dateValidate = new DateValidate();
        EmailValidate emailValidate = new EmailValidate();
        PhoneNumberValidate phoneNumberValidate = new PhoneNumberValidate();
        String phoneNumber = "";
        boolean flag = true;
        while (flag) {
            while (true) {
                System.out.print("Nhập số điện thoại cần sửa: ");
                String phoneNumberTemp = scl.nextLine().toLowerCase();
                if (phoneNumberValidate.validate(phoneNumberTemp)) {
                    phoneNumber = phoneNumberTemp;
                    break;
                }
                System.out.println("Số điện thoại của bạn chưa đúng định dạng");
            }
            if (phoneBook.findIndexByNumberPhone(phoneNumber) != -1) {
                flag = false;
            }
        }

        System.out.print("Nhập tên: ");
        String name = scl.nextLine().trim();
        System.out.print("Nhập nhóm: ");
        String group = scl.nextLine().trim();
        System.out.print("Nhập giới tính: ");
        String sex = scl.nextLine().trim().toLowerCase();
        System.out.print("Nhập địa chỉ: ");
        String address = scl.nextLine();
        String email = "";
        while (true) {
            System.out.print("Nhập email: ");
            String emailTemp = scl.nextLine().trim();
            if (emailValidate.validate(emailTemp)) {
                email = emailTemp;
                break;
            }
            System.out.println("Email của bạn chưa đúng định dạng");
        }
        LocalDate dateOfBirth = LocalDate.now();
        while (true) {
            System.out.print("Nhập ngày sinh(năm-tháng-ngày): ");
            String date = scl.nextLine();
            if (dateValidate.validate(date)) {
                dateOfBirth = DateFormatter.parseDate(date);
                break;
            }
            System.out.println("Bạn phải nhập đúng định dạng");
        }
        phoneBook.updateUser(phoneNumber, new User(phoneNumber, group, name, sex, address, dateOfBirth, email));

    }

    public static void deleteUser(Scanner scl, PhoneBook phoneBook) {
        System.out.print("Nhập số điện thoại cần xóa: ");
        String numberPhone = scl.nextLine().trim().toLowerCase();
        phoneBook.deleteUser(numberPhone);
    }

    public static void findUser(Scanner scl, PhoneBook phoneBook) {
        System.out.print("Nhập số điện thoại hoặc tên người dùng cần tìm: ");
        String user = scl.nextLine().trim();
        for (User u : phoneBook.findUserByNumberPhoneOrName(user)) {
            System.out.println(u);
        }
    }
}