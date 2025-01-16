package Implementation;
import Dao.AdminDAO;
import Model.BorrowBook;
import Dao.BorrowBookDAO;
import Database.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BorrowBookDAOImplementation implements BorrowBookDAO{
    private final Database database;
    private final AdminDAO adminDAO;

    public BorrowBookDAOImplementation(Database database, AdminDAO adminDAO){
        this.database = database;
        this.adminDAO = adminDAO;
    }

    @Override
    public List<BorrowBook> borrowedBooks(){
        List<BorrowBook> borrowedBooks = new ArrayList<>();
        BorrowBook borrowedBook = null;
        String query = "CALL getBorrowedBooks()";

        try(Connection con = database.getConnection();
            CallableStatement cst = con.prepareCall(query);){
            ResultSet rs = cst.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String studentId = rs.getString("student_id");
                String title = rs.getString("title");
                String adminName = rs.getString("admin_name");
                String borrowedDate = rs.getString("borrowed_date");
                String returnDate=  rs.getString("return_date");
                borrowedBook = new BorrowBook(id, studentId, title, adminName, borrowedDate, returnDate);
                borrowedBooks.add(borrowedBook);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return borrowedBooks;
    }

    @Override
    public List<BorrowBook> specificBorrowedBook(String studentId){
        String query = "CALL getSpecificBorrowedBook(?)";
        List<BorrowBook> borrowedBooks = new ArrayList<>();
        BorrowBook borrowedBook = null;
        try(Connection con = database.getConnection();
            CallableStatement cs = con.prepareCall(query)){
            cs.setString(1, studentId);
            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String studentID = rs.getString("student_id");
                String title = rs.getString("title");
                String adminName = rs.getString("admin_name");
                String borrowedDate = rs.getString("borrowed_date");
                String returnDate=  rs.getString("return_date");
                borrowedBook = new BorrowBook(id, studentID, title, adminName, borrowedDate, returnDate);
                borrowedBooks.add(borrowedBook);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return borrowedBooks;
    }

    @Override
    public boolean checkAvailability(int bookId){
        String query = "SELECT stock FROM books WHERE id = ?";

        try(Connection con = database.getConnection();
            PreparedStatement pst = con.prepareStatement(query);){
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                int stockCount = rs.getInt("stock");
                return stockCount > 0;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean borrowBook(BorrowBook borrowBook){
        String query = "INSERT INTO transaction_table (student_id, book_id, borrowed_admin_id, return_date) VALUES (?, ?, ?, ?)";

        try(Connection con = database.getConnection();
            PreparedStatement pst = con.prepareStatement(query);){
            pst.setString(1, borrowBook.getStudentId());
            pst.setInt(2, borrowBook.getBookId());
            pst.setInt(3, borrowBook.getAdminId());
            pst.setString(4, borrowBook.getReturnDate());

            int rowsInserted = pst.executeUpdate();
            return rowsInserted > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public BorrowBook createBorrowBook(Scanner scanner){
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Return Date (YYY-mm-dd): ");
        String returnDate = scanner.nextLine();
        BorrowBook borrowBook = new BorrowBook(studentId, adminDAO.getAdminId(), returnDate);
        System.out.println("-------------------------------");
        return borrowBook;
    }

    @Override
    public void minusStack(int bookId){
        String query = "UPDATE books SET stock = stock - 1 WHERE id = ?";

        try(Connection con = database.getConnection();
            PreparedStatement pst = con.prepareStatement(query);){
            pst.setInt(1, bookId);
            pst.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
