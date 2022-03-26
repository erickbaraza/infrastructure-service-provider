package data;

import business.Category;
import business.Service;
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
public class ServiceIO {
    private final static Connection connection = DatabaseConnection.getConnection();
    
    public static boolean saveService(Service service)
    {
        try
        {
            String insertQuery = "" +
                "insert into services(" +
                    "name" + "," +
                    "slug" + "," +
                    "short_description" + "," +
                    "description" + "," +
                    "price" + "," +
                    "category_id" + "," +
                    "company_id" + "," +
                    "image" +
                ") values(?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(insertQuery);
            
            statement.setString(1, service.getName() );
            statement.setString(2, service.getSlug() );
            statement.setString(3, service.getShortDescription());
            statement.setString(4, service.getDescription() );
            statement.setFloat(5, service.getPrice() );
            statement.setLong(6, service.getCategoryId() );
            statement.setLong(7, service.getCompanyId() );
            statement.setBytes(8, service.getImage());
            
            int row = statement.executeUpdate();
            
            return row > 0;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }  
    }
    
    public static ArrayList<Service> getServices(){
        ArrayList<Service> services = new ArrayList<>();
        
        try
        {
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery("select * from services");
            
            while (rs.next())
            {
                //add service to services array list
                services.add(getServiceFromResultSet(rs));
            }
            return services;
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return services;
    }
    
    public static Service getService( long serviceId)
    {
        Service service = new Service();
        try
        {
            String serviceQuery = "SELECT * FROM services WHERE id = " + serviceId + " LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(serviceQuery);
             while (rs.next())
             {
                 service = getServiceFromResultSet(rs);
             }
            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return service;
    }
    
    public static Service getServiceBySlug( String slug)
    {
       Service service = null ;
        try
        {
            String serviceQuery = "SELECT * FROM services WHERE slug = '" + slug + "' LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(serviceQuery);
             while (rs.next())
             {
                 service = getServiceFromResultSet(rs);
             }
            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return service;
    }
    
    public static ArrayList<Service> getServicesByCategory( String categorySlug)
    {
        ArrayList<Service> services = new ArrayList<>();
        try
        {
             Category category = CategoryIO.getCategoryBySlug(categorySlug);
            
            if( category != null){
                String serviceQuery = "SELECT * FROM services where category_id = " + category.getId() ;
                ResultSet rs = DatabaseConnection.getResultFromSqlQuery(serviceQuery);
                while (rs.next())
                {
                    services.add( getServiceFromResultSet(rs));
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return services;
    }
    
    public static boolean  deleteService( long serviceId)
    {
        try
        {
            String deleteQuery = "DELETE FROM services WHERE id = ?";
           Service service = getService(serviceId);

            if(service != null)
            {
                PreparedStatement statement = connection.prepareStatement(deleteQuery);
                statement.setLong(1, serviceId);

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
    
    private static Service getServiceFromResultSet(ResultSet rs)
    {
        Service service = new Service();
        
        try
        {
            service.setId(rs.getLong("id"));
            service.setName(rs.getString("name"));
            service.setSlug( rs.getString("slug"));
            service.setShortDescription( rs.getString("short_description"));
            service.setDescription(rs.getString("description"));
            service.setPrice(rs.getFloat("price"));
            service.setCategoryId(rs.getLong("category_id"));
            service.setCompanyId(rs.getLong("company_id"));
            service.setCreatedAt(rs.getDate("created_at"));
           service.setUpdatedAt( rs.getDate("updated_at"));
            
            Category category = CategoryIO.getCategory( rs.getLong("category_id"));
            service.setCategory(category);
            
            Company company = CompanyIO.getCompany( rs.getLong("company_id"));
            service.setCompany(company);
            
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

            service.setImageToBase64( blobToBase64);
        }
        catch(IOException | SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return service;
    }
}
