package Dashboard;
import Dao.AdminDAO;
import Model.ReturnBook;
import java.util.List;
import java.util.Scanner;

public class ReturnBookDashboard {

    private final AdminDAO adminDAO;

    public ReturnBookDashboard(AdminDAO adminDAO){
        this.adminDAO = adminDAO;
    }

    public ReturnBook displayReturnBook(Scanner scanner, ReturnBook returnBook){
        System.out.print("Transaction ID: ");
        int transactionId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Condition: ");
        String condition = scanner.nextLine();
        returnBook = new ReturnBook(adminDAO.getAdminId(), transactionId, condition, returnBook.getBookId());
        return returnBook;
    }

    public byte returnBooksDashboard(Scanner scanner){
        System.out.println("[1] Returned Books");
        System.out.println("[2] Specific Returned Book");
        System.out.println("[3] Return Book");
        System.out.println("[4] Back");
        System.out.print("Enter your Option: ");
        byte choice = scanner.nextByte();
        System.out.println("-------------------------------");
        return choice;
    }

    public void displayReturnedBooks(List<ReturnBook> books){
        for(ReturnBook returnBook : books){
            System.out.println("Transaction ID: " + returnBook.getTransactionId());
            System.out.println("Book Borrowed By: " + returnBook.getStudentId());
            System.out.println("Book Title: " + returnBook.getTitle());
            System.out.println("Book Borrowed By Admin: " + returnBook.getAdminName());
            System.out.println("Borrowed Date: " + returnBook.getBorrowedDate());
            System.out.println("Return Date: " + returnBook.getReturnDate());
            System.out.println("Returned Date: " + returnBook.getReturnedDate());
            System.out.println("-------------------------------");
        }
    }

    public void displayReturnedBooks(ReturnBook returnBook){
        if (returnBook == null) {
            System.out.println("No data available for the specified transaction ID.");
            System.out.println("-------------------------------");
            return;
        }
        System.out.println("Transaction ID: " + returnBook.getTransactionId());
        System.out.println("Book Borrowed By: " + returnBook.getStudentId());
        System.out.println("Book Title: " + returnBook.getTitle());
        System.out.println("Book Borrowed By Admin: " + returnBook.getAdminName());
        System.out.println("Borrowed Date: " + returnBook.getBorrowedDate());
        System.out.println("Return Date: " + returnBook.getReturnDate());
        System.out.println("Returned Date: " + returnBook.getReturnedDate());
        System.out.println("-------------------------------");
    }
}
