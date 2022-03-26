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

@WebServlet("/cart")
public class Cart extends HttpServlet {
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
           
            
            if(user != null) //user exists
            {
                ArrayList<business.Cart> carts = CartIO.getCartsByUserId(user.getId());
                
                request.setAttribute("cart", carts);
                float totalMrpPrices = CartIO.getSubTotalByUserId(user.getId());
                float totalPrices = CartIO.getTotalByUserId(user.getId());
                
                request.setAttribute("cartTotal" , totalPrices );
                request.setAttribute("cartDiscount" , (totalPrices - totalMrpPrices) );
                request.setAttribute("cartAmount" , totalMrpPrices );
            }
            else{
                message = "You have no items in cart yet";
                messageType = "danger";
            }
            request.setAttribute("message", message);
            request.setAttribute("messageType", messageType);
            // forwards to the products page
            getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
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
