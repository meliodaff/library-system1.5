package Implementation;
import Dao.BookDAO;
import Database.Database;
import Model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImplementation implements BookDAO {
    private Database database;

    public BookDAOImplementation(Database database){
        this.database = database;
    }

    @Override
    public List<Book> getBooks(){
        List<Book> books = new ArrayList<>();
        String query = "CALL getBooks()";
        Book book = null;
        try(Connection connection = database.getConnection();
            Statement statement = connection.createStatement();){

            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                book = new Book();

                book.setId(result.getInt("id"));
                book.setTitle(result.getString("title"));
                book.setGenre(result.getString("genre"));
                book.setYear(result.getString("year"));
                book.setStock(result.getInt("stock"));
                book.setAuthor(result.getString("author_name"));
                book.setPublisher(result.getString("publisher_name"));

                books.add(book);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getSpecificBook(int id){
        String query = "CALL getSpecificBook(?)";
        Book book = null;
        try(Connection connection = database.getConnection();
            CallableStatement statement = connection.prepareCall(query);){
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if(result.next()){
                book = new Book();
                book.setTitle(result.getString("title"));
                book.setGenre(result.getString("genre"));
                book.setYear(result.getString("year"));
                book.setStock(result.getInt("stock"));
                book.setAuthor(result.getString("author_name"));
                book.setPublisher(result.getString("publisher_name"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(book == null){
            throw new IllegalArgumentException("No record was found");
        }
        return book;
    }

    /*
    @Override
    public Book getSpecificBook(String word){

        if(true){
            throw new IllegalArgumentException("No strings please");
        }

        return null;
    } // to prevent the user from typing String instead of int. can be better
     */
    @Override
    public boolean addBook(Book book){
        String query = "INSERT INTO books (title, genre, year, stock, author_id, publisher_id) VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);){

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getGenre());
            statement.setString(3, book.getYear());
            statement.setInt(4, book.getStock());
            statement.setInt(5, book.getAuthorId());
            statement.setInt(6, book.getPublisherId());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }




    @Override
    public boolean updateBook(Book book){
        String query = "UPDATE books SET title = ?, genre = ?, year = ?, stock = ?, author_id = ?, publisher_id = ? WHERE id = ?";

        try(Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);){

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getGenre());
            statement.setString(3, book.getYear());
            statement.setInt(4, book.getStock());
            statement.setInt(5, book.getAuthorId());
            statement.setInt(6, book.getPublisherId());
            statement.setInt(7, book.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public boolean deleteBook(int id){
        String query = "DELETE FROM books WHERE id = ?";

        try(Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
