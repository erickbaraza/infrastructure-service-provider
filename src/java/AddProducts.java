
import business.Category;
import business.Company;
import business.Product;
import data.AuthIO;
import data.CategoryIO;
import data.CompanyIO;
import data.ProductIO;

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

@WebServlet("/add-product")
@MultipartConfig
public class AddProducts extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        //check if user is logged in
        if( AuthIO.isLoggedIn(request)){
        
            String message = null;
            String messageType = null;
            Product product = new Product();

            product.setName( request.getParameter("name"));
            product.setSlug( request.getParameter("slug"));
            product.setShortDescription( request.getParameter("short_description"));
            product.setDescription(request.getParameter("description"));
            product.setPrice( Float.parseFloat(request.getParameter("price")));
            product.setQuantity( Integer.parseInt(request.getParameter("quantity")));
            product.setMrpPrice( Float.parseFloat(request.getParameter("mrp_price")));
            product.setStatus(request.getParameter("status"));
            product.setCreatedAt( new Date());
            product.setUpdatedAt( new Date());
            long categoryId = Integer.parseInt(request.getParameter("category_id"));

            product.setCategoryId( categoryId);

            long companyId = Integer.parseInt(request.getParameter("company_id"));

            product.setCompanyId( companyId);

            //get and save image
            Part image = request.getPart("image");

            if (image != null) {
                InputStream imageStream = null;
                // obtains input stream of the upload file
                imageStream = image.getInputStream();

                product.setImage( imageStream.readAllBytes());
            }

            boolean saved = ProductIO.saveProduct( product);
            //If product inserted sucessfully in the database

            if (saved) {
                message = "Product added successfully.";
                messageType = "success";
            }else{
                message = "Unable to add Product .";
                messageType = "danger";
            }   

            request.setAttribute("message", message);
            request.setAttribute("messageType", messageType);

           // forwards to the products page
            getServletContext().getRequestDispatcher("/add-product.jsp").forward(request, response);
        }
        else
        {
            response.sendRedirect("login");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        //check if user is logged in
        if( AuthIO.isLoggedIn(request)){
            //get categories
            ArrayList<Category> categories = CategoryIO.getCategories();
            request.setAttribute("categories", categories);
            
            //get companies
            ArrayList<Company> companies = CompanyIO.getCompanies();
            request.setAttribute("companies", companies);

            getServletContext().getRequestDispatcher("/add-product.jsp").forward(request, response);
        }
        else
        {
            response.sendRedirect("login");
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
