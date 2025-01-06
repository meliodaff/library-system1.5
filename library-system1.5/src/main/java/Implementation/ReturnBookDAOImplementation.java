package Implementation;

import Dao.ReturnBookDAO;
import Database.Database;
import Model.ReturnBook;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReturnBookDAOImplementation implements ReturnBookDAO {
    private final Database database;

    public ReturnBookDAOImplementation(Database database){
        this.database = database;
    }
    @Override
    public ReturnBook returnBook(ReturnBook returnBook, int adminId) {
        String query = "INSERT INTO return_table (transaction_id, `condition`, returned_admin_id) VALUES (?, ?, ?)";

        try (Connection con = database.getConnection();
             PreparedStatement pst = con.prepareStatement(query);) {
            pst.setInt(1, returnBook.getTransactionId());
            pst.setString(2, returnBook.getCondition());
            pst.setInt(3, adminId);
            //returnBook = new ReturnBook(returnBook.getTransactionId(), returnBook.getCondition(), adminDAO.getAdminId());
            //returnBook.setAdminId(adminDAO.getAdminId());
            pst.executeUpdate();
            return returnBook;
        } catch (Exception e) {
            System.out.printf("im here");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkTransactionId(int transactionId, ReturnBook returnBook) {
        String query = "SELECT id, book_id FROM transaction_table WHERE id = ? AND remarks = 'BORROWED'";

        try (Connection con = database.getConnection();
             PreparedStatement pst = con.prepareStatement(query);) {
            pst.setInt(1, transactionId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                returnBook.setBookId(rs.getInt("book_id"));
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void plusStock(int bookId) {
        String query = "UPDATE books SET stock = stock + 1 WHERE id = ?";

        try (Connection con = database.getConnection();
             PreparedStatement pst = con.prepareStatement(query);) {
            pst.setInt(1, bookId);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateBook(int transactionId){
        String query = "UPDATE transaction_table SET remarks = 'RETURNED' WHERE id = ?";

        try(Connection con = database.getConnection();
            PreparedStatement pst = con.prepareStatement(query);){
            pst.setInt(1, transactionId);
            pst.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<ReturnBook> getReturnedBooks(){
        String query = "CALL getReturnedBooks()";
        List<ReturnBook> books = new ArrayList<>();
        ReturnBook book = null;
        try(Connection con = database.getConnection();
            CallableStatement cs = con.prepareCall(query);){
            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                book = new ReturnBook(rs.getInt("id"), rs.getString("student_id"), rs.getString("title"), rs.getString("admin_name"), rs.getString("borrowed_date"), rs.getString("return_date"), rs.getString("returned_date"));
                books.add(book);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public ReturnBook getSpecificReturnedBook(int transactionId){
        String query = "CALL getSpecificReturnedBook(?)";
        ReturnBook returnBook = null;

        try(Connection con = database.getConnection();
            CallableStatement cs = con.prepareCall(query);){
            cs.setInt(1, transactionId);
            ResultSet rs = cs.executeQuery();

            if(rs.next()){
                returnBook = new ReturnBook(rs.getInt("id"),rs.getString("student_id"), rs.getString("title"), rs.getString("admin_name"), rs.getString("borrowed_date"), rs.getString("return_date"), rs.getString("returned_date"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return returnBook;
    }
}
