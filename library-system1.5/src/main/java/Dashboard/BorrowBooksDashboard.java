package Dashboard;
import Model.BorrowBook;
import java.util.List;
import java.util.Scanner;

public class BorrowBooksDashboard {

    public byte borrowBooksDashboard(Scanner scanner){
        System.out.println("[1] Display Borrowed Books");
        System.out.println("[2] Display Specific Borrowed Books");
        System.out.println("[3] Borrow Book");
        System.out.println("[4] Back");
        System.out.print("Enter your Option: ");
        byte choice = scanner.nextByte();
        System.out.println("-------------------------------");
        return choice;
    }
    public void displayBorrowedBooks(List<BorrowBook> borrowedBooks){
        for(BorrowBook borrowedBook : borrowedBooks){
            System.out.println("Transaction ID: " + borrowedBook.getId());
            System.out.println("Student ID: " + borrowedBook.getStudentId());
            System.out.println("Book Title: " + borrowedBook.getTitle());
            System.out.println("Admin Name: " + borrowedBook.getAdminName());
            System.out.println("Borrowed Date: " + borrowedBook.getBorrowedDate());
            System.out.println("Return Date: " + borrowedBook.getReturnDate());
            System.out.println("-------------------------------");
        }
    }
}
