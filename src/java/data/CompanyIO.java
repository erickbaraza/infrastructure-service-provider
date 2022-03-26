package data;

import business.Category;
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
public class CompanyIO {
    private static Connection connection = DatabaseConnection.getConnection();
    
    public static boolean saveCompany(Company company)
    {
        try
        {
            String insertQuery = "" +
                "insert into companies(" +
                    "reg_no" + "," +
                    "name" + "," +
                    "email" + "," +
                    "phone" + "," +
                    "description" + "," +
                    "category_id" + "," +
                    "address" + "," +
                    "website" + "," +
                    "image" +
                ") values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(insertQuery);
            
            statement.setString(1, company.getRegNo() );
            statement.setString(2, company.getName() );
            statement.setString(3, company.getEmail() );
            statement.setString(4, company.getPhone() );
            statement.setString(5, company.getDescription() );
            statement.setLong(6, company.getCategoryId() );
            statement.setString(7, company.getAddress());
             statement.setString(8, company.getWebsite());
            statement.setBytes(9, company.getImage());
            
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
    
    public static ArrayList<Company> getCompanies(){
        ArrayList<Company> companies = new ArrayList<>();
        
        try
        {
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery("select * from companies ORDER BY created_at");
            
            while (rs.next())
            {
                //add company to companies array list
                companies.add(getCompanyFromResultSet(rs));
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return companies;
    }
    public static ArrayList<Company> getCompaniesByCategory( String categorySlug)
    {
        ArrayList<Company> companies = new ArrayList<>();
        try
        {
            //get category
            Category category = CategoryIO.getCategoryBySlug(categorySlug);
            
            if( category != null){
                String companyQuery = "SELECT * FROM companies where category_id = " + category.getId() ;
                ResultSet rs = DatabaseConnection.getResultFromSqlQuery(companyQuery);
                while (rs.next())
                {
                    companies.add( getCompanyFromResultSet(rs));
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return companies;
    }
    public static Company getCompany( long companyId)
    {
        Company company = new Company();
        try
        {
            String companyQuery = "SELECT * FROM companies WHERE id = " + companyId + " LIMIT 1";
            ResultSet rs = DatabaseConnection.getResultFromSqlQuery(companyQuery);
             while (rs.next())
             {
                 company = getCompanyFromResultSet(rs);
             }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return company;
    }
    
    public static boolean  deleteCompany( int companyId)
    {
        try
        {
            String deleteQuery = "DELETE FROM companies WHERE id = ?";
            Company company = getCompany(companyId);
            
            if(company != null)
            {
                PreparedStatement statement = connection.prepareStatement(deleteQuery);
                statement.setInt(1, companyId);

                statement.execute();
                return true;
            }
            return false;
            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    private static Company getCompanyFromResultSet(ResultSet rs)
    {
        Company company = new Company();
        
        try
        {
            company.setId(rs.getString("id"));
            company.setRegNo(rs.getString("reg_no"));
            company.setName(rs.getString("name"));
            company.setDescription(rs.getString("description"));
            company.setEmail(rs.getString("email"));
            company.setPhone(rs.getString("phone"));
            company.setAddress(rs.getString("address"));
            company.setCategoryId(rs.getLong("category_id"));
            company.setWebsite(rs.getString("website"));
            company.setCreatedAt(rs.getDate("created_at"));
            company.setUpdatedAt( rs.getDate("updated_at"));
            
            Category category = CategoryIO.getCategory( rs.getLong("category_id"));
            company.setCategory(category);
            
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

        company.setImageToBase64( blobToBase64);
        }
        catch(IOException | SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return company;
    }
}
