
import business.Category;
import business.Company;
import business.Service;
import data.CategoryIO;
import data.CompanyIO;
import data.ServiceIO;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

@WebServlet("/add-services")
@MultipartConfig
public class AddServices extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        String message = null;
        String messageType = null;
        Service service = new Service();
        
        service.setName( request.getParameter("name"));
        service.setSlug( request.getParameter("slug"));
        service.setShortDescription( request.getParameter("short_description"));
        service.setDescription(request.getParameter("description"));
        service.setPrice( Float.parseFloat(request.getParameter("price")));
        service.setCreatedAt( new Date());
        service.setUpdatedAt( new Date());
        long categoryId = Integer.parseInt(request.getParameter("category_id"));
        
        service.setCategoryId( categoryId);
        
        long companyId = Integer.parseInt(request.getParameter("company_id"));
        
        service.setCompanyId( companyId);
       
        //get and save image
        Part image = request.getPart("image");
        
        if (image != null) {
            InputStream imageStream = null;
            // obtains input stream of the upload file
            imageStream = image.getInputStream();
            
            service.setImage( imageStream.readAllBytes());
        }
        
        boolean saved = ServiceIO.saveService( service);
        //If service inserted sucessfully in the database

        if (saved) {
            message = "Service added successfully.";
            messageType = "success";
        }else{
            message = "Unable to add Service .";
            messageType = "danger";
        }   
        
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
        
       // forwards to the services page
        getServletContext().getRequestDispatcher("/add-service.jsp").forward(request, response);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        //get categories
        ArrayList<Category> categories = CategoryIO.getCategories();
        request.setAttribute("categories", categories);
        
        //get companies
        ArrayList<Company> companies = CompanyIO.getCompanies();
        request.setAttribute("companies", companies);
        
        getServletContext().getRequestDispatcher("/add-service.jsp").forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
