package data;
import business.Cart;
import business.Product;

import com.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartIO {
     private static Connection connection = DatabaseConnection.getConnection();
    //get carts
    public static ArrayList<Cart> getCarts()
    {
        return null;
    }
    
    //get carts by user id
    public static Cart getCartByUserId( long userId)
    {
        Cart cart = null;
        try
        {
            String query = "SELECT * FROM cart WHERE user_id = " + userId + " LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(query);
            while (rs.next())
            {
                cart = getCartFromResultSet(rs);
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return cart;
    }
    
     public static boolean isProductInUserCart( long userId, long productId)
    {
        boolean found = false;
        try
        {
            String query = "SELECT * FROM cart WHERE user_id = " + userId + " AND product_id = " + productId + " LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(query);
            if (rs.next())
            {
                found = true;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return found;
    }
    
    //Check if product exists
    public static ArrayList<Cart> getCartsByUserId( long userId)
    {
        ArrayList<Cart> carts = new ArrayList<>();
        try
        {
            String query = "SELECT * FROM cart WHERE user_id = " + userId + " ORDER BY created_at";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(query);
            while (rs.next())
            {
                //add cart to carts array list
                carts.add(getCartFromResultSet(rs));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return carts;
    }
    
    //add to cart
    public static boolean addItemToCart(long userId, Product product)
    {
        try
        {
            String query =  "";
            boolean exist = false;
            if( isProductInUserCart(userId, product.getId())){
                exist = true;
                query = "UPDATE cart SET quantity = quantity + 1 WHERE user_id = ?  AND product_id = ?";
            }
            else
            {
                query = "INSERT INTO cart( "
                  + "user_id" + ","
                  + "product_id" + ","
                  + "price" + ","
                  + "mrp_price" + ","
                  + "quantity) "
                  + " VALUES(?, ?, ?, ?, ?)";
            }
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setLong(1, userId );
            statement.setLong(2, product.getId());
                
            if(!exist){
                statement.setFloat(3, product.getPrice() );
                statement.setFloat(4, product.getMrpPrice());
                statement.setInt(5, 1);
            }
            
            int row = statement.executeUpdate();
            
            return row > 0;
        }
        catch(Exception exp){
            exp.printStackTrace();
            return false;
        }
    }
    
    //increase item quantity
    public static boolean increaseQuantity(long cartId, long productId)
    {
        return true;
    }
    
    //decrease item quantity
    public static boolean decreaseQuantity(long cartId, long productId)
    {
        return true;
    }
    //confirm item form cart
    public static boolean confirmCartItem(long userId, long productId)
    {
        boolean confirmed;
        try{
            if( isProductInUserCart(userId, productId)){
                String query = "UPDATE cart SET confirm = true WHERE user_id = ?  AND product_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
            
                statement.setLong(1, userId );
                statement.setLong(2, productId);
                
                int row = statement.executeUpdate();
            
                return row > 0;
            }
            confirmed = false;
        }
        catch(SQLException ex){
            confirmed = false;
        }
        return confirmed;
    }
    //remove item form cart
    public static boolean removeItemFromCart(long userId, long productId)
    {
        boolean removed ;
        try{
            if( isProductInUserCart(userId, productId)){
                String query = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setLong(1, userId );
                statement.setLong(2, productId );

                int row = statement.executeUpdate();

               return row > 0 ;
            } 
            removed = false;
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
            removed = false;
        }
        return removed;
    }
    //cleare cart
    public static boolean clearUserCart(long userId)
    {
        boolean cleared;
        try{
            String query = "DELETE FROM cart WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setLong(1, userId );
                
            int row = statement.executeUpdate();

            cleared = row > 0;
            
        }
        catch(SQLException ex){
            cleared = false;
        }
        return cleared;
    }
    
    // get cart sub total
    public static float getSubTotalByUserId(long userId){
        float subTotal = 0;
        ArrayList<Cart> userCarts = getCartsByUserId( userId);
        
        if(userCarts != null){
            for(Cart cartItem : userCarts){
                subTotal += cartItem.getMrpPrice() * cartItem.getQuantity();
            }
        }
        return subTotal;
    }
    //get cart total
    public static float getTotalByUserId(long userId){
        float total = 0;
        ArrayList<Cart> userCarts = getCartsByUserId( userId);
        
        if(userCarts != null){
            for(Cart cartItem : userCarts){
                total += cartItem.getPrice() * cartItem.getQuantity();
            }
        }
        return total;
    }
    
    
    
    private static Cart getCartFromResultSet(ResultSet rs)
    {
        Cart cart = new Cart();
        
        try
        {
            cart.setId(rs.getLong("id"));
            cart.setUserId(rs.getLong("user_id"));
            cart.setProductId(rs.getInt("product_id"));
            cart.setPrice(rs.getFloat("price"));
            cart.setMrpPrice( rs.getFloat("mrp_price"));
            cart.setQuantity( rs.getInt("quantity"));
            cart.setConfirm(rs.getBoolean("confirm"));
            cart.setCreatedAt(rs.getDate("created_at"));
            cart.setUpdatedAt( rs.getDate("updated_at"));
            
            Product product = ProductIO.getProduct( rs.getLong("product_id"));
            cart.setProduct(product);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return cart;
    }
}
