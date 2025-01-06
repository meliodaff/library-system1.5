package Dashboard;
import Model.Book;
import java.util.List;
import java.util.Scanner;
public class BooksDashboard {
    Scanner scanner = new Scanner(System.in);

    public byte booksDashboard(){
        System.out.println("++ Books ++");
        System.out.println("[1] View Books");
        System.out.println("[2] View Specific Book");
        System.out.println("[3] Add Book");
        System.out.println("[4] Update Book");
        System.out.println("[5] Delete Book");
        System.out.println("[6] Back");
        System.out.println("-------------------------");
        byte choice = scanner.nextByte();
        scanner.nextLine();
        return choice;
    }

    public void displayBooks(List<Book> books){
        for(Book book : books){
            System.out.println("Book ID: " + book.getId());
            System.out.println("Book title: " + book.getTitle());
            System.out.println("Book genre: " + book.getGenre());
            System.out.println("Year: " + book.getYear());
            System.out.println("Stock: " + book.getStock());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Publisher: " + book.getPublisher());
            System.out.println("-----------------------------------");
        }
    }

    public Book createBook(Scanner scanner){
        System.out.print("Book title: ");
        String title = scanner.nextLine();
        System.out.print("Book genre: ");
        String genre = scanner.nextLine();
        System.out.print("Book year (yyyy-mm-dd): ");
        String year = scanner.nextLine();
        System.out.print("Stock: ");
        short stock = scanner.nextShort();
        scanner.nextLine(); // consume buffer line
        System.out.print("Author ID: ");
        short authorId = scanner.nextShort();
        scanner.nextLine();
        System.out.print("Publisher ID: ");
        short publisherId = scanner.nextShort();
        scanner.nextLine();
        Book book = new Book(title, genre, year, stock, authorId, publisherId);
        return book;
    }

    public Book createUpdateBook(Scanner scanner){
        System.out.print("Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        Book book = createBook(scanner);
        book.setId(bookId);
        return book;
    }
}
