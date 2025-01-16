    package Dashboard;

    import Dao.AdminDAO;

    import java.util.Scanner;

    public class AccountSettingDashboard {
        private final AdminDAO adminDAO;
        private final Scanner scanner;

        public AccountSettingDashboard(AdminDAO adminDAO, Scanner scanner) {
            this.adminDAO = adminDAO;
            this.scanner = scanner;
        }
        public void displayAccountSettings() {
            while (true) {
                System.out.println("++Account Settings++");
                System.out.println("[1] View All Account");
                System.out.println("[2] Update User Account");
                System.out.println("[3] Deactivate/Activate User Account");
                System.out.println("[4] Back to Main Menu");
                System.out.print("Enter your Option: ");
                byte choice = scanner.nextByte();
                System.out.println("-------------------------------");
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        adminDAO.viewAllUsers();
                        continue;
                    case 2:
                        adminDAO.viewAllUsers();
                        updateUser();
                        continue;
                    case 3:
                        adminDAO.viewAllUsers();
                        toggleUserStatus();
                        continue;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid Choice. Please Try Again.");
                }
            }
        }
        private void updateUser() {
            while (true) {
                System.out.print("Enter the ID to Update: ");
                byte id = scanner.nextByte();
                scanner.nextLine();
                System.out.print("Enter the Username: ");
                String username = scanner.nextLine();
                System.out.print("Enter the Password: ");
                String password = scanner.nextLine();
                System.out.print("Re-Enter the Password: ");
                String rePassword = scanner.nextLine();
                System.out.println("-------------------------------");

                if(password.length() <12){
                    System.out.println("Your Password is weak. Please Try Again.");
                    System.out.println("-------------------------------");
                    continue;
                }

                if (!password.equals(rePassword)) {
                    System.out.println("Your Password doesn't match. Please Try Again.");
                    System.out.println("-------------------------------");
                    continue;
                }
                adminDAO.updateUser(id, username, password);
                System.out.println("-------------------------------");
                return;
            }
        }

        private void toggleUserStatus() {
            while (true) {
                System.out.println("[1] Activate User Account ");
                System.out.println("[2] Deactivate User Account ");
                System.out.println("[3] Back ");
                System.out.print("Enter your Option: ");
                byte choice = scanner.nextByte();
                scanner.nextLine();
                System.out.println("-------------------------------");

                if (choice == 3) {
                    System.out.println("Going Back to Menu.");
                    return;
                }
                if (choice == 1 || choice == 2) {
                    System.out.print("Enter User ID: ");
                    byte id = scanner.nextByte();
                    scanner.nextLine();
                    System.out.println("-------------------------------");

                    // Call toggleUserStatus, pass 'true' for activate and 'false' for deactivate
                    adminDAO.toggleUserStatus(id, choice == 1); // true for activate, false for deactivate
                } else {
                    System.out.println("Invalid Input. Please Try Again.");
                    System.out.println("-------------------------------");
                }

                System.out.println("-------------------------------");
            }
        }

    }
