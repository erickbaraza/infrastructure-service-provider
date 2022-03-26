package data;
import business.Order;
import business.OrderItem;

import com.connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderIO {
     private static Connection connection = DatabaseConnection.getConnection();
    //get orders
    public static ArrayList<Order> getOrders()
    {
        return null;
    }
    //get orders by user id
    public static ArrayList<Order> getOrdersByUserId(long userId)
    {
     return null;   
    }
    
    //get orders by status
    public static ArrayList<Order> getOrdersByStatus(String status)
    {
        return null;
    }
    
    public static Order getOrder(long orderId){
        Order order = null;
        try
        {
            String categoryQuery = "SELECT * FROM orders WHERE id = " + orderId + " LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(categoryQuery);
             while (rs.next())
             {
                 order = getOrderFromResultSet(rs);
             }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return order;
    }
    //create order
    public static int createOrder(Order order, ArrayList<OrderItem> orderItems )
    {
        try{
            String insertQuery = "INSERT INTO orders(" +
                    "user_id" + "," +
                    "items" + "," +
                    "amount" + "," +
                    "mrp_amount" + "," +
                    "status" + ")" +
                    " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.setInt(2, order.getItems());
            statement.setFloat(3, order.getAmount());
            statement.setFloat(4, order.getMrpAmount());
            statement.setString(5, order.getStatus());
            
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                int orderId = rs.getInt(1);
                createOrderItems(orderItems, orderId);
                return orderId;
            }
            return 0;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }
    
    //create order
    public static boolean createOrderItems( ArrayList<OrderItem> orderItems, long orderId)
    {
        boolean allSaved = false;
        try
        {
            for(OrderItem item: orderItems){
                item.setOrderId(orderId);
                String insertQuery = "INSERT INTO order_items(" +
                    "order_id" + "," +
                    "product_id" + "," +
                    "quantity" + "," +
                    "price" + "," +
                    "mrp_price" + ")" +
                    " VALUES (?, ?, ?, ?, ?)";
                
                PreparedStatement statement = connection.prepareStatement(insertQuery);
                statement.setLong(1, item.getOrderId());
                statement.setLong(2, item.getProductId());
                statement.setInt(3, item.getQuantity());
                statement.setFloat(4, item.getPrice());
                statement.setFloat(5, item.getMrpPrice());
                statement.executeUpdate();
               
            } 
            return true;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            allSaved = false;
        }
        
        return allSaved;
    }
    //delete order
    public static boolean deleteOrder(long orderId)
    {
        try
        {
            String deleteQuery = "DELETE FROM orders WHERE id = ?";
            Order order = getOrder(orderId);

            if(order != null)
            {
                PreparedStatement statement = connection.prepareStatement(deleteQuery);
                statement.setLong(1, orderId);

                statement.execute();
                return true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    private static Order getOrderFromResultSet(ResultSet rs)
    {
        Order order = new Order();
        
        try
        {
            order.setId(rs.getLong("id"));
            order.setUserId(rs.getLong("user_id"));
            order.setItems(rs.getInt("items"));
            order.setAmount(rs.getFloat("amount"));
            order.setMrpAmount( rs.getFloat("mrp_amount"));
            order.setStatus( rs.getString("status"));
            order.setPaymentId( rs.getLong("payment_id"));
            order.setCreatedAt(rs.getDate("created_at"));
            order.setUpdatedAt( rs.getDate("updated_at"));
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return order;
    }
}
