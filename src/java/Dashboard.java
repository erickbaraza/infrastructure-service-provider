import business.User;
import utils.Utils;

import data.AuthIO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            //check if user is logged in
            if( AuthIO.isLoggedIn(request)){
                //user details
                String email = Utils.getCookieParam(request, "email");
                
                User user = AuthIO.getLoggedInUser(email);
                if(user != null){
                    request.setAttribute("user", user);
                    if(user.getRole().equals("Customer")){ //redirect to home if customer
                        getServletContext().getRequestDispatcher("/").forward(request, response);
                    } else {
                        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
                    }
                }
                else
                {
                    String message = "An error occured while processing your request";
                    String messageType = "danger"; 

                    request.setAttribute("message", message);
                    request.setAttribute("messageType", messageType);
            
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }
            else
            {
                response.sendRedirect("login");
            }
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
    }

}
