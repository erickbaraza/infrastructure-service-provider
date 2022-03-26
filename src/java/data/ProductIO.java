package data;

import business.Category;
import business.Product;
import business.Company;

import com.connection.DatabaseConnection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 * @author ERICK
 */
public class ProductIO {
    private final static Connection connection = DatabaseConnection.getConnection();
    
    public static boolean saveProduct(Product product)
    {
        try
        {
            String insertQuery = "" +
                "insert into products(" +
                    "name" + "," +
                    "slug" + "," +
                    "short_description" + "," +
                    "description" + "," +
                    "price" + "," +
                    "quantity" + "," +
                    "mrp_price" + "," +
                    "category_id" + "," +
                    "company_id" + "," +
                    "status" + "," +
                    "image" +
                ") values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(insertQuery);
            
            statement.setString(1, product.getName() );
            statement.setString(2, product.getSlug() );
            statement.setString(3, product.getShortDescription());
            statement.setString(4, product.getDescription() );
            statement.setFloat(5, product.getPrice() );
            statement.setInt(6, product.getQuantity() );
            statement.setFloat(7, product.getMrpPrice() );
            statement.setLong(8, product.getCategoryId() );
            statement.setLong(9, product.getCompanyId() );
            statement.setString(10, product.getStatus());
            statement.setBytes(11, product.getImage());
            
            int row = statement.executeUpdate();
            
            if (row > 0) {
                return true;
            }
            return false;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }  
    }
    
    public static ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        
        try
        {
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery("select * from products ORDER BY created_at");
            
            while (rs.next())
            {
                //add product to products array list
                products.add(getProductFromResultSet(rs));
            }
            return products;
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return products;
    }
    
    public static Product getProduct( long productId)
    {
        Product product = null;
        try
        {
            String productQuery = "SELECT * FROM products WHERE id = " + productId + " LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(productQuery);
             while (rs.next())
             {
                 product = getProductFromResultSet(rs);
             }
            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return product;
    }
    
    public static Product getProductBySlug( String slug)
    {
        Product product = null;
        try
        {
            String productQuery = "SELECT * FROM products WHERE slug = '" + slug + "' LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(productQuery);
             while (rs.next())
             {
                 product = getProductFromResultSet(rs);
             }
            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return product;
    }
    
    public static ArrayList<Product> getProductsByCategory( String categorySlug)
    {
        ArrayList<Product> products = new ArrayList<>();
        try
        {
            //get category
            Category category = CategoryIO.getCategoryBySlug(categorySlug);
            
            if( category != null){
                String productQuery = "SELECT * FROM products where category_id = " + category.getId() ;
                ResultSet rs = DatabaseConnection.getResultFromSqlQuery(productQuery);
                while (rs.next())
                {
                    products.add( getProductFromResultSet(rs));
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return products;
    }
    
    public static boolean  deleteProduct( long productId)
    {
        try
        {
            String deleteQuery = "DELETE FROM products WHERE id = ?";
            Product product = getProduct(productId);

            if(product != null)
            {
                PreparedStatement statement = connection.prepareStatement(deleteQuery);
                statement.setLong(1, productId);

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
    
    private static Product getProductFromResultSet(ResultSet rs)
    {
        Product product = new Product();
        
        try
        {
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setSlug( rs.getString("slug"));
            product.setShortDescription( rs.getString("short_description"));
            product.setDescription(rs.getString("description"));
            product.setQuantity(rs.getInt("quantity"));
            product.setPrice(rs.getFloat("price"));
            product.setMrpPrice(rs.getFloat("mrp_price"));
            product.setCategoryId(rs.getLong("category_id"));
            product.setCompanyId(rs.getLong("company_id"));
            product.setStatus( rs.getString("status"));
            product.setCreatedAt(rs.getDate("created_at"));
            product.setUpdatedAt( rs.getDate("updated_at"));
            
            Category category = CategoryIO.getCategory( rs.getLong("category_id"));
            product.setCategory(category);
            
            Company company = CompanyIO.getCompany( rs.getLong("company_id"));
            product.setCompany(company);
            
            Blob blob = rs.getBlob("image");
            String blobToBase64 = null;

            if(blob != null)
            {
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();

                blobToBase64 = Base64.getEncoder().encodeToString(imageBytes);

                inputStream.close();
                outputStream.close();
            }

            product.setImageToBase64( blobToBase64);
        }
        catch(IOException | SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return product;
    }
}
