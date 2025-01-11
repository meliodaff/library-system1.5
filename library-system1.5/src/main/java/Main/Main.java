package Main;
import Dao.*;
import Dashboard.*;
import Database.Database;
import Implementation.*;
import Model.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Injection Dependencies
        Database database = new Database();
        BookDAO bookDao = new BookDAOImplementation(database);
        AuthorDAO authorDao = new AuthorDAOImplementation(database);
        PublisherDAO publisherDAO = new PublisherDAOImplementation(database);
        Scanner scanner = new Scanner(System.in);
        AdminDAO adminDAO = new AdminDAOImplementation(database);
        BooksDashboard booksDashboard = new BooksDashboard();
        AuthorsDashboard authorsDashboard = new AuthorsDashboard();
        PublishersDashboard publishersDashboard = new PublishersDashboard();

        BorrowBooksDashboard borrowBooksDashboard = new BorrowBooksDashboard();
        BorrowBookDAO borrowBookDAO = new BorrowBookDAOImplementation(database, adminDAO);
        ReturnBook returnBook = new ReturnBook();
        ReturnBookDAO returnBookDAO = new ReturnBookDAOImplementation(database);
        AdminDashboard adminDashboard = new AdminDashboard(adminDAO, scanner);
        DisplayDashboards displayDashboards = new DisplayDashboards();
        ReturnBookDashboard returnBookDashboard = new ReturnBookDashboard(adminDAO);

        System.out.println("Book Buddy: A Library Management System. [Version 1.0.0.0]");
        System.out.println("Javarian Corporation. All rights reserved.");
        while(true){
            int chooseDashboard = adminDashboard.frontDashboard();
            if(chooseDashboard == 1){
                Admin admin = adminDashboard.loginDashboard();
                if (admin == null) {
                    continue;
                }
                while(true) {
                    byte choiceAdminDashboard = adminDashboard.adminDashboard();
                    if (choiceAdminDashboard == 1) {
                        displayDashboards.displayOne(booksDashboard,bookDao, scanner);
                    } else if (choiceAdminDashboard == 2) {
                        displayDashboards.displayTwo(authorsDashboard, authorDao, scanner);

                    } else if (choiceAdminDashboard == 3) {
                        displayDashboards.displayThree(publishersDashboard, publisherDAO, scanner);
                    }
                    else if (choiceAdminDashboard == 4) {
                        while(true){
                            byte choiceBorrowBookDashboard = borrowBooksDashboard.borrowBooksDashboard(scanner);
                            if(choiceBorrowBookDashboard == 1){
                                List<BorrowBook> borrowedBooks = borrowBookDAO.borrowedBooks();
                                borrowBooksDashboard.displayBorrowedBooks(borrowedBooks);
                            }
                            else if (choiceBorrowBookDashboard == 2){
                                scanner.nextLine();
                                System.out.print("Student ID: ");
                                String studentId = scanner.nextLine();
                                List<BorrowBook> borrowedBook = borrowBookDAO.specificBorrowedBook(studentId);
                                borrowBooksDashboard.displayBorrowedBooks(borrowedBook);
                            }
                            else if (choiceBorrowBookDashboard == 3){
                                scanner.nextLine();
                                System.out.print("Book ID: ");
                                int bookId = scanner.nextInt();
                                scanner.nextLine();
                                if(borrowBookDAO.checkAvailability(bookId)){
                                    BorrowBook borrowBook = borrowBookDAO.createBorrowBook(scanner);
                                    borrowBook.setBookId(bookId);
                                    borrowBook.setAdminId(adminDAO.getAdminId());
                                    if(borrowBookDAO.borrowBook(borrowBook)) {
                                        borrowBookDAO.minusStack(bookId);
                                        System.out.println("Book ID: " + bookId + " Successfully borrowed By Student ID: " + borrowBook.getStudentId());
                                    }
                                    else System.out.println("An error has occurred");
                                }
                                else System.out.println("Book ID: " + bookId + " has no stocks");

                            }
                            else if (choiceBorrowBookDashboard == 4){
                                break;
                            }
                        }
                    }
                    else if (choiceAdminDashboard == 5) {
                        while (true){
                            byte returnBookChoice = returnBookDashboard.returnBooksDashboard(scanner);
                            if(returnBookChoice == 1){
                                returnBookDashboard.displayReturnedBooks(returnBookDAO.getReturnedBooks());
                            }
                            else if (returnBookChoice == 2) {
                                scanner.nextLine();
                                System.out.print("Transaction ID: ");
                                byte transactionId = scanner.nextByte();
                                scanner.nextLine();
                                returnBookDashboard.displayReturnedBooks(returnBookDAO.getSpecificReturnedBook(transactionId));
                            }
                            else if (returnBookChoice == 3){
                                returnBook = returnBookDashboard.displayReturnBook(scanner, returnBook);
                                if (returnBookDAO.checkTransactionId(returnBook.getTransactionId(), returnBook)) {
                                    returnBook = returnBookDAO.returnBook(returnBook, adminDAO.getAdminId());
                                    if (returnBook != null) {
                                        returnBookDAO.plusStock(returnBook.getBookId()); // this doesnt work, why?
                                        System.out.println(returnBook.getBookId());
                                        returnBookDAO.updateBook(returnBook.getTransactionId());
                                        System.out.println("Transaction ID: " + returnBook.getTransactionId() + " Returned Successfully");
                                        System.out.println("-------------------------------");
                                    }
                                } else {
                                    System.out.println("Transaction ID does not exist");
                                    System.out.println("-------------------------------");
                                }
                            }
                            else if (returnBookChoice == 4){
                                break;
                            }
                        }
                    }
                    else if (choiceAdminDashboard == 6){
                        System.out.println("Are you sure you want to LOGOUT?");
                        System.out.println("[1] YES");
                        System.out.println("[2] NO");
                        System.out.print("Enter your Option: ");
                        byte choice = scanner.nextByte();
                        System.out.println("-------------------------------");
                        if(choice ==1){
                            System.out.println(admin.getName() + " has been LOGGED OUT. ");
                            System.out.println("-------------------------------");
                            break;
                        } else if (choice ==2) {
                            System.out.println("Back to the Main Menu");
                            System.out.println("-------------------------------");
                        }

                    }
                    else {
                        System.out.println("Invalid Inputs. Please Try Again.");
                        System.out.println("-------------------------------");
                    }
                }
            }
            else if (chooseDashboard == 2){
                while (true){
                    System.out.print("Super Admin Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Super Admin Password: ");
                    String password = scanner.nextLine();
                    if(adminDAO.validateSuperAdmin(username, password)){
                        System.out.println("-------------------------------");
                        adminDashboard.registerDashboard();
                        break;
                    }
                    else{
                        System.out.println("Wrong Super Admin Credentials");
                        System.out.println("-------------------------------");
                    }
                }
            }else if (chooseDashboard ==3){
                System.out.println("Are you sure you want to exit the program? ");
                System.out.println("[1] YES");
                System.out.println("[2] NO");
                System.out.print("Enter your Option: ");
                byte choice = scanner.nextByte();
                System.out.println("-------------------------------");
                if(choice ==1){
                    System.out.println("Thank you for using our system. Goodbye!!");
                    System.out.println("-------------------------------");
                    System.exit(0);
                } else if (choice==2) {
                    System.out.println("Going back to the Home Page");
                    System.out.println("-------------------------------");
                }

            }else {
                System.out.println("Invalid Inputs. Please Try Again.");
                System.out.println("-------------------------------");
            }
        }
    }
}