package Dao;
import Model.Publisher;
import java.util.List;

public interface PublisherDAO {
    List<Publisher> getPublishers();
    Publisher getSpecificPublisher(int id);
    boolean addPublisher(Publisher publisher);
    boolean updatePublisher(Publisher publisher);
    boolean deletePublisher(int id);
    List<Publisher> getPublisherBooks(int id);

}
