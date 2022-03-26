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

@WebServlet("/clear-cart")
public class ClearCart extends HttpServlet {
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
                userCarts = CartIO.getCartsByUserId(user.getId()) ;
                
                if( userCarts != null )
                {
                    boolean cleared = CartIO.clearUserCart(user.getId());
                    if(cleared){
                        message = "Cart cleared successfully";
                        messageType = "success";
                    }
                    else
                    {
                        message = "Unable to clear cart ";
                        messageType = "danger";
                    }

                }
                else
                {
                    message = "You have no products in cart yet";
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
            
            // forwards to the cart page
            getServletContext().getRequestDispatcher("/cart").forward(request, response);
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
