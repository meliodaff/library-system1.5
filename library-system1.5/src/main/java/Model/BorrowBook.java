package Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class BorrowBook {
    private int id;
    private String studentId;
    private int bookId; // to add Borrowed Books
    private int adminId;
    private String title;
    private String adminName;
    private String borrowedDate;
    private String returnDate;

    public BorrowBook(int id, String studentId, String title, String adminName, String borrowedDate, String returnDate) {
        this.id = id;
        this.studentId = studentId;
        this.title = title;
        this.adminName = adminName;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
    }

    public BorrowBook(String studentId, int bookId, int adminId, String returnDate) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.adminId = adminId;
        this.returnDate = returnDate;
    }

    public BorrowBook(String studentId, int adminId, String returnDate) {
        this.studentId = studentId;
        this.adminId = adminId;
        this.returnDate = returnDate;
    }
}
