package Implementation;
import Dao.AdminDAO;
import Database.Database;
import Model.Admin;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAOImplementation implements AdminDAO {
    private static Database database;

    public AdminDAOImplementation(Database database){
        this.database = database;
    }

        @Override
        public void register(Admin admin) {
            String query = "INSERT INTO admins (name, username, password) VALUES (?, ?, ?)";


            try(Connection con = database.getConnection();
                PreparedStatement pst = con.prepareStatement(query);){
                String encryptedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt(12));
                pst.setString(1, admin.getName());
                pst.setString(2, admin.getUsername());
                pst.setString(3, encryptedPassword);

                int rowsInserted = pst.executeUpdate();

                if(rowsInserted > 0){
                    System.out.println("Registered Successfully");
                    System.out.println("-------------------------------");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        public static boolean isUsernameExists(String username){
            String query = "SELECT COUNT(*) count FROM admins WHERE username = ?";

            try(Connection connection = database.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);){
                preparedStatement.setString(1, username);

                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    int count = resultSet.getInt("count");
                    if(count > 0){
                        return true;
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return false;
        }

    //Admin admin = null;

    private int id;

    @Override
    public int getAdminId(){
        return id;
    }

    @Override
    public Admin logIn(String username, String password){
        String query = "SELECT * FROM admins WHERE username = ?";
        Admin admin = null;
        try(Connection con = database.getConnection();
            PreparedStatement pst = con.prepareStatement(query)){

            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            if(rs.next()){
                String encryptedPassword = rs.getString("password");
                boolean isActive = rs.getBoolean("is_active");
                if(BCrypt.checkpw(password, encryptedPassword)){
                    if(!isActive) {
                        System.out.println("-------------------------------");
                        System.out.println("Your Account is Deactivated. Please Try Again or Contact the Admin.");
                        return null;
                    }
                    admin = new Admin(rs.getString("name"), username, encryptedPassword);
                    admin.setId(rs.getInt("id"));
                }
                else{
                    System.out.println("Invalid Password. Please Try Again.");
                    System.out.println("-------------------------------");
                }
            }
            else{
                System.out.println("Username doesn't exist. Please Try Again.");
                System.out.println("-------------------------------");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public boolean validateSuperAdmin(String username, String password){
        String query = "SELECT * FROM admins WHERE username = ? AND id = 1";

        try(Connection con = database.getConnection();
        PreparedStatement pst = con.prepareStatement(query);){
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String encryptedPassword = rs.getString("password");
                if(BCrypt.checkpw(password, encryptedPassword)){
                    return true;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean isSuperAdmin(Admin admin) {
        return admin.getId() ==1;
    }

    @Override
    public void viewAllUsers() {
        String query = "SELECT * FROM admins";

        try(Connection connection  = database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                System.out.println("User ID: "+resultSet.getInt("id"));
                System.out.println("Full Name: "+ resultSet.getString("name"));
                System.out.println("Username: "+resultSet.getString("username"));
                System.out.println("Password: "+resultSet.getString("password"));
                System.out.println("Status/Activation: "+resultSet.getBoolean("is_active"));
                System.out.println("-------------------------------");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(int id, String username, String password) {
        String query ="UPDATE admins SET username = ?, password = ? WHERE id =?";
        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        try(Connection connection = database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);){

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, encryptedPassword);
            preparedStatement.setInt(3, id);

            int rowsUpdated = preparedStatement.executeUpdate();

            if(rowsUpdated > 0)
                System.out.println("Successfully Updated");
            else
                System.out.println("Unsuccessfully Updated");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void toggleUserStatus(int id, boolean activate) {
        String queryCheckStatus = "SELECT is_active FROM admins WHERE id = ?";
        String queryUpdate = activate ?
                "UPDATE admins SET is_active = 1, updated_at = CURRENT_TIMESTAMP WHERE id = ?" :
                "UPDATE admins SET is_active = 0, updated_at = CURRENT_TIMESTAMP WHERE id = ?";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatementCheck = connection.prepareStatement(queryCheckStatus);
             PreparedStatement preparedStatementUpdate = connection.prepareStatement(queryUpdate)) {

            // Check current status
            preparedStatementCheck.setInt(1, id);
            ResultSet resultSet = preparedStatementCheck.executeQuery();

            if (resultSet.next()) {
                boolean isActive = resultSet.getBoolean("is_active");
                if (activate && isActive) {
                    System.out.println("The account is already activated.");
                    return;
                } else if (!activate && !isActive) {
                    System.out.println("The account is already deactivated.");
                    return;
                }

                // Proceed to update the status
                preparedStatementUpdate.setInt(1, id);
                int rowsAffected = preparedStatementUpdate.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println(activate ? "Successfully Activated" : "Successfully Deactivated");
                } else {
                    System.out.println(activate ? "Unsuccessfully Activated" : "Unsuccessfully Deactivated");
                }

            } else {
                System.out.println("User not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
