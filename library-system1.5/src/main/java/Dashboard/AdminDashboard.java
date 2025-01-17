package Dashboard;
import Dao.AdminDAO;
import Model.Admin;
import java.util.Scanner;

public class AdminDashboard {
    private final Scanner scanner;
    private final AdminDAO adminDAO;
    public AdminDashboard(AdminDAO adminDAO, Scanner scanner){
        this.adminDAO = adminDAO;
        this.scanner = scanner;
    }


    public byte frontDashboard(){
        System.out.println("Welcome to book buddy");
        System.out.println("[1] Login");
        System.out.println("[2] Register");
        byte choice = scanner.nextByte();
        scanner.nextLine();
        return choice;
    }

    public Admin loginDashboard(){
        System.out.println("Book Buddy");
        System.out.println("Login");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        Admin admin = adminDAO.logIn(username, password);
        return admin;
    }

    public void registerDashboard(){
        System.out.print("Full Name:");
        String name = scanner.nextLine().toLowerCase();
        System.out.print("Username: ");
        String username = scanner.nextLine().toLowerCase();
        System.out.print("Password: ");
        String password = scanner.nextLine().toLowerCase();
        Admin admin = new Admin(name, username, password);
        adminDAO.register(admin);
    }
    public byte adminDashboard(){
        System.out.println("Book Buddy");
        System.out.println("[1] Books");
        System.out.println("[2] Authors");
        System.out.println("[3] Publishers");
        System.out.println("[4] Borrow Books");
        System.out.println("[5] Return Books");
        System.out.println("[6] Logout");
        byte choice = scanner.nextByte();
        return choice;
    }
}
