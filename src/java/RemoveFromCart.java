import business.Product;
import business.User;

import data.AuthIO;
import data.CartIO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.ProductIO;
import data.UserIO;
import java.util.ArrayList;
import utils.Utils;

@WebServlet("/remove-from-cart")
public class RemoveFromCart extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String requestURL = Utils.getFullURLFromRequest(request);
        
        //check login
        if( AuthIO.isLoggedIn(request))
        {
            String message = null;
            String messageType = null;
            
            //retrieve user
            String userEmail = Utils.getCookieParam(request, "email");
            User user = UserIO.getUserByEmail(userEmail);
            
             ArrayList<business.Cart> userCarts = new ArrayList<>();
             
            if(user != null) //user exists
            {
                String productSlug = request.getParameter("slug");
                Product product = ProductIO.getProductBySlug(productSlug);

                userCarts = CartIO.getCartsByUserId(user.getId()) ;
                
                if( userCarts != null )
                {
                    boolean removed= CartIO.removeItemFromCart(user.getId(), product.getId());
                    if(removed){
                        message = "Product removed from cart";
                        messageType = "success";
                    }
                    else
                    {
                        message = "Unable to remove product from cart ";
                        messageType = "danger";
                    }

                }
                else
                {
                    message = "Product  not found or you have an empty cart";
                    messageType = "danger";
                }
            }
            else{
                message = "Invalid user account";
                messageType = "danger";
            }
            
            request.setAttribute("message", message);
            request.setAttribute("messageType", messageType);
            request.setAttribute("cart", userCarts);
            if(request.getParameter("redirect") != null){
                 response.sendRedirect(request.getParameter("redirect"));
            }
            else{
                // forwards to the cart page
                getServletContext().getRequestDispatcher("/cart").forward(request, response);
            }
        }
        else
        {
            response.sendRedirect("login?redirect=" + requestURL);
        } 
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Gets list of items in cart";
    }
}
