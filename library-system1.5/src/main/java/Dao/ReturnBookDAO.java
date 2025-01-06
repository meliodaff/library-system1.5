package Dao;
import Model.ReturnBook;
import java.util.List;

public interface ReturnBookDAO {
    ReturnBook returnBook(ReturnBook returnBook, int adminId);
    boolean checkTransactionId(int transactionId, ReturnBook returnBook);
    void plusStock (int bookId);
    void updateBook(int transactionId);
    List<ReturnBook> getReturnedBooks();
    ReturnBook getSpecificReturnedBook(int transactionId);
}
