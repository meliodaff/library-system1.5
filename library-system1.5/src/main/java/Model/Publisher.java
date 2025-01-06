package Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Publisher extends Author {

    private int id;
    private String address;
    private List<Book> books;
    // use composition or not??
    public Publisher(int id, String name, String email, String address){
        super(name, email);
        this.id = id;
        this.address = address;
    }
    public Publisher(String name, String email, String address) {
        super(name, email);
        this.address = address;
    }
}
