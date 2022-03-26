package data;
import business.Address;
import business.User;

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
public class AddressIO {
    private final static Connection connection = DatabaseConnection.getConnection();

    public static boolean saveAddress(Address address) {
        try {
            String insertQuery = ""
                    + "insert into addresses("
                    + "user_id" + ","
                    + "type" + ","
                    + "country" + ","
                    + "city" + ","
                    + "street" + ","
                    + "house_number" + ","
                    + "postal_code" + ","
                    + "zip_code"
                    + ") values(?, ?, ?, ?,? , ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(insertQuery);

            statement.setLong(1, address.getUserId());
            statement.setString(2, address.getType());
            statement.setString(3, address.getCountry());
            statement.setString(4, address.getCity());
            statement.setString(5, address.getStreet());
            statement.setInt(6, address.getHouseNumber());
            statement.setInt(7, address.getPostalCode());
            statement.setInt(8, address.getZipCode());
            
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

    public static ArrayList<Address> getAddresss() {
        ArrayList<Address> addresses = new ArrayList<>();

        try {
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery("select * from addresses ORDER BY created_at");

            while (rs.next()) {
                //add address to addresses array list
                addresses.add(getAddressFromResultSet(rs));
            }
            return addresses;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return addresses;
    }
    
    public static boolean updateAddress(Address address) {
        try {
            String insertQuery = ""
                    + "UPDATE addresses SET"
                    + " type = ? " + ","
                    + "country = ?" + ","
                    + "city = ?" + ","
                    + "street =? " + ","
                    + "house_number = ?" + ","
                    + "postal_code = ?" + ","
                    + "zip_code = ?"
                    + " WHERE user_id = ?";

            PreparedStatement statement = connection.prepareStatement(insertQuery);

            statement.setString(1, address.getType());
            statement.setString(2, address.getCountry());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getStreet());
            statement.setInt(5, address.getHouseNumber());
            statement.setInt(6, address.getPostalCode());
            statement.setInt(7, address.getZipCode());
            statement.setLong(8, address.getUserId());
            
            
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

    public static ArrayList<Address> getAddresses() {
        ArrayList<Address> addresses = new ArrayList<>();

        try {
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery("select * from addresses ORDER BY created_at");

            while (rs.next()) {
                //add address to addresses array list
                addresses.add(getAddressFromResultSet(rs));
            }
            return addresses;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return addresses;
    }

    public static Address getAddress(long addressId) {
        Address address = null;
        try {
            String addressQuery = "SELECT * FROM addresses WHERE id = " + addressId + " LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(addressQuery);
            while (rs.next()) {
                address = getAddressFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return address;
    }
    public static Address getAddressByUserId(long userId) {
        Address address = null;
        try {
            String addressQuery = "SELECT * FROM addresses WHERE user_id =" + userId + " LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(addressQuery);
            while (rs.next()) {
                address = getAddressFromResultSet(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return address;
    }
    
    public static boolean deleteAddress(long addressId) {
        try {
            String deleteQuery = "DELETE FROM addresses WHERE id = ?";
            Address address = getAddress(addressId);

            if (address != null) {
                PreparedStatement statement = connection.prepareStatement(deleteQuery);
                statement.setLong(1, addressId);

                statement.execute();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean deleteAddressByUserId(long userId) {
        try {
            String deleteQuery = "DELETE FROM addresses WHERE user_id = ?";
            Address address = getAddress(userId);

            if (address != null) {
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

    private static Address getAddressFromResultSet(ResultSet rs) {
        Address address = new Address();

        try {
            address.setId(rs.getLong("id"));
            address.setUserId(rs.getLong("user_id"));
            address.setType( rs.getString("type"));
            address.setCountry(rs.getString("country"));
            address.setCity(rs.getString("city"));
            address.setStreet(rs.getString("street"));
            address.setHouseNumber(rs.getInt("house_number"));
            address.setPostalCode(rs.getInt("postal_code"));
            address.setZipCode(rs.getInt("zip_code"));
            address.setCreatedAt(rs.getDate("created_at"));
            address.setUpdatedAt( rs.getDate("updated_at"));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return address;
    }
}
