
import business.Category;
import business.Company;
import data.CategoryIO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import data.CompanyIO;
import java.io.InputStream;
import java.util.ArrayList;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@WebServlet("add-company")
@MultipartConfig
public class AddCompanies extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String message = null;
        String messageType = null;
        
        Company company = new Company();
        
        company.setRegNo( request.getParameter("reg_no"));
        company.setName( request.getParameter("name"));
        company.setDescription(request.getParameter("description"));
        company.setEmail(request.getParameter("email"));
        company.setPhone(request.getParameter("phone"));
        company.setAddress(request.getParameter("address"));
        company.setWebsite(request.getParameter("website"));
         long categoryId = Integer.parseInt(request.getParameter("category_id"));

           company.setCategoryId( categoryId);
        
       
        //get and save image
        Part image = request.getPart("image");
        
        if (image != null) 
        {
            InputStream imageStream = null;
            // obtains input stream of the upload file
            imageStream = image.getInputStream();
            
            company.setImage( imageStream.readAllBytes());
        }
        
        boolean saved = CompanyIO.saveCompany( company);
        //If Company inserted sucessfully in the database

        if (saved) 
        {
            message = "Company added successfully.";
            messageType = "success";
        }
        else
        {
            message = "Unable to add Company,try again later.";
            messageType = "danger";
        }   
        
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
        
       // forwards to the Companies page
        getServletContext().getRequestDispatcher("/add-company.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        ArrayList<Category> categories = CategoryIO.getCategories();
        request.setAttribute("categories", categories);

        getServletContext().getRequestDispatcher("/add-company.jsp").forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
