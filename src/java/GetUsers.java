
import business.User;

import data.UserIO;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("list-users")
public class GetUsers extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = null;
        String messageType = null;
        
        String action = request.getParameter("action");
        
        ArrayList<User> users = UserIO.getUsers();
        
        if(users.isEmpty())
        {
           message = "No users found";
            messageType = "danger";
        }
        
        request.setAttribute("users", users);
        
        if( action != null)
        {
            switch (action)
            {
                case "delete":
                    String id = request.getParameter("userId");
                    int userId = 0;

                    if(id != null)
                    {
                        userId = Integer.parseInt(id);
                    }
                    boolean deleted = UserIO.deleteUser( Integer.parseInt(id));
                    if( deleted )
                    {
                        message = "User with id :" + userId +" deleted successfully";
                        messageType = "success";
                    }
                    else
                    {
                        message = "Unable to delete user with id :" + userId;
                        messageType = "danger";
                    }
                    break;
                default:
                    break;
            }
        }
        
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
       
        
        // forwards to the companies page
        getServletContext().getRequestDispatcher("/list-users.jsp").forward(request, response);
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Get list of users including deleting users";
    }

}
