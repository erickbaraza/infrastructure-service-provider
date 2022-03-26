package data;

import business.OrderItem;
import com.connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderItemIO {
     private static Connection connection = DatabaseConnection.getConnection();
    //get order items by order id
    public static ArrayList<OrderItem> getOrderItemsByOrderId(long orderId)
    {
        return null;
    }
    
    //add order item
    public static boolean addItemToOrder(OrderItem orderItem)
    {
        try{
            String insertQuery = "INSERT INTO order_items(" +
                    "order_id" + "," +
                    "product_id" + "," +
                    "quantity" + "," +
                    "price" + "," +
                    "mrp_price" + ")" +
                    " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setLong(1, orderItem.getOrderId());
            statement.setLong(2, orderItem.getProductId());
            statement.setInt(3, orderItem.getQuantity());
            statement.setFloat(4, orderItem.getPrice());
            statement.setFloat(5, orderItem.getMrpPrice());
            
            int rows = statement.executeUpdate();
            return rows > 0;
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    
    
    //increase item quantity
    public static boolean increaseQuantity(long orderId, long productId)
    {
        return true;
    }
    
    //decrease item quantity
    public static boolean decreaseQuantity(long orderId, long productId)
    {
        return true;
    }
    
    //remove item form order
    public static boolean removeItemFromOrder(long orderId, long productId)
    {
        return true;
    }
    
}
