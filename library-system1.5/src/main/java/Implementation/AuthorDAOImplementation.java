package Implementation;

import Dao.AuthorDAO;
import Database.Database;
import Model.Author;
import Model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthorDAOImplementation implements AuthorDAO {
    Database database = new Database();
    public AuthorDAOImplementation(Database database){
        this.database = database;
    }

    @Override
    public List<Author> getAuthors(){
        String query = "SELECT * FROM authors ORDER BY name ASC";
        List<Author> authors = new ArrayList<>();
        Author author = null;
        try(Connection connection = database.getConnection();
            Statement statement = connection.createStatement();){

            ResultSet result = statement.executeQuery(query);

            while(result.next()){
                author = new Author();
                author.setId(result.getInt("id"));
                author.setName(result.getString("name"));
                author.setEmail(result.getString("email"));
                authors.add(author);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return authors;
    }

    @Override
    public Author getSpecificAuthor(int id){
        String query = "SELECT * FROM authors WHERE id = ?";
        Author author = null;

        try(Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                author = new Author();

                author.setId(result.getInt("id"));
                author.setName(result.getString("name"));
                author.setEmail(result.getString("email"));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(author == null){
            throw new IllegalArgumentException("No record was found");
        }
        return author;
    }

    @Override
    public boolean addAuthor(Author author){
        String query = "INSERT INTO authors (name, email) VALUES (?, ?)";

        try(Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);){

            statement.setString(1, author.getName());
            statement.setString(2, author.getEmail());

            int rowsAdded = statement.executeUpdate();

            return rowsAdded > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }



    @Override
    public boolean updateAuthor(Author author){
        String query = "UPDATE authors SET name = ?, email = ? WHERE id = ?";
        try(Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);){

            statement.setString(1, author.getName());
            statement.setString(2, author.getEmail());
            statement.setInt(3, author.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean deleteAuthor(int id){
        String query = "DELETE FROM authors WHERE id = ?";

        try(Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);){

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Author> getAuthorBooks(int authorId){
        String query = "CALL getAuthorBooks(?)";
        List<Author> authors = new ArrayList<>();
        Author author = null;
        try(Connection connection = database.getConnection();
            CallableStatement statement = connection.prepareCall(query);){

            statement.setInt(1, authorId);

            ResultSet result = statement.executeQuery();

            while(result.next()){

                List<Book> books = new ArrayList<>();
                Book book = new Book();

                author = new Author();
                author.setName(result.getString("name"));
                author.setEmail(result.getString("email"));
                book.setTitle(result.getString("title"));
                book.setGenre(result.getString("genre"));
                book.setStock(result.getInt("stock"));
                book.setYear(result.getString("year"));

                books.add(book);
                author.setBooks(books);
                authors.add(author);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }
}
