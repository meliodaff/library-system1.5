package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuperAdmin {
    private int id;
    private int info_Id;
    private String username;
    private String password;
    private boolean isActive;

    public SuperAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
