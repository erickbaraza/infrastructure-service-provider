/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

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
@WebServlet(urlPatterns = {"/list-products"})
public class AdminGetProducts extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String message = null;
        String messageType = null;
        boolean redirectToEditPage = false; 
        String action = request.getParameter("action");
        ArrayList<Product> products = null;
        
        ArrayList<Category> categories = CategoryIO.getCategories();
        
        long productId = 0l;
        
        if( request.getParameter("productId") != null)
        {
            productId = Integer.parseInt(request.getParameter("productId"));
        }
        
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
        
        
        if( action != null)
        {
            switch (action)
            {
                case "delete":
                    boolean deleted = ProductIO.deleteProduct( productId);
                    if( deleted )
                    {
                        message = "Product with id :" + productId +" deleted successfully";
                        messageType = "success";
                    }
                    else
                    {
                        message = "Unable to delete product with id :" + productId;
                        messageType = "danger";
                    }
                    break;
                case "edit" :
                    
                    redirectToEditPage = true;
                    break;
                default:
                    break;
            }
        }
        
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
       
        if(redirectToEditPage)
        {
            getServletContext().getRequestDispatcher("/edit-product.jsp").forward(request, response);
        }
        else
        {
            // forwards to the products page
            getServletContext().getRequestDispatcher("/list-products.jsp").forward(request, response);
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Gets list of products, includes deleting a product";
    }
}
