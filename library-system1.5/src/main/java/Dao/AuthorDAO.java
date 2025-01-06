package Dao;
import Model.Author;
import java.util.List;

public interface AuthorDAO {
    List<Author> getAuthors();
    Author getSpecificAuthor(int id);
    boolean addAuthor(Author author);
    boolean updateAuthor(Author author);
    boolean deleteAuthor(int id);
    List<Author> getAuthorBooks(int id);

}
