package Model;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Author {
    private int id;
    private String name;
    private String email;

    private List<Book> books; // comprehend this more bialen xD
    // for createUpdateBook method
    public Author(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // constructor for adding author record
    public Author(String name, String email){
        this.name = name;
        this.email = email;
    }
    @Override
    public String toString(){

        return null;
    }
}
