package Dashboard;
import Model.Author;

import java.util.List;
import java.util.Scanner;

public class AuthorsDashboard {
    Scanner scanner = new Scanner(System.in);
    public byte authorsDashboard(){
        System.out.println("++ Authors ++");
        System.out.println("[1] View Authors");
        System.out.println("[2] View Specific Author");
        System.out.println("[3] View Author Books");
        System.out.println("[4] Add Author");
        System.out.println("[5] Update Author");
        System.out.println("[6] Delete Author");
        System.out.println("[7] Back");
        System.out.print("Enter your Option: ");
        byte choice = scanner.nextByte();
        scanner.nextLine();
        System.out.println("-------------------------------");
        return choice;
    }

    public void displayAuthors(List<Author> authors){
        for(Author author : authors){
            System.out.println("Author's ID: " + author.getId());
            System.out.println("Author's name: " + author.getName());
            System.out.println("Author's email: " + author.getEmail());
            System.out.println("-------------------------------");
        }
    }

    public void displaySpecificAuthor(Author author){
        System.out.println("Author's ID: " + author.getId());
        System.out.println("Author's name: " + author.getName());
        System.out.println("Author's email: " + author.getEmail());
        System.out.println("-------------------------------");
    }

    public void displayAuthorBooks(List<Author> author){
        byte i = 1;
        if(author == null || author.isEmpty()){ // i think isEmpty can handle this expression
            System.out.println("Author has no books");
            System.out.println("-------------------------------");
        }
        for(Author authorBooks : author){
            if(i > 0){
                System.out.println("Author name: " + authorBooks.getName());
                System.out.println("Author email: " + authorBooks.getEmail());
                System.out.println("-------------------------------");
                i--;
            }
            System.out.println("-------------------------------");
            System.out.println(authorBooks.getBooks());
            System.out.println("-------------------------------");
        }
    }

    public Author createAuthor(Scanner scanner){
        System.out.print("Author's name: ");
        String name = scanner.nextLine();
        System.out.print("Author's email: ");
        String email = scanner.nextLine();
        Author author = new Author(name, email);
        System.out.println("-------------------------------");
        return author;
    }

    public Author createUpdateAuthor(Scanner scanner){
        System.out.print("Author ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Author's name: ");
        String name = scanner.nextLine();
        System.out.print("Author's email: ");
        String email = scanner.nextLine();
        Author author = new Author(id, name, email);
        System.out.println("-------------------------------");
        return author;
    }
}
