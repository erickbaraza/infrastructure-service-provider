import business.Category;
import business.Product;
import data.CategoryIO;
import data.ProductIO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ERICK
 */
@WebServlet("/products")

public class GetProducts extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String message = null;
        String messageType = null;
        
        ArrayList<Product> products = null;
        
        ArrayList<Category> categories = CategoryIO.getCategories();
        
        long productId ;
        
        if( request.getParameter("productId") != null)
        {
            productId = Integer.parseInt(request.getParameter("productId"));
            Product product = ProductIO.getProduct(productId);
            request.setAttribute("product", product);
            // forwards to the products page
            getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
        }
        else
        {
            if( request.getParameter("category") != null)
            {
                String categorySlug = request.getParameter("category");
                products = ProductIO.getProductsByCategory( categorySlug);
            }
            else
            {
                products = ProductIO.getProducts();
            }

            if(products.isEmpty() )
            {
                message = "No products found";
                messageType = "danger";
            }
        
            request.setAttribute("message", message);
            request.setAttribute("messageType", messageType);
            request.setAttribute("products", products);
            request.setAttribute("categories", categories);

            // forwards to the products page
            getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
        }
        
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Gets list of products, includes deleting a product";
    }

}
