package view;
import control.PhoneBook;
        import file_handle.FileUserCSV;

        import java.util.InputMismatchException;
        import java.util.Scanner;

public class Main {
    public static void createMain() {
        Scanner scn = new Scanner(System.in);
        Scanner scl = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        int choose = 0;
        String select;
        do {
            Menu.builtMenu();
            do {
                try {
                    System.out.print("Nhập lựa chọn của bạn: ");
                    choose = scn.nextInt();
                } catch (InputMismatchException e) {
                    choose = -1;
                    System.out.println("Bạn phải chọn các số có trong menu.");
                    scn.nextLine();
                }
            } while (!(choose >= 1 && choose <= 8));
            switch (choose) {
                case 1:
                    phoneBook.showPhoneBook();
                    break;
                case 2:
                    do {
                        phoneBook.addUser(Menu.createUser(scl));
                        do {
                            System.out.print("Bạn muốn nhập tiếp không (y/n): ");
                            select = scl.nextLine();
                        } while (!(select.equals("n") || select.equals("y")));
                    } while (select.equals("y"));
                    break;
                case 3:
                    do {
                        Menu.editUser(scl, phoneBook);
                        do {
                            System.out.print("Bạn muốn sửa tiếp không (y/n): ");
                            select = scl.nextLine();
                        } while (!(select.equals("n") || select.equals("y")));
                    } while (select.equals("y"));
                    break;
                case 4:
                    do {
                        Menu.deleteUser(scl, phoneBook);
                        do {
                            System.out.print("Bạn muốn xóa tiếp không (y/n): ");
                            select = scl.nextLine();
                        } while (!(select.equals("n") || select.equals("y")));
                    } while (select.equals("y"));
                    break;
                case 5:
                    Menu.findUser(scl, phoneBook);
                    break;
                case 6:
                    FileUserCSV.readFile(phoneBook);
                    System.out.println("Đọc file thành công");
                    break;
                case 7:
                    FileUserCSV.writeFile(phoneBook);
                    System.out.println("Ghi file Thành công");
                    break;
            }
        } while (choose <= 7);
    }
}