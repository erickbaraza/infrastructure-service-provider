import data.UserIO;
import business.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ERICK
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
       
       String email = request.getParameter("email");
       String password = request.getParameter("password");
       
       User user = UserIO.getUserByEmailAndPassword(email, password);
       
       if( user != null ){
          
           // create cookies
           Cookie emailCookie = new Cookie("email", user.getEmail() );
           
           int expiryTime = 24 * 60 *60; //cookie expires after 24 hours
           
           emailCookie.setMaxAge( expiryTime );
           Cookie loggedInCookie = new Cookie("loggedIn", "loggedIn" );
           loggedInCookie.setMaxAge( expiryTime );
           request.getSession().setAttribute("uname", user.getEmail());
           response.addCookie(emailCookie);
           response.addCookie(loggedInCookie);
           
           if( request.getParameter("redirect") != null)
           {
               String url = request.getParameter("redirect").replace("__", "&");
               
               response.sendRedirect(url);
           }
           else
           {
               response.sendRedirect("dashboard");
           }
           
        }
        else
        {
            String message = "Invalid Login Credentials";
            String messageType = "danger"; 
            
            request.setAttribute("message", message);
            request.setAttribute("messageType", messageType);
            
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
        
        
    
    }
    
}

