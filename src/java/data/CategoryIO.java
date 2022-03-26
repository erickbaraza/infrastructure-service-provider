/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;
import business.Category;
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
public class CategoryIO {
    private static Connection connection = DatabaseConnection.getConnection();
    
    public static boolean saveCategory(Category category)
    {
        try
        {
            String insertQuery = "" +
                "insert into categories(" +
                    "name" + "," +
                    "slug" + "," +
                    "description" + "," +
                    "status" + "," +
                    "image" +
                ") values(?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(insertQuery);
            
            statement.setString(1, category.getName() );
            statement.setString(2, category.getSlug());
            statement.setString(3, category.getDescription() );
            statement.setString(4, category.getStatus());
            statement.setBytes(5, category.getImage());
            
            int row = statement.executeUpdate();
            
            return row > 0;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }  
    }
    
    public static ArrayList<Category> getCategories(){
        ArrayList<Category> categorys = new ArrayList<>();
        
        try
        {
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery("select * from categories");
            
            while (rs.next())
            {
                //add category to categorys array list
                categorys.add(getCategoryFromResultSet(rs));
            }
            return categorys;
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return categorys;
    }
    
    public static Category getCategory( long categoryId)
    {
        Category category = new Category();
        try
        {
            String categoryQuery = "SELECT * FROM categories WHERE id = " + categoryId + " LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(categoryQuery);
             while (rs.next())
             {
                 category = getCategoryFromResultSet(rs);
             }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return category;
    }
    
    public static Category getCategoryBySlug( String slug)
    {
        Category category = new Category();
        try
        {
            String categoryQuery = "SELECT * FROM categories WHERE slug = '" + slug + "' LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(categoryQuery);
             while (rs.next())
             {
                 category = getCategoryFromResultSet(rs);
             }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return category;
    }
    
    public static boolean  deleteCategory( long categoryId)
    {
        try
        {
            String deleteQuery = "DELETE FROM categories WHERE id = ?";
            Category category = getCategory(categoryId);

            if(category != null)
            {
                PreparedStatement statement = connection.prepareStatement(deleteQuery);
                statement.setLong(1, categoryId);

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
    
    private static Category getCategoryFromResultSet(ResultSet rs)
    {
        Category category = new Category();
        
        try
        {
            category.setId(rs.getLong("id"));
            category.setName(rs.getString("name"));
            category.setSlug(rs.getString("slug"));
            category.setDescription(rs.getString("description"));
            category.setStatus( rs.getString("status"));
            category.setCreatedAt(rs.getDate("created_at"));
            category.setUpdatedAt( rs.getDate("updated_at"));
            
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

        category.setImageToBase64( blobToBase64);
        }
        catch(IOException | SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return category;
    }
}
