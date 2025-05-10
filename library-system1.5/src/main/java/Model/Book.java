package Model;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Book {

    private int id;
    private String title;
    private String genre;
    private String year;
    private int stock;
    private String author; 
    private String publisher; 


    private int authorId;
    private int publisherId;


    // constructor for inserting book information
    public Book(String title, String genre, String year, int stock, int authorId, int publisherId){
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.stock = stock;
        this.authorId = authorId;
        this.publisherId = publisherId;
    }

    // constructor for updating book information
    public Book(int id, String title, String genre, String year, int stock, int authorId, int publisherId){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.stock = stock;
        this.authorId = authorId;
        this.publisherId = publisherId;
    }
    @Override
    public String toString() {
        return "Title: " + title + "\nGenre: " + genre + "\nYear: " + year + "\nStock: " + stock;
    }
}