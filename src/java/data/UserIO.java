package data;
import business.User;
import business.Address;

import com.connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author ERICK
 */
public class UserIO {
    private final static Connection connection = DatabaseConnection.getConnection();

    public static boolean saveUser(User user) {
        try {
            String insertQuery = ""
                    + "insert into users("
                    + "first_name" + ","
                    + "last_name" + ","
                    + "email" + ","
                    + "role" + ","
                    + "phone_number" + ","
                    + "password" + ","
                    + "verification_code" + ","
                    + "is_verified"
                    + ") values(?, ?, ?, ?,? , md5(?), ?, ?)";

            PreparedStatement statement = connection.prepareStatement(insertQuery);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getVerificationCode());
            statement.setBoolean(8, user.isIsVerified());
            
            int row = statement.executeUpdate();

            if (row > 0) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean updateUser(User user) {
        try {
            String updateQuery = ""
                    + "UPDATE users SET "
                    + "first_name = ?" + ","
                    + "last_name = ?" + ","
                    + "email = ?" + ","
                    + "phone_number = ?" 
                    + " WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(updateQuery);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhoneNumber());
            statement.setLong(5, user.getId());
             
            int row = statement.executeUpdate();

            if (row > 0) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery("select * from users ORDER BY created_at");

            while (rs.next()) {
                //add user to users array list
                users.add(getUserFromResultSet(rs));
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public static User getUser(long userId) {
        User user = null;
        try {
            String userQuery = "SELECT * FROM users WHERE id = " + userId + " LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(userQuery);
            while (rs.next()) {
                user = getUserFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    public static User getUserByEmail(String email) {
        User user = null;
        try {
            String userQuery = "SELECT * FROM users WHERE email = '" + email + "' LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(userQuery);
            while (rs.next()) {
                user = getUserFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    public static User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        if(email == null || password == null){
            return null;
        }
        else
        {
            try {
                String userQuery = "SELECT * FROM users WHERE email = '" + email + "' AND password=md5('" + password + "') LIMIT 1";
                ResultSet rs = DatabaseConnection.getResultFromSqlQuery(userQuery);
                while (rs.next()) {
                    user = getUserFromResultSet(rs);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return user;
    }
    
    public static boolean deleteUser(long userId) {
        try {
            String deleteQuery = "DELETE FROM users WHERE id = ?";
            User user = getUser(userId);

            if (user != null) {
                PreparedStatement statement = connection.prepareStatement(deleteQuery);
                statement.setLong(1, userId);

                statement.execute();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private static User getUserFromResultSet(ResultSet rs) {
        User user = new User();

        try {
            user.setId(rs.getLong("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setRole(rs.getString("role"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setVerificationCode(rs.getString("verification_code"));
            user.setIsVerified(rs.getBoolean("is_verified"));
            user.setCreatedAt(rs.getDate("created_at"));
            user.setUpdatedAt( rs.getDate("updated_at"));
            
            Address address = AddressIO.getAddressByUserId(rs.getLong("id"));
            
            user.setAddress(address);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }
}
