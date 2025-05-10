package Dao;
import Model.Admin;

public interface AdminDAO {
    void register(Admin admin);
    Admin logIn(String username, String password);
    int getAdminId();
    boolean validateSuperAdmin(String username, String password);
    boolean isSuperAdmin(Admin admin);
    void viewAllUsers();
    void updateUser(int id, String username, String password);
    void toggleUserStatus(int id, boolean activate);
    boolean isAccountActive(String username);
}
