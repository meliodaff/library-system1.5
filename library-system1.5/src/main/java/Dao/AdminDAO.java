package Dao;
import Model.Admin;

public interface AdminDAO {
    void register(Admin admin);
    Admin logIn(String username, String password);
    int getAdminId();
    boolean validateSuperAdmin(String username, String password);

}
