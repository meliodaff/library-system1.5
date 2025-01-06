package Implementation;
import Dao.PublisherDAO;
import Database.Database;
import Model.Book;
import Model.Publisher;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;


public class PublisherDAOImplementation implements PublisherDAO {
    Database database = new Database();

    public PublisherDAOImplementation(Database database){
        this.database = database;
    }
    @Override
    public List<Publisher> getPublishers() {
        String query = "SELECT * FROM publishers ORDER BY name asc";
        List<Publisher> publishers = new ArrayList<>();
        Publisher publisher = null;

        try(Connection con = database.getConnection();
            PreparedStatement pst = con.prepareStatement(query);){

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                publisher = new Publisher();
                publisher.setId(rs.getInt("id"));
                publisher.setName(rs.getString("name"));
                publisher.setEmail(rs.getString("email"));
                publisher.setAddress(rs.getString("address"));
                publishers.add(publisher);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return publishers;
    }

    @Override
    public Publisher getSpecificPublisher(int id) {
        String query = "SELECT * FROM publishers WHERE id = ?";
        Publisher publisher = null;

        try(Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                publisher = new Publisher();

                publisher.setId(result.getInt("id"));
                publisher.setName(result.getString("name"));
                publisher.setEmail(result.getString("email"));
                publisher.setAddress(result.getString("address"));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(publisher == null){
            throw new IllegalArgumentException("No record was found");
        }
        return publisher;
    }

    @Override
    public boolean addPublisher(Publisher publisher) {
        String query = "INSERT INTO publishers (name, email, address) VALUES (?, ?, ?)";

        try(Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);){

            statement.setString(1, publisher.getName());
            statement.setString(2, publisher.getEmail());
            statement.setString(3, publisher.getAddress());


            int rowsAdded = statement.executeUpdate();

            return rowsAdded > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePublisher(Publisher publisher) {
        String query = "UPDATE publishers SET name = ?, email = ?, address = ? WHERE id = ?";
        try(Connection connection = database.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);){

            statement.setString(1, publisher.getName());
            statement.setString(2, publisher.getEmail());
            statement.setString(3, publisher.getAddress());
            statement.setInt(4, publisher.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePublisher(int id) {
        String query = "DELETE FROM publishers WHERE id = ?";

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
    public List<Publisher> getPublisherBooks(int id) {
        String query = "CALL getPublisherBooks(?)";
        List<Publisher> publishers = new ArrayList<>();
        Publisher publisher = null;
        try(Connection connection = database.getConnection();
            CallableStatement statement = connection.prepareCall(query);){

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            while(result.next()){

                List<Book> books = new ArrayList<>();
                Book book = new Book();

                publisher = new Publisher();
                publisher.setName(result.getString("publisher_name"));
                publisher.setEmail(result.getString("email"));
                publisher.setAddress(result.getString("address"));
                book.setTitle(result.getString("title"));
                book.setGenre(result.getString("genre"));
                book.setYear(result.getString("year"));
                book.setStock(result.getInt("stock"));

                books.add(book);
                publisher.setBooks(books);
                publishers.add(publisher);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return publishers;
    }


}
