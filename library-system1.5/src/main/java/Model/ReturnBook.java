package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ReturnBook {
    private int adminId;
    private int transactionId;
    private String condition;
    private int bookId;

    private String studentId;
    private String title;
    private String adminName;
    private String borrowedDate;
    private String returnDate;
    private String returnedDate;

    public ReturnBook(int adminId, int transactionId, String condition) {
        this.adminId = adminId;
        this.transactionId = transactionId;
        this.condition = condition;
    }

    public ReturnBook(int adminId, int transactionId, String condition, int bookId) {
        this.adminId = adminId;
        this.transactionId = transactionId;
        this.condition = condition;
        this.bookId = bookId;
    }

    public ReturnBook(int transactionId, String studentId, String title, String adminName, String borrowedDate, String returnDate, String returnedDate) {
        this.transactionId = transactionId;
        this.studentId = studentId;
        this.title = title;
        this.adminName = adminName;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
        this.returnedDate = returnedDate;
    }
}
