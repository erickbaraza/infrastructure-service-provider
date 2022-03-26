

import java.io.IOException;
import business.User;
import data.UserIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Utils;

@WebServlet("add-user")
public class AddUser extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        String message = null;
        String messageType = null;
        
        User user = new User();
         user.setFirstName( request.getParameter("first_name"));
        user.setLastName( request.getParameter("last_name"));
        user.setEmail( request.getParameter("email"));
        user.setRole( request.getParameter("role"));
        user.setPassword( request.getParameter("password"));
        user.setPhoneNumber( request.getParameter("phone_number"));
        
        String code = Utils.generateCode();
        
        user.setVerificationCode( code);
        user.setIsVerified(false);
        
        
        boolean saved = UserIO.saveUser(user);
        
         if (saved) 
        {
            message = "User added successfully.";
            messageType = "success";
        }
        else
        {
            message = "Unable to add User,try again later.";
            messageType = "danger";
        }   
        
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
        
       // forwards to the Companies page
        getServletContext().getRequestDispatcher("/add-user.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        getServletContext().getRequestDispatcher("/add-user.jsp").forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
