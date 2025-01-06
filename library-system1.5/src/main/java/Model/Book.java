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
    private String author; // taga kuha lang to guys ng pangalan sa db para lang may placeholder, hindi kasi pwedeng id gamitin ditu guys kasi hindi mababasa ng end user
    private String publisher; // parang kaya pa to i improve guys or pwede na to? baka may ibang approach
    // pa kayu syug para sa variable ng author at publisher names


    private int authorId; // eto para pang update at pang inset ng author sa db gamit and id nila
    private int publisherId; // pero pwede rin siguro names nila gamitin kaso baka may kaparehas kasi eh kaya baka tagelidchhi
    // or baka may approach kayu nah iba



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
        // ano ba mas ideal guys? sa dulo yung id kasi diba sa query ng UPDATE na sa dulo yung WHERE clause para sa id
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
        // di ko to gets guys, kinuha ko lang kay chatgpt xD di kasi cute pag display pag walang gantu eh
    }
}