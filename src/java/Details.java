import business.Category;
import business.Product;
import data.CategoryIO;
import data.ProductIO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Details extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = null;
        String messageType = null; 
        String action = request.getParameter("action");
        Product product = null;
        
        
        
        ArrayList<Category> categories = CategoryIO.getCategories();
         
        if( request.getParameter("slug") != null)
        {
           String slug = request.getParameter("slug");
           product = ProductIO.getProductBySlug(slug);
            
           if(product == null){
               message = "Unable to get product with slug :" + slug;
               messageType = "danger";
           }
           else
           {
               ArrayList<Product> productList = ProductIO.getProductsByCategory(product.getCategory().getSlug());
                ArrayList<Product> products = new ArrayList<>();
                
                //filter to remove the current target product
                
                for(Product p : productList){
                    if(p.getId() != product.getId()){
                        products.add(p);
                    }
                }
                
               request.setAttribute("product", product);
               request.setAttribute("products", products);
               
           }
        }
        else{
             response.sendRedirect("/products");
        }
        
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
        
        request.setAttribute("categories", categories);
        // forwards to the details page
        getServletContext().getRequestDispatcher("/details.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
